package com.limon.awsspringblog.controller

import com.limon.awsspringblog.dto.HelloResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/hello/dto")
    fun helloDto(
        @RequestParam("name") name: String,
        @RequestParam("amount") amount: Int
    ): HelloResponseDto {
        return HelloResponseDto(name, amount)
    }
}