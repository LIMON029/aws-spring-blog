package com.limon.awsspringblog.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class PostsRepositoryTest {
    @Autowired
    private lateinit var repository: PostsRepository

    @AfterEach
    fun tearDown() {
        repository.deleteAll()
    }

    @Test
    fun 게시글저장_불러오기() {
        val title = "테스트 게시글"
        val content = "테스트 본문"
        val author = "koko@gmail.com"

        repository.save(Posts(title, content, author))

        val postList = repository.findAll()

        val post = postList[0]
        println(post)
        assertThat(post.getTitle()).isEqualTo(title)
        assertThat(post.getContent()).isEqualTo(content)
    }
}