package org.example.redis

abstract class BasePrefix(private val prefix: String, private val expireSeconds: Int): KeyPrefix {

    constructor(prefix: String): this(prefix, 0)

    override fun expireSeconds(): Int {
        return expireSeconds
    }

    override fun getPrefix(): String {
        val className = javaClass.simpleName
        return "$className:$prefix"
    }

}