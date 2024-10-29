package com.kurlic.tasksspring

import com.fasterxml.jackson.databind.ObjectMapper
import com.kurlic.tasksspring.auth.AuthRequest
import com.kurlic.tasksspring.auth.RegisterData
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class AuthTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `test auth controller`() {
        val authData = AuthRequest("newUserLogin", "newPassword")
        val registerData = RegisterData(authData, "NewUser")
        val jsonRegister = objectMapper.writeValueAsString(registerData)


        mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRegister)
        ).andExpect(status().isOk)
        mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRegister)
        ).andExpect(status().isConflict)

        val jsonLogin = objectMapper.writeValueAsString(authData)
        mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonLogin)
        ).andExpect(status().isOk)
        mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonLogin)
        ).andExpect(status().isOk)
    }
}