package com.limon.awsspringblog.controller

import com.limon.awsspringblog.dto.PostsResponseDto
import com.limon.awsspringblog.dto.PostsSaveRequestDto
import com.limon.awsspringblog.dto.PostsUpdateRequestDto
import com.limon.awsspringblog.service.PostsService
import org.springframework.web.bind.annotation.*

@RestController
class PostsApiController(private val postsService: PostsService) {

    @PostMapping("/api/v1/posts")
    fun save(@RequestBody requestDto: PostsSaveRequestDto): Long? {
        return postsService.save(requestDto)
    }

    @PutMapping("/api/v1/posts/{id}")
    fun update(@PathVariable id:Long, @RequestBody requestDto: PostsUpdateRequestDto): Long? {
        return postsService.update(id, requestDto)
    }

    @GetMapping("/api/v1/posts/{id}")
    fun findById(@PathVariable id:Long): PostsResponseDto {
        return postsService.findById(id)
    }
}