package com.limon.awsspringblog.domain.posts

import org.springframework.data.jpa.repository.JpaRepository

interface PostsRepository: JpaRepository<Posts, Long> {
}