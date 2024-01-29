package com.example.blog1.controller.api;

import com.example.blog1.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public int save(@RequestBody User user) {
        System.out.println("UserApiController:save 호출됨");
        return 1;
    }
}