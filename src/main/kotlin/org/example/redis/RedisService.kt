package org.example.redis

import com.alibaba.fastjson.JSON
import org.springframework.stereotype.Service
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool

@Service
class RedisService(val jedisPool: JedisPool) {

    /**
     * 获取当个对象
     */
    fun <T> get(prefix: KeyPrefix, key: String, clazz: Class<T>): T? {
        var jedis: Jedis? = null
        try {
            jedis = jedisPool.resource
            val res = jedis.get(generateKey(prefix, key))
            return resolveString(res, clazz)
        } finally {
            jedis?.close()
        }
    }

    /**
     * 设置对象
     */
    fun <T> set(prefix: KeyPrefix, key: String, value: T) {
        var jedis: Jedis? = null
        try {
            jedis = jedisPool.resource
            val s = parseToString(value)
            val expireTime = prefix.expireSeconds()
            if (expireTime <= 0) {
                jedis.set(generateKey(prefix, key), s)
            } else {
                jedis.setex(generateKey(prefix, key), expireTime, s)
            }
        }finally {
            jedis?.close()
        }
    }

    /**
     * 判断是否存在
     */
    fun exist(prefix: KeyPrefix, key: String): Boolean {
        var jedis: Jedis? = null
        try {
            jedis = jedisPool.resource
            return jedis.exists(generateKey(prefix, key))
        }finally {
            jedis?.close()
        }
    }

    fun decr(prefix: KeyPrefix, key: String): Long {
        var jedis: Jedis? = null
        try {
            jedis = jedisPool.resource
            return jedis.decr(generateKey(prefix, key))
        }finally {
            jedis?.close()
        }
    }

    fun incr(prefix: KeyPrefix, key: String): Long {
        var jedis: Jedis? = null
        try {
            jedis = jedisPool.resource
            return jedis.incr(generateKey(prefix, key))
        }finally {
            jedis?.close()
        }
    }

    private fun generateKey(prefix: KeyPrefix, key: String) = "${prefix.getPrefix()}$key"

    private fun <T> parseToString(value: T): String {
        return when (value) {
            is Int, Long, Double, Float, String -> value.toString()
            else -> JSON.toJSONString(value)
        }
    }

    private fun <T> resolveString(string: String, clazz: Class<T>): T {
        return when (clazz) {
            Int::class -> string.toInt()
            Long::class -> string.toLong()
            Double::class -> string.toDouble()
            Float::class -> string.toFloat()
            String::class -> this
            else -> JSON.parseObject(string, clazz)
        } as T
    }

}
