package com.example.blog1.controller.api;

import com.example.blog1.dto.ResponseDto;
import com.example.blog1.model.RoleType;
import com.example.blog1.model.User;
import com.example.blog1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {   //username , password, email
        System.out.println("UserApiController:save 호출됨");
        //실제로 DB에 insert를 하고 아래에서 return이 되면 되요.
        user.setRole(RoleType.USER);
        userService.save(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); // 자바 오프젝트를 JSON으로 변환해서 리턴(Jackson)
    }

   /* @PostMapping("/api/user/login")   //스프링 시큐리티 이용해서 로그인
    public ResponseDto<Integer> login(@RequestBody User user , HttpSession session) {
        System.out.println("UserApiController:login 호출됨");
        User principal=userService.login(user);   //principal(접근주체)

        if (principal != null) {
            session.setAttribute("principal", principal);
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); // 자바 오프젝트를 JSON으로 변환해서 리턴(Jackson)
    }*/





}
