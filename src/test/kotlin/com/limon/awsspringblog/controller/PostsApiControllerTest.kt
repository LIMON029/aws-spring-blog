package com.limon.awsspringblog.controller

import com.limon.awsspringblog.domain.posts.Posts
import com.limon.awsspringblog.domain.posts.PostsRepository
import com.limon.awsspringblog.dto.PostsSaveRequestDto
import com.limon.awsspringblog.dto.PostsUpdateRequestDto
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

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
        val savedPosts = postsRepository.save(Posts(title = "title", content = "content", author = "author"))
        val new_title = "title2"
        val new_content = "content2"
        val updatedId = savedPosts.getId()
        val requestDto = PostsUpdateRequestDto(new_title, new_content)
        val url = "http://localhost:${port}/api/v1/posts/$updatedId"
        val requestEntity = HttpEntity<PostsUpdateRequestDto>(requestDto)

        val responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long::class.java)

        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        val all = postsRepository.findAll()
        assertThat(all[0].getTitle()).isEqualTo(new_title)
        assertThat(all[0].getContent()).isEqualTo(new_content)
    }
}