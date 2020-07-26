package org.example.util

import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

object DBUtil {

    private val props: Properties
        get() {
            val pro = Properties()
            try {
                val input = this::class.java.classLoader.getResourceAsStream("application.properties")
                pro.load(input)
                input.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return pro
        }

    fun getConnection(): Connection {
        val url = props.getProperty("spring.datasource.url")
        val username = props.getProperty("spring.datasource.username")
        val password = props.getProperty("spring.datasource.password")

        return DriverManager.getConnection(url, username, password)
    }
}