package com.example.blog1.test;


import com.example.blog1.model.User;
import com.example.blog1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired  //의존성 주입(DI)
    private UserRepository userRepository;

    //localhost:8000/blog/dummy/join(요청)
    //http의 body에 username,password, email데이터를 가지고 (요청)

    @PostMapping("/dummy/join1")
    public String join1(String username, String password, String email) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("email = " + email);

        return "회원가입이 완료되었습니다.";
    }
    @PostMapping("/dummy/join2")
    public String join2(@RequestParam("username") String u, @RequestParam("password")  String p,@RequestParam("email")  String e) {
        System.out.println("username = " + u);
        System.out.println("password = " + p);
        System.out.println("email = " + e);

        return "회원가입이 완료되었습니다.";
    }
    @PostMapping("/dummy/join3")
    public String join3(User user) {    //아주 유용한 기능으로 스프링 Object도 파싱해서 데이터값을 넣어준다.
        System.out.println("user.getId() = " + user.getId());  // 자동으로 들어가는 값
        System.out.println("user.getCreateDate() = " + user.getCreateDate()); // 자바에서 생성시간을 자동으로 넣어줌
        System.out.println("user.getRole() = " + user.getRole()); //
        System.out.println("user.getUsername() = " + user.getUsername()); //
        System.out.println("user.getPassword() = " + user.getPassword()); //
        System.out.println("user.getEmail() = " + user.getEmail());       //

        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

}
