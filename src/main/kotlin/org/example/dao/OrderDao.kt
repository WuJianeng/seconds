package org.example.dao

import org.apache.ibatis.annotations.*
import org.example.domain.Order
import org.example.domain.OrderInfo

@Mapper
interface OrderDao {

    @Select("SELECT * FROM x_order WHERE user_id=#{userId} AND goods_id=#{goodsId}")
    fun getOrderByUserIdAndOrderId(@Param("userId")userId: Long, @Param("goodsId")goodsId: Long): Order

    @Insert("INSERT INTO order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date) " +
            "VALUES(#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = Long::class, before = false, statement = ["select last_insert_id()"])
    fun insert(orderInfo: OrderInfo): Long

    @Insert("INSERT INTO x_order(user_id, order_id, goods_id) VALUES(#{userId}, #{orderId}, #{goodsId})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false, statement = ["select last_insert_id()"], resultType = Long::class)
    fun insertOrder(order: Order): Long
}