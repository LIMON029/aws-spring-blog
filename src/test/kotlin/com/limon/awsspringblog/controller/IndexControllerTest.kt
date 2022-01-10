package com.limon.awsspringblog.controller

import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class IndexControllerTest {
    @Autowired
    private lateinit var restTemplate:TestRestTemplate

    @Test
    fun index() {
        val body = this.restTemplate.getForObject("/", String::class.java)

        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스")
    }
}