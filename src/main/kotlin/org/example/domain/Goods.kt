package org.example.domain

data class Goods(val id: Long,
                 val goodsName: String,
                 val goodsTitle: String,
                 val goodsImg: String,
                 val goodsDetail: String,
                 val goodsPrice: Double,
                 val goodsStock: Int
)