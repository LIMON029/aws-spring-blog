package com.limon.awsspringblog.dto

import com.limon.awsspringblog.domain.posts.Posts

class PostsResponseDto(entity: Posts) {
    var id: Long
    var title: String
    var content: String?
    var author: String
    init {
        id = entity.getId() ?: 0L
        title = entity.getTitle()
        content = entity.getContent()
        author = entity.getAuthor()
    }

    override fun toString(): String {
        return "Posts(id:$id, title:$title, author:$author, content:$content)"
    }
}