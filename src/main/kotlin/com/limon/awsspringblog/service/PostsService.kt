package com.limon.awsspringblog.service

import com.limon.awsspringblog.domain.posts.Posts
import com.limon.awsspringblog.domain.posts.PostsRepository
import com.limon.awsspringblog.dto.PostsListResponseDto
import com.limon.awsspringblog.dto.PostsResponseDto
import com.limon.awsspringblog.dto.PostsSaveRequestDto
import com.limon.awsspringblog.dto.PostsUpdateRequestDto
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class PostsService(val postsRepository:PostsRepository, val jdbcTemplate: JdbcTemplate) {

    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long? {
        return postsRepository.save(requestDto.toEntity()).getId()
    }

    @Transactional
    fun update(id: Long, requestDto: PostsUpdateRequestDto): Long {
        val posts: Posts = postsRepository.findById(id).orElseThrow {IllegalArgumentException()}
        posts.update(title = requestDto.title, content = requestDto.content)
        return id
    }

    fun findById(id: Long): PostsResponseDto {
        val entity: Posts = postsRepository.findById(id).orElseThrow {IllegalArgumentException()}
        return PostsResponseDto(entity)
    }

    @Transactional(readOnly = true)
    fun findAllAsc():List<PostsListResponseDto> {
        return postsRepository.findAllAsc().stream()
            .map{posts -> PostsListResponseDto(posts)}
            .collect(Collectors.toList())
    }

    @Transactional
    fun delete(id:Long) {
        val posts:Posts = postsRepository.findById(id).orElseThrow {IllegalArgumentException()}
        postsRepository.delete(posts)
        afterDelete()
    }

    @Transactional
    private fun afterDelete() {
        val postsList: List<Posts> = postsRepository.findAllAsc()
        val max = postsList.size
        for(i: Int in 1..max) {
            if(postsList[i-1].getId()!=i.toLong()){
                jdbcTemplate.update("update posts set id=? where id=?", i.toLong(), postsList[i-1].getId())
            }
        }
        val sql = "ALTER TABLE posts auto_increment=${max+1}"
        jdbcTemplate.execute(sql)
    }
}