package com.limon.awsspringblog.dto

import com.limon.awsspringblog.domain.posts.Posts

class PostsResponseDto(entity: Posts) {
    private var id: Long
    private var title: String
    private var content: String?
    private var author: String
    init {
        id = entity.getId() ?: 0L
        title = entity.getTitle()
        content = entity.getContent()
        author = entity.getAuthor()
    }
}