package org.example.util

import java.util.regex.Pattern

private val mobilePattern: Pattern = Pattern.compile("1\\d{10}")

fun isMobile(src: String): Boolean {
    val mather = mobilePattern.matcher(src)
    return mather.matches()
}

fun main() {
    println("${isMobile("13928374859")}")
    println("${isMobile("135234567890")}")
}