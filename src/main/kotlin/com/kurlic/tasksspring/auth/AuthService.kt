package com.kurlic.tasksspring.auth

import com.kurlic.tasksspring.data.User
import com.kurlic.tasksspring.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

data class AuthRequest(val login: String, val password: String)

@Service
class AuthService {
    @Autowired
    private lateinit var userRepository: UserRepository

    private val passwordEncoder = BCryptPasswordEncoder()

    fun authenticate(authRequest: AuthRequest): User? {
        val user = userRepository.findUserByLogin(authRequest.login)
        return if (user != null && passwordEncoder.matches(authRequest.password, user.password)) {
            user
        } else {
            null
        }
    }

    fun register(registerData: RegisterData): User? {
        val encryptedPassword = passwordEncoder.encode(registerData.authRequest.password)
        val user = User(
            name = registerData.name,
            login = registerData.authRequest.login,
            password = encryptedPassword
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