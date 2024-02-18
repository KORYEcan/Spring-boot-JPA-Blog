package com.example.blog1.test;

import org.springframework.web.bind.annotation.*;


//사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
//@RestController
//public class HttpControllerTest {
// @GetMapping("/http/lombok")
//    public String lombokTest() {
//        Member m1 = Member.builder().id(1).username("ssar").password("1234").email("ssar@nate.com")
//                .build();
//        System.out.println(TAG + "getter" + m1.getId());
////        m1.setId(5000);
////        System.out.println(TAG + "setter:"+m1.getId());
//     System.out.println(m1.getUsername());
//        return "lombok test완료";
//    }
//
//
//    private static final String TAG = "HttpControllerTest";
//
//    //http://localhost:8080/http/get?id=1&user=cos
//    //http://localhost:8080/http/get?id=1&username=two&emai=ssar@nate.com-> 쿼리스트링과 member클래스 변수명일치하면 쿼리스트링값을 넣어줌
//    @GetMapping("/http/get")
//     public String getTest(Member m){
//
//        return "get 요청 :" +m.getId()+","+m.getUsername()+","+m.getEmail();
//    }
//    //http://localhost:8080/http/post  --> 인터넷 브라우저 요청은 무조건 get 요청밖에 할수없다.
//    @PostMapping("/http/post") //text/plain, application/json
//     public String postTest(@RequestBody Member m){
//        return "post 요청 :"+m.getId()+","+m.getUsername()+","+m.getEmail();
//    }
//    //http://localhost:8080/http/put
//    @PutMapping("/http/put")
//     public String putTest(@RequestBody Member m){
//        return "put 요청 :"+m.getId()+","+m.getPassword()+","+m.getUsername()+","+m.getEmail();
//    }
//    //http://localhost:8080/http/delete
//    @DeleteMapping("/http/delete")
//     public String deleteTest(){
//        return "delete 요청";
//    }
//
//}
