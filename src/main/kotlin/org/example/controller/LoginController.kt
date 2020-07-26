package org.example.controller

import lombok.extern.slf4j.Slf4j
import org.example.result.Result
import org.example.service.UserService
import org.example.vo.LoginVo
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Slf4j
@Controller
@RequestMapping("/login")
class LoginController(val userService: UserService) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @RequestMapping("/to_login")
    fun toLogin(): String {
        return "login"
    }

    @RequestMapping("/do_login")
    @ResponseBody
    fun  doLogin(response: HttpServletResponse, @Valid loginVo: LoginVo): Result<String> {
        log.info(loginVo.toString())
        val token = userService.login(response, loginVo)
        return Result.success(token)
    }
}