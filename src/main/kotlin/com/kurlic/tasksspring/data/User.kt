package com.kurlic.tasksspring.data

import jakarta.persistence.*


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String? = null,
    @Column(unique = true)
    var login: String? = null,
    var password: String? = null,
)