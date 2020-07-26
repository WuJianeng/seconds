package org.example.service

import org.example.dao.OrderDao
import org.example.domain.Order
import org.example.domain.OrderInfo
import org.example.domain.User
import org.example.vo.GoodsVo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.math.log


@Service
class OrderService(
        val orderDao: OrderDao
) {

    fun getKillOrderByUserIdAndGoodsId(userId: Long, goodsId: Long): Order? {
        return orderDao.getOrderByUserIdAndOrderId(userId, goodsId)
    }

    /***
     * 创建订单，属于事务，原子性
     */
    @Transactional
    fun createOrder(user: User, goods: GoodsVo): OrderInfo {
        val orderInfo = OrderInfo(null, user.id, goods.id, null, goods.goodsName, 1, goods.goodsPrice, 1, 0, Date(), null)

        println("orderInfo: $orderInfo")

        val orderId = orderDao.insert(orderInfo)

        orderInfo.id = orderId

        val order = Order(null, user.id, orderId, goods.id)
        orderDao.insertOrder(order)

        return orderInfo
    }
}