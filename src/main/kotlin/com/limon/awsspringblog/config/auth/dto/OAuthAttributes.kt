package com.limon.awsspringblog.config.auth.dto

import com.limon.awsspringblog.domain.user.Role
import com.limon.awsspringblog.domain.user.User

@Suppress("UNCHECKED_CAST")
class OAuthAttributes{
    private lateinit var attributes: Map<String, Any>
    private lateinit var nameAttributeKey: String
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var picture: String

    constructor()
    constructor(attributes: Map<String, Any>,
                nameAttributeKey: String,
                name: String,
                email: String,
                picture: String) {
        this.attributes = attributes
        this.nameAttributeKey = nameAttributeKey
        this.name = name
        this.email = email
        this.picture = picture
    }

    fun of(registrationId: String, userNameAttributeName: String, attributes: Map<String, Any>):OAuthAttributes{
        if(registrationId == "naver") return ofNaver("id", attributes)
        return ofGoogle(userNameAttributeName, attributes)
    }

    private fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
        return OAuthAttributes(
            attributes = attributes,
            nameAttributeKey = userNameAttributeName,
            name = attributes["name"] as String,
            email = attributes["email"] as String,
            picture = attributes["picture"] as String)
    }

    private fun ofNaver(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
        val response: Map<String, Any> = attributes["response"] as Map<String, Any>
        return OAuthAttributes(
            attributes = response,
            nameAttributeKey = userNameAttributeName,
            name = response["name"] as String,
            email = response["email"] as String,
            picture = response["profile_image"] as String)
    }

    fun getAttributes(): Map<String, Any> = attributes
    fun getNameAttributeKey(): String = nameAttributeKey
    fun getName(): String = name
    fun getEmail(): String = email
    fun getPicture(): String = picture

    fun toEntity(): User {
        return User(name = name, email = email, picture = picture, role = Role.GUEST)
    }
}