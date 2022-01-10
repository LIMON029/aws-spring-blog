package com.limon.awsspringblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AwsSpringBlogApplication

fun main(args: Array<String>) {
	runApplication<AwsSpringBlogApplication>(*args)
}
