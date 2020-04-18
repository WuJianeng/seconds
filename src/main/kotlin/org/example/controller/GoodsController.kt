package org.example.controller

import org.example.domain.User
import org.example.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/goods")
class GoodsController(val userService: UserService) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @RequestMapping("/to_list")
    fun toList(model: Model, user: User): String {
        model.addAttribute("user", user)
        return "goods_list"
    }
}