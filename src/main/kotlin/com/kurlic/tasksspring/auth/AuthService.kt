package com.kurlic.tasksspring.auth

import com.kurlic.tasksspring.data.User
import com.kurlic.tasksspring.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

data class AuthRequest(val login: String, val password: String)

@Service
class AuthService {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun authenticate(authRequest: AuthRequest): User? {
        val user = userRepository.findUserByLogin(authRequest.login)
        return if (user != null && user.password == authRequest.password) {
            user
        } else {
            null
        }
    }

    fun register(registerData: RegisterData): User? {
        val user = User(
            name = registerData.name,
            login = registerData.authRequest.login,
            password = registerData.authRequest.password
        )
        return try {
            userRepository.save(user)
            user
        } catch (ex: Exception) {
            println("ERROR to register: ${ex.message}")
            null
        }
    }
}