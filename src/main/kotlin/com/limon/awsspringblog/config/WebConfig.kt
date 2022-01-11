package com.limon.awsspringblog.config

import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*
import javax.servlet.SessionTrackingMode

@Configuration
class WebConfig(private val loginUserArgumentResolver: HandlerMethodArgumentResolver): WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(loginUserArgumentResolver)
    }

    @Bean
    fun clearJsession():ServletContextInitializer {
        return ServletContextInitializer {
            it.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE))
            val sessionCookieConfig = it.sessionCookieConfig
            sessionCookieConfig.isHttpOnly = true
        }
    }
}