package com.limon.awsspringblog.config.auth

import com.limon.awsspringblog.config.auth.dto.SessionUser
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpSession
@Component
class LoginUserArgumentResolver(val httpSession: HttpSession): HandlerMethodArgumentResolver {
    // 컨트롤러 메서드의 특정파라미터를 지원하는지 판단
    // 현재 : 파라미터에 @LoginUser가 붙어있고, 파라미터의 클래스타입이 SessionUser.class인 경우 true
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val isLoginUserAnnotation: Boolean = parameter.getParameterAnnotation(LoginUser::class.java) != null
        val isUserClass: Boolean = SessionUser::class.java == parameter.parameterType
        return isLoginUserAnnotation && isUserClass
    }

    // 파라미터를 전달한 객체 생성
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        return httpSession.getAttribute("user")
    }
}