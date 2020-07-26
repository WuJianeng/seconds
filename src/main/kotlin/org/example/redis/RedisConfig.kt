package org.example.redis

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "redis")
data class RedisConfig(val host: String,
                  val port: Int,
                  val password: String,
                  val timeout: Int,
                  val poolMaxTotal: Int,
                  val poolMaxIdle: Int,
                  val poolMaxWait: Long
) {

}