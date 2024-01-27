package com.example.blog1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/joinForm")  //회원 가입
     public String joinForm() {

        return "user/joinForm";
     }
    @GetMapping("/user/loginForm")  //로그인
     public String loginForm() {

        return "user/loginForm";
     }


}
