package org.example.service

import org.example.dao.GoodsDao
import org.example.domain.Goods
import org.example.domain.OrderInfo
import org.example.domain.User
import org.example.vo.GoodsVo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class KillService(
        val goodsService: GoodsService,
        val orderService: OrderService
) {

    @Transactional
    fun kill(user: User, goods: GoodsVo): OrderInfo {
        // 减库存
        goodsService.reduceStock(goods.id)
        // 生成订单
        return orderService.createOrder(user, goods)
    }

}
