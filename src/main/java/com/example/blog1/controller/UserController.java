package com.example.blog1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥 주소가 / 이면 index.jsp 허용
//static이하에 있는  /js/** , /css/**, /image/**

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")  //회원 가입
     public String joinForm() {
        return "user/joinForm";
     }

    @GetMapping("/auth/loginForm")  //로그인
     public String loginForm() {
        System.out.println("loginForm 불렀습니다.");
        return "user/loginForm";
     }


}
