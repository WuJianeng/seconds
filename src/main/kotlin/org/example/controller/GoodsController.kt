package org.example.controller

import org.example.domain.User
import org.example.service.GoodsService
import org.example.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/goods")
class GoodsController(
        val userService: UserService,
        val goodsService: GoodsService
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @RequestMapping("/to_list")
    fun toList(model: Model, user: User): String {
        model.addAttribute("user", user)

        // 查询商品列表
        val goodsList = goodsService.listGoodsVo()
        model.addAttribute("goodsList", goodsList)
        return "goods_list"
    }

    @RequestMapping("to_detail/{goodsId}")
    fun detail(model: Model, user: User, @PathVariable("goodsId") goodsId: Long): String {
        model.addAttribute("user", user)
        log.info("user", user)

        val goods = goodsService.getGoodsVoByGoodsId(goodsId)
        model.addAttribute("goods", goods)


        val startAt = goods.startDate.time
        val endAt = goods.endDate.time
        val now = System.currentTimeMillis()

        var remainSeconds = 0
        val killStatus = when {
            now < startAt -> {
                remainSeconds = ((startAt - now) / 1000).toInt()
                0
            }
            now > endAt -> {
                remainSeconds = -1
                2
            }
            else -> {
                remainSeconds = 0
                1
            }
        }

        model.addAttribute("killStatus", killStatus)
        model.addAttribute("remainSeconds", remainSeconds)

        return "goods_detail"
    }
}