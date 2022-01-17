package com.limon.awsspringblog.config.auth

import com.limon.awsspringblog.domain.user.Role
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
@Configuration
class SecurityConfig(private val customOAuth2UserService: CustomOAuth2UserService):WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http?.let {
            with(it){
                csrf().disable().headers().frameOptions().disable() // h2 console사용을 위해 옵션들 disable
                    .and()
                authorizeRequests() // URL 별 권한 관리
                    .antMatchers(
                    "/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile"
                    ).permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name).anyRequest().authenticated()
                    .and()
                logout().logoutSuccessUrl("/").invalidateHttpSession(true).clearAuthentication(true)
                    .and()
                oauth2Login().userInfoEndpoint().userService(customOAuth2UserService)   // 소셜로그인 후속 조치(userService)
            }
        }
    }
}