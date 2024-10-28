package com.kurlic.tasksspring.data

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findUserByLogin(login: String): User?
}