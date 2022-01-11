package com.limon.awsspringblog.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.limon.awsspringblog.domain.posts.Posts
import com.limon.awsspringblog.domain.posts.PostsRepository
import com.limon.awsspringblog.dto.PostsSaveRequestDto
import com.limon.awsspringblog.dto.PostsUpdateRequestDto
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PostsApiControllerTest(@LocalServerPort private val port: Int) {
    @Autowired
    private lateinit var restTemplate: TestRestTemplate
    @Autowired
    private lateinit var postsRepository: PostsRepository
    @Autowired
    private lateinit var context: WebApplicationContext

    private lateinit var mvc:MockMvc

    @AfterEach
    fun tearDown() {
        postsRepository.deleteAll()
    }

    @BeforeEach
    fun setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply<DefaultMockMvcBuilder?>(springSecurity()).build()
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun save() {
        val title = "title"
        val content = "content"
        val author = "author"

        val requestDto = PostsSaveRequestDto(title, content, author)
        val url = "http://localhost:${port}/api/v1/posts"

        mvc.perform(MockMvcRequestBuilders.post(url)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(MockMvcResultMatchers.status().isOk)

        val all = postsRepository.findAll()
        assertThat(all[0].getTitle()).isEqualTo(title)
        assertThat(all[0].getContent()).isEqualTo(content)
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun update() {
        val savedPosts = postsRepository.save(Posts(title = "title", content = "content", author = "author"))
        val new_title = "title2"
        val new_content = "content2"
        val updatedId = savedPosts.getId()
        val requestDto = PostsUpdateRequestDto(new_title, new_content)
        val url = "http://localhost:${port}/api/v1/posts/$updatedId"

        mvc.perform(MockMvcRequestBuilders.put(url)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(MockMvcResultMatchers.status().isOk)

        val all = postsRepository.findAll()
        assertThat(all[0].getTitle()).isEqualTo(new_title)
        assertThat(all[0].getContent()).isEqualTo(new_content)
    }
}