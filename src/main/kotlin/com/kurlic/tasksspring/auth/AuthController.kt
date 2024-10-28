package com.kurlic.tasksspring.auth

import com.kurlic.tasksspring.data.User
import com.kurlic.tasksspring.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

data class AuthRequest(val login: String, val password: String)

@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @PostMapping("/register")
    fun register(@RequestBody authRequest: AuthRequest): String {
        val user = User(name = "sth", login = authRequest.login, password = authRequest.password)
        return try {
            userRepository.save(user)
            authRequest.login + "+" + authRequest.password
        } catch (ex: Exception) {
            "0"
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): String {
        val user = userRepository.findUserByLogin(authRequest.login)
        if (user != null) {
            if (user.password == authRequest.password) {
                return "True"
            }
        }
        return "False"
    }
}