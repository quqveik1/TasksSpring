package com.kurlic.tasksspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TasksSpringApplication

fun main(args: Array<String>) {
    runApplication<TasksSpringApplication>(*args)
}
