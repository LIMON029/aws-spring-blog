package com.limon.awsspringblog.dto

import com.limon.awsspringblog.domain.posts.Posts
import java.sql.Timestamp

class PostsListResponseDto(entity: Posts) {
    var id: Long
    var title: String
    var author: String
    var modifiedDate: Timestamp
    init {
        id = entity.getId() ?: 0L
        title = entity.getTitle()
        author = entity.getAuthor()
        modifiedDate = entity.getModifiedDate()
    }
}