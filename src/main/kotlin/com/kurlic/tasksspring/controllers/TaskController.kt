package com.kurlic.tasksspring.controllers

import com.kurlic.tasksspring.auth.AuthRequest
import com.kurlic.tasksspring.auth.AuthService
import com.kurlic.tasksspring.data.Task
import com.kurlic.tasksspring.data.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController {
    @Autowired
    private lateinit var authService: AuthService
    @Autowired
    private lateinit var taskRepository: TaskRepository

    data class TaskRequest(
        val task: Task,
        val authRequest: AuthRequest
    )

    @PostMapping("/add")
    fun addTask(@RequestBody taskRequest: TaskRequest): String {
        val user = authService.authenticate(taskRequest.authRequest) ?: return "No such user"
        taskRequest.task.id = null
        try {
            taskRequest.task.userId = user.id!!
        }
        catch (e: Exception) {
            println("Error in adding task $taskRequest.task")
            return "Error in adding task"
        }
        taskRepository.save(taskRequest.task)
        return "ok"
    }
}