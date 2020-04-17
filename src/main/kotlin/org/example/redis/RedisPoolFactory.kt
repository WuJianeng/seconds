package org.example.redis

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig


@Component
class RedisPoolFactory(val redisConfig: RedisConfig){
    /**
     * 将redis连接池注入spring容器
     */
    @Bean
    fun jedisPoolFactory() : JedisPool {
        val jedisPoolConfig = JedisPoolConfig()
        return with(redisConfig) {
                jedisPoolConfig.maxTotal = poolMaxTotal
                jedisPoolConfig.maxIdle = poolMaxIdle
                jedisPoolConfig.maxWaitMillis = poolMaxWait
                JedisPool(jedisPoolConfig, host, port, timeout)
            }
    }
}