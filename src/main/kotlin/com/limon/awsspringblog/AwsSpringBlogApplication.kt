package com.limon.awsspringblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
class AwsSpringBlogApplication

fun main(args: Array<String>) {
	runApplication<AwsSpringBlogApplication>(*args)
}
