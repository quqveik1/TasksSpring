package com.kurlic.tasksspring.auth

import com.kurlic.tasksspring.data.User
import com.kurlic.tasksspring.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private lateinit var authService: AuthService


    @PostMapping("/register")
    fun register(@RequestBody registerData: RegisterData): ResponseEntity<String> {
        val res = authService.register(registerData)
        return if (res == null) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(res.toString())
        } else {
            ResponseEntity.status(HttpStatus.OK).body(res.toString())
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): ResponseEntity<String> {
        val res = authService.authenticate(authRequest)
        return if (res == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed")
        } else {
            ResponseEntity.status(HttpStatus.OK).body("Successfully logged in")
        }
    }

    @PostMapping("/delete")
    fun delete(@RequestBody authRequest: AuthRequest): ResponseEntity<String> {
        val res = authService.deleteUser(authRequest)
        return if (!res) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Delete failed")
        } else {
            ResponseEntity.status(HttpStatus.OK).body("Successfully deleted account")
        }
    }
}