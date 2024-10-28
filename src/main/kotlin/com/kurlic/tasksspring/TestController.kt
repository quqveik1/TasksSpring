package com.kurlic.tasksspring

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/vbu")
    fun test(@RequestParam(value = "login") login: String, @RequestParam(value = "password") password: String): String {
        return login + password
    }
}