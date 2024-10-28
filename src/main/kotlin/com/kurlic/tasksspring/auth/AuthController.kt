package com.kurlic.tasksspring.auth

import com.kurlic.tasksspring.data.User
import com.kurlic.tasksspring.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private lateinit var authService: AuthService


    @PostMapping("/register")
    fun register(@RequestBody registerData: RegisterData): String {
        val res = authService.register(registerData)
        return res.toString()
    }

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): String {
        val res = authService.authenticate(authRequest)
        return if (res != null) {
            "True"
        } else {
            "False"
        }
    }
}