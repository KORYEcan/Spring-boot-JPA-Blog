package com.example.blog1.test;

import org.springframework.web.bind.annotation.*;


//사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    //http://localhost:8080/http/get?id=1&user=cos
    @GetMapping("/http/get")
     public String getTest(Member m){
        return "get 요청 :" +m.getId()+","+m.getUsername()+","+m.getPassword();
    }
    //http://localhost:8080/http/post  --> 인터넷 브라우저 요청은 무조건 get 요청밖에 할수없다.
    @PostMapping("/http/post")
     public String postTest(){
        return "post 요청";
    }
    //http://localhost:8080/http/put
    @PutMapping("/http/put")
     public String putTest(){
        return "put 요청";
    }
    //http://localhost:8080/http/delete
    @DeleteMapping("/http/delete")
     public String deleteTest(){
        return "delete 요청";
    }

}
