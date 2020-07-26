package org.example.vo

import org.example.domain.Goods
import java.util.*

data class GoodsVo (
        val id: Long,
        val goodsName: String,
        val goodsTitle: String,
        val goodsImg: String,
        val goodsDetail: String,
        val goodsPrice: Double,
        val goodsStock: Int,

        val killPrice: Double,
        val stockCount: Int,
        val startDate: Date,
        val endDate: Date
)