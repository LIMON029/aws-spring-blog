package com.limon.awsspringblog.controller

import com.limon.awsspringblog.config.auth.SecurityConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [HelloController::class],
excludeFilters = [ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = [SecurityConfig::class])])
internal class HelloControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    @WithMockUser(roles = ["USER"])
    fun hello_리턴() {
        val hello = "hello"

        mvc.perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(hello))
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun hello_dto_리턴() {
        val name = "hello"
        val amount = 1000

        mvc.perform(get("/hello/dto").param("username", name).param("amount", amount.toString()))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.username").value(name))
            .andExpect(jsonPath("$.amount").value(amount.toString()))
    }
}