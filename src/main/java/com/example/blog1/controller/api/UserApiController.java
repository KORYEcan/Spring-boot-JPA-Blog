package com.example.blog1.controller.api;

import com.example.blog1.config.auth.PrincipalDetail;
import com.example.blog1.dto.ResponseDto;
import com.example.blog1.model.RoleType;
import com.example.blog1.model.User;
import com.example.blog1.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {   //username , password, email
        System.out.println("UserApiController:save 호출됨");
        //실제로 DB에 insert를 하고 아래에서 return이 되면 되요.
        user.setRole(RoleType.USER);
        userService.save(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); // 자바 오프젝트를 JSON으로 변환해서 리턴(Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){  //key= value , x-www-form-urlencoded
        userService.updateForm(user);
        //여기서는 트랜잭션이 종료되기 떄문에 DB에 값은 변경이 됐음
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.

        //세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }






}
