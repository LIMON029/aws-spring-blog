package com.limon.awsspringblog.dto

import com.limon.awsspringblog.domain.posts.Posts

class PostsSaveRequestDto(var title:String, var content:String, var author:String) {
    fun toEntity(): Posts{
        return Posts(title, content, author)
    }
}