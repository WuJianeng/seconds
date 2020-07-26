package org.example.domain

import java.util.function.LongFunction

data class Order (
        val id: Long?,
        val userId: Long,
        val orderId: Long,
        val goodsId: Long
)