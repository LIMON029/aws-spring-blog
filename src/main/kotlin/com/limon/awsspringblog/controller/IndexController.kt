package com.limon.awsspringblog.controller

import com.limon.awsspringblog.config.auth.LoginUser
import com.limon.awsspringblog.config.auth.dto.SessionUser
import com.limon.awsspringblog.service.PostsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@Controller
class IndexController(private val postsService: PostsService) {
    @GetMapping("/")
    fun index(model: Model, @LoginUser user:SessionUser?): String {
        model.addAttribute("posts", postsService.findAllAsc())
        if(user!=null){
            model.addAttribute("loginName", user.getName())
        }
        return "index"
    }

    @GetMapping("/posts/save")
    fun postsSave(model: Model, @LoginUser user:SessionUser?):String {
        model.addAttribute("loginName", user?.getName())
        return "posts-save"
    }

    @GetMapping("/posts/update/{id}")
    fun postsUpdate(@PathVariable id:Long, model:Model):String {
        val dto = postsService.findById(id)
        model.addAttribute("post", dto)
        return "posts-update"
    }
}