package org.example.config

import org.example.domain.User
import org.example.redis.UserKey
import org.example.service.UserService
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Service
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class UserArgumentResolver(val userService: UserService): HandlerMethodArgumentResolver {
    override fun resolveArgument(p0: MethodParameter, p1: ModelAndViewContainer?, p2: NativeWebRequest, p3: WebDataBinderFactory?): Any? {
        val request = p2.getNativeRequest(HttpServletRequest::class.java)
        val response = p2.getNativeResponse(HttpServletResponse::class.java)

        if (request == null || response == null) {
            return null
        }
        val paramToken = request.getAttribute(UserService.COOKIE_NAME_TOKEN) as String?
        val cookieToken = getCookieToken(request, UserService.COOKIE_NAME_TOKEN) as String?
        return if (paramToken == null && cookieToken == null) {
            null
        } else {
            val token: String = (paramToken ?: cookieToken) as String
            userService.getByToken(response, token)
        }
    }

    private fun getCookieToken(request: HttpServletRequest, cookieName: String): Any? {
        val cookies = request.cookies
        for (cookie in cookies) {
            if (cookie.name == cookieName) {
                return cookie.value
            }
        }
        return null
    }

    override fun supportsParameter(p0: MethodParameter): Boolean {
        val clazz = p0.parameterType
        return clazz == User::class.java
    }
}
