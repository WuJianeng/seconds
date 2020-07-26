package org.example.util

import com.alibaba.fastjson.JSON
import org.example.domain.User
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.RandomAccessFile
import java.net.HttpURLConnection
import java.net.URL
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

object UserUtil {
    fun createUser(count: Int) {
        val users = ArrayList<User>(count)

        for (i in 0 until count) {
            val salt = "1a2b3c"
            val user = User(13000000000L+i, "user${i}", inputPassToDbPass("123456", salt), salt,
                    null, Date(), Date(), 1)
            users.add(user)
            println("user:${user}")
        }

        println("create user")

        // 插入 database
        val connection = DBUtil.getConnection()
        val sql = "INSERT INTO user(login_count, nickname, register_date, salt, password, id)VALUES(?,?,?,?,?,?)"
        val statement = connection.prepareStatement(sql)
        for (user in users) {
            with(statement) {
                setInt(1, user.loginCount)
                setString(2, user.nickname)
                setTimestamp(3, Timestamp(user.registerDate.time))
                setString(4, user.salt)
                setString(5, user.password)
                setLong(6, user.id)
                addBatch()
            }
        }
        statement.executeBatch()
        statement.close()
        connection.close()

        println("insert to db")

        // 生成 token
        val loginUrl = "http://localhost:8081/login/do_login"
        val file = File("E:\\Projects\\Java\\tokens.txt")
        if (file.exists()) {
            file.delete()
        }

        val randomAccessFile = RandomAccessFile(file, "rw")
        file.createNewFile()
        randomAccessFile.seek(0)


        for(user in users) {
            val url = URL(loginUrl)
            val co = url.openConnection() as HttpURLConnection
            co.requestMethod = "POST"
            co.doOutput = true
            val out = co.outputStream
            val params = "mobile="+user.id+"&password="+ inputPassToFormPass("123456")
            out.write(params.toByteArray())
            out.flush()
            val inputStream = co.inputStream
            val bout = ByteArrayOutputStream()
            val buff = ByteArray(1024)
            var len = inputStream.read(buff)
            while(len >= 0) {
                bout.write(buff, 0 ,len)
                len = inputStream.read(buff)
            }
            inputStream.close()
            bout.close()
            val response = String(bout.toByteArray())
            val jo = JSON.parseObject(response)
            val token = jo.getString("data")
            println("create token : " + user.id)

            val row = user.id.toString()+","+token
            randomAccessFile.seek(randomAccessFile.length())
            randomAccessFile.write(row.toByteArray())
            randomAccessFile.write("\r\n".toByteArray())
            println("write to file : " + user.id)
        }
        randomAccessFile.close()

        println("over")

    }
}

fun main() {
    UserUtil.createUser(5000)
}