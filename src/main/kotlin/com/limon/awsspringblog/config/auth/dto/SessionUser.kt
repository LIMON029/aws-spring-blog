package com.limon.awsspringblog.config.auth.dto

import com.limon.awsspringblog.domain.user.User
import java.io.Serializable

class SessionUser(user: User) : Serializable {
    private val name: String
    private val email: String
    private val picture: String

    init {
        this.name = user.getName()
        this.email = user.getEmail()
        this.picture = user.getPicture()
    }

    fun getName():String = name
    fun getEmail():String = email
    fun getPicture():String = picture

    override fun toString(): String {
        return "SessionUser(name=$name, email=$email)"
    }
}