package com.limon.awsspringblog.controller

import com.limon.awsspringblog.domain.posts.PostsRepository
import com.limon.awsspringblog.dto.PostsSaveRequestDto
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PostsApiControllerTest(@LocalServerPort private val port: Int) {
    @Autowired
    private lateinit var restTemplate: TestRestTemplate
    @Autowired
    private lateinit var postsRepository: PostsRepository

    @AfterEach
    fun tearDown() {
        postsRepository.deleteAll()
    }

    @Test
    fun save() {
        val title = "title"
        val content = "content"
        val author = "author"

        val requestDto = PostsSaveRequestDto(title, content, author)
        val url = "http://localhost:${port}/api/v1/posts"

        val responseEntity = restTemplate.postForEntity(url, requestDto, Long::class.java)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        val all = postsRepository.findAll()
        assertThat(all[0].getTitle()).isEqualTo(title)
        assertThat(all[0].getContent()).isEqualTo(content)
    }

    @Test
    fun update() {
        val new_title = "title2"
        val new_content = "content2"
    }
}