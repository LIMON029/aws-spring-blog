package com.limon.awsspringblog.service

import com.limon.awsspringblog.domain.posts.Posts
import com.limon.awsspringblog.domain.posts.PostsRepository
import com.limon.awsspringblog.dto.PostsResponseDto
import com.limon.awsspringblog.dto.PostsSaveRequestDto
import com.limon.awsspringblog.dto.PostsUpdateRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostsService(val postsRepository:PostsRepository) {

    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long? {
        return postsRepository.save(requestDto.toEntity()).getId()
    }

    @Transactional
    fun update(id: Long, requestDto: PostsUpdateRequestDto): Long {
        val posts: Posts = postsRepository.findById(id).orElseThrow()
        posts.update(title = requestDto.title, content = requestDto.content)
        return id
    }

    fun findById(id: Long): PostsResponseDto {
        val entity: Posts = postsRepository.findById(id).orElseThrow()
        return PostsResponseDto(entity)
    }
}