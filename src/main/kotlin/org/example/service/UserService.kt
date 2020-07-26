package org.example.service

import org.example.dao.UserDao
import org.example.domain.User
import org.example.exception.GlobalException
import org.example.redis.RedisService
import org.example.redis.UserKey
import org.example.result.CodeMsg
import org.example.util.UserUtil
import org.example.util.formPassToDbPass
import org.example.util.uuid
import org.example.vo.LoginVo
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Service
class UserService(
        val userDao: UserDao,
        val redisService: RedisService
) {

    fun getById(id: Long): User? {
        return userDao.getById(id)
    }

    fun login(response: HttpServletResponse, loginVo: LoginVo): String {
        val mobile = loginVo.mobile
        val password = loginVo.password
        val user = getById(mobile.toLong()) ?: throw GlobalException(CodeMsg.MOBILE_NOT_EXIST)
        // verify password
        val dbPass = user.password
        val saltDb = user.salt
        val calPass = formPassToDbPass(password, saltDb)
        if (calPass != dbPass) {
            throw GlobalException(CodeMsg.PASSWORD_ERROR)
        }

        // 使用uuid作为token
        val token = uuid()
        addCookie(response, token, user)
        return token
    }

    fun getByToken(response: HttpServletResponse, token: String): User? {
        val user = redisService.get(UserKey.token, token, User::class.java)
        user?.run {
            // 更新有效期
            addCookie(response, token, user)
        }
        return user
    }

    companion object{
        public const val COOKIE_NAME_TOKEN:String = "token"
    }

    private fun addCookie(response: HttpServletResponse, token: String, user: User) {
        // 将 token 写入 redis 中
        redisService.set(UserKey.token, token, user)
        // 设置cookie
        val cookie: Cookie = Cookie(COOKIE_NAME_TOKEN, token)
        cookie.maxAge = UserKey.token.expireSeconds()
        cookie.path = "/"
        response.addCookie(cookie)
    }

//    /**
//     * 测试事务，
//    @Transactional
//    fun tx(): Boolean {
//        val user = User(3, "Jack")
//        val user1 = User(1, "Mack")
//        userDao.insert(user)
//        userDao.insert(user1)
//
//        return true
//    }*/

}