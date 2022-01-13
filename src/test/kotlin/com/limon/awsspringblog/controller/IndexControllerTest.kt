package com.limon.awsspringblog.controller

import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class IndexControllerTest {
    @Autowired
    private lateinit var restTemplate:TestRestTemplate

    @Test
    fun index() {
        val body = this.restTemplate.getForObject("/", String::class.java)

        assertThat(body).contains("게시글 목록")
    }
}