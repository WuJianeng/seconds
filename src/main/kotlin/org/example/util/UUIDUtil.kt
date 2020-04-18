package org.example.util

import java.util.*

fun uuid(): String {
    return UUID.randomUUID().toString().replace("-", "")
}

fun main() {
    println(uuid())
}