package com.limon.awsspringblog.dto

import com.limon.awsspringblog.domain.posts.Posts
import java.time.LocalDateTime

class PostsListResponseDto(entity: Posts) {
    var id: Long
    var title: String
    var author: String
    var modifiedDate: LocalDateTime
    init {
        id = entity.getId() ?: 0L
        title = entity.getTitle()
        author = entity.getAuthor()
        modifiedDate = entity.modifiedDate
    }
}