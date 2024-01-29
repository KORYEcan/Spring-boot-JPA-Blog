package com.example.blog1.controller.api;

import com.example.blog1.dto.ResponseDto;
import com.example.blog1.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController:save 호출됨");
        //실제로 DB에 insert를 하고 아래에서 return이 되면 되요.
        return new ResponseDto<Integer>(HttpStatus.OK,1); // 자바 오프젝트를 JSON으로 변환해서 리턴(Jackson)
    }
}
