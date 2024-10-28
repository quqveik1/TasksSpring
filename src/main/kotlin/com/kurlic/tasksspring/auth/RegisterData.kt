package com.kurlic.tasksspring.auth

data class RegisterData(
    val authRequest: AuthRequest,
    val name: String
)
