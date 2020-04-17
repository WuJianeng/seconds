package org.example.redis

interface KeyPrefix {
    fun expireSeconds(): Int

    fun getPrefix(): String

}