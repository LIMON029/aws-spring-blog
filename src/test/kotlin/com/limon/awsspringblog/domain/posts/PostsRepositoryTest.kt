package com.limon.awsspringblog.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class PostsRepositoryTest {
    @Autowired
    private lateinit var repository: PostsRepository

    @AfterEach
    fun tearDown() {
        repository.deleteAll()
    }

    @Test
    fun load_post() {
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

    @Test
    fun baseTimeEntity_save() {
        val now = LocalDateTime.of(2019,6,4,0,0,0,0)

        repository.save(Posts(title = "title", content = "content", author = "author"))

        val postsList = repository.findAll()
        val post = postsList[0]

        println(">>>>>> createdDate = ${post.createdDate}, modifiedDate = ${post.modifiedDate}")

        assertThat(post.createdDate).isAfter(now)
        assertThat(post.modifiedDate).isAfter(now)
    }
}