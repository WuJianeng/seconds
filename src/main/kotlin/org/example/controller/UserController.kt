package org.example.controller

import org.example.domain.User
import org.example.redis.RedisService
import org.example.result.Result
import org.example.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/user")
class UserController(
        val userService: UserService,
        val redisService: RedisService
) {

    @RequestMapping("/info")
    @ResponseBody
    fun info(model: Model, user: User) = Result.success(user)
}