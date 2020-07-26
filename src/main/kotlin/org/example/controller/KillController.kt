package org.example.controller


import org.example.domain.User
import org.example.result.CodeMsg
import org.example.service.GoodsService
import org.example.service.KillService
import org.example.service.OrderService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/kill")
class KillController(
        val goodsService: GoodsService,
        val orderService: OrderService,
        val killService: KillService
) {

    @RequestMapping("/do_kill")
    fun doKill(model: Model,
               user: User,
               @RequestParam("goodsId")goodsId: Long
    ): String {
        model.addAttribute("user", user)
        if (user == null) {
            return "login"
        }

        // 判断商品是否还有库存
        val goods = goodsService.getGoodsVoByGoodsId(goodsId)


        if (goods.goodsStock <= 0) {
            model.addAttribute("errmsg", CodeMsg.KILL_OVER.msg)
            return "kill_fail"
        }

        //判断是否已经秒杀到了
        val order = orderService.getKillOrderByUserIdAndGoodsId(user.id, goodsId)

        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_KILL.msg)
            return "kill_fail"
        }

        // 减库存，下订单 (事务)
        val orderInfo = killService.kill(user, goods)
        model.addAttribute("orderInfo", orderInfo)
        model.addAttribute("goods", goods)
        return "order_detail"
    }
}