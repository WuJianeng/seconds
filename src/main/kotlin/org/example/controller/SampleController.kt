package org.example.controller

import org.example.redis.RedisService
import org.example.redis.UserKey
import org.example.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author WuJianeng
 */
@Controller
@RequestMapping("demo")
class SampleController(val userService: UserService, val redisService: RedisService) {

    @RequestMapping("/hello/thymeleaf")
    fun thymeleaf(model: Model): String {
        model.addAttribute("name", "Joshua")
        return "hello"
    }

    @GetMapping("/db/get")
    @ResponseBody
    fun dbGet(): String{
        return userService.getById(1).toString()
    }

    @GetMapping("/tx")
    @ResponseBody
    fun tran(): String {
        val res: Boolean = userService.tx()
        return res.toString()
    }

    @GetMapping("/redis/get/{key}")
    @ResponseBody
    fun redisGet(@PathVariable("key") key: String): String {
        return redisService.get(UserKey.getDefault(), key, String::class.java).toString()
    }

    @GetMapping("/redis/set/{key}/{value}")
    @ResponseBody
    fun set(@PathVariable("key") key: String, @PathVariable("value") value: String): String {
        redisService.set(UserKey.getDefault(), key, value)
        return "success"
    }
}