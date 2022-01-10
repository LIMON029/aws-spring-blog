package com.limon.awsspringblog.domain.posts

import com.limon.awsspringblog.domain.BaseTimeEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Posts: BaseTimeEntity{
    constructor()
    constructor(title: String, content: String, author: String) : this() {
        this.title = title
        this.content = content
        this.author = author
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id:Long ?= 0L

    @Column(length = 500, nullable = false)
    private lateinit var title: String

    @Column(columnDefinition = "TEXT", nullable = false)
    private var content: String ?= "TEXT"
    private lateinit var author: String

    fun getId():Long? = id
    fun getTitle():String = title
    fun getContent():String? = content
    fun getAuthor():String = author

    fun update(id:Long){
        this.id = id
    }

    fun update(title:String, content:String){
        this.title = title
        this.content = content
    }

    override fun toString(): String {
        return "Posts(id=$id, title:$title, author:$author,\ncontent:$content)"
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun equals(other: Any?): Boolean {
        if(this === other)  return true
        if(javaClass != other?.javaClass)   return false

        other as Posts
        if (id != other.id)   return false

        return true
    }
}