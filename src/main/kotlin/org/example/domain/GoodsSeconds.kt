package org.example.domain

import java.util.*

data class GoodsSeconds (
        val id: Long,
        val goodsId: Long,
        val secondsPrice: Double,
        val stockCount: Int,
        val startDate: Date,
        val endDate: Date,
        val version: Int
)