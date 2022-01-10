package com.limon.awsspringblog.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfig:WebSecurityConfigurerAdapter() {
    // 테스트를 위해 잠시 권한 모두 끔
    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers("/**")
    }
}