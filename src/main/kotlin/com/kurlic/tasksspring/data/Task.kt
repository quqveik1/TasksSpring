package com.kurlic.tasksspring.data

import jakarta.persistence.*

@Entity
@Table(name = "tasks")
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var userId: Long = 0,
    var title: String = "",
    var description: String = "",
)
