package org.example.domain

import java.util.Date


data class OrderInfo (
        var id: Long?,
        val userId: Long,
        val goodsId: Long,
        val deliveryAddrId: Long?,
        val goodsName: String,
        val goodsCount: Int,
        val goodsPrice: Double,
        val orderChannel: Int,
        val status: Int,
        val createDate: Date,
        val payDate: Date?
)