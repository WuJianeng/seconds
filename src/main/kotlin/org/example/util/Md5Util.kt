package org.example.util

import org.springframework.util.DigestUtils

const val salt: String = "1a2b3c4d"

private fun md5(src: String): String = DigestUtils.md5DigestAsHex(src.toByteArray())

fun inputPassToFormPass(inputPass: String): String {
    val str = "${salt[0]}${salt[2]}$inputPass${salt[5]}${salt[4]}"
    return md5(str)
}

fun formPassToDbPass(formPass: String, salt: String): String {
    val str = "${salt[0]}${salt[2]}$formPass${salt[5]}${salt[4]}"
    return md5(str)
}

fun inputPassToDbPass(inputPass: String, salt: String): String {
    val fromPass: String = inputPassToFormPass(inputPass)
    return formPassToDbPass(fromPass, salt)
}

fun main() {
    println(inputPassToFormPass("123456")) // d3b1294a61a07da9b49b6e22b2cbd7f9
    println(formPassToDbPass(inputPassToFormPass("123456"), salt)) // b7797cce01b4b131b433b6acf4add449
}
