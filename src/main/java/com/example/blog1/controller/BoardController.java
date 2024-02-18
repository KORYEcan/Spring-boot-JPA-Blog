package com.example.blog1.controller;

import com.example.blog1.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

   //@AuthenticationPrincipal PrincipalDetail principal
     @GetMapping({"","/"})
    public String index( ) {  //컨트롤러에서 세션을 어떻게 찾을까?
         // /WEB-INF/views/index.jsp
         return "index";
    }

   //USER권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

}
