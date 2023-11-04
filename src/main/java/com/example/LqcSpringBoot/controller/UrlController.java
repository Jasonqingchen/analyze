package com.example.LqcSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UrlController {
    /**
     * 跳转连接（登录页面）
     */
    @RequestMapping("/login")
    public String kunyueyeUrl () {
        return "index";
    }
}
