package org.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

/**
 * @author WuJianeng
 */
@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
class MainApplication

fun main(args: Array<String>) {
    SpringApplication.run(MainApplication::class.java, *args)
}
