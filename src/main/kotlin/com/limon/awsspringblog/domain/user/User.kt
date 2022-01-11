package com.limon.awsspringblog.domain.user

import com.limon.awsspringblog.domain.BaseTimeEntity
import javax.persistence.*

// id, name, email, picture, role

@Entity
class User: BaseTimeEntity {
    constructor()
    constructor(name: String, email:String, picture:String, role:Role){
        this.name = name
        this.email = email
        this.picture = picture
        this.role = role
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id:Long ?= 0L

    @Column(nullable = false)
    private lateinit var name: String

    @Column(nullable = false)
    private lateinit var email: String

    @Column
    private lateinit var picture: String

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private lateinit var role: Role

    fun getId():Long? = id
    fun getName():String = name
    fun getEmail():String = email
    fun getPicture():String = picture
    fun getRole():Role = role
    fun getRoleKey():String = role.key

    fun update(name:String, picture:String): User {
        this.name = name
        this.picture = picture

        return this
    }

    override fun toString(): String {
        return "User(id=$id, name=$name, email=$email, role=$role)"
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun equals(other: Any?): Boolean {
        if(this === other)  return true
        if(javaClass != other?.javaClass)   return false

        other as User
        if (id != other.id)   return false

        return true
    }
}