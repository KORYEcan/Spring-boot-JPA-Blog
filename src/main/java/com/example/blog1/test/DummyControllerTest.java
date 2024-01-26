package com.example.blog1.test;


import com.example.blog1.model.RoleType;
import com.example.blog1.model.User;
import com.example.blog1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController //HTML이 아닌 데이터를 리턴해주는 컨트롤러
public class DummyControllerTest {

    @Autowired  //의존성 주입(DI)
    private UserRepository userRepository;



    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
            User user = userRepository.findById(id).orElseThrow(()-> {
                return new IllegalArgumentException("삭제할 엔티티가 존재하지 않습니다. id="+id);
            });

            userRepository.deleteById(id);
            return "삭제되었습니다. id=" + id;

    }

    //save()는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
    //email, password
    @Transactional //메서드 종료시에 자동 commit이 됨.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json 데이터를 요청- > java object(MessageConverter의 Jackson라이브러리가 변환해줘서 받아줌.
        System.out.println("id = " + id);
        System.out.println("password:" + requestUser.getPassword());
        System.out.println("email:" + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("수정에 실패하였습니다");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user);
         //더티 체킹 ->
        return user;
    }

    // http://localhost:8000/blog/dummy/user
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }


    //한페이지당 2건에 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2,sort = "id", direction =Sort.Direction.DESC)Pageable pageable) {
    Page<User> pagingUser= userRepository.findAll(pageable);
    List<User> users = pagingUser.getContent();
    return users;
   }

    //http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")   //{id} 주소로 파라미터를 전달 받을 수 있음
    public User detail(@PathVariable int id) {
        // user/4을 찾다가 DB에서 못찾으면 user가 null이 되고 return 또한 null발생해 프로그램 문제을 발생
        //Optional로 User객체를 감싸서 가져오고 null인지 판단해서 return을 시켜줌
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 사용자가 없습니다. id:"+id);
            }
        });
       //@RestController
        return user;
     }

    //localhost:8000/blog/dummy/join(요청)
    //http의 body에 username,password, email데이터를 가지고 (요청)

    @PostMapping("/dummy/join1")
    public String join1(String username, String password, String email) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("email = " + email);

        return "회원가입이 완료되었습니다1.";
    }
    @PostMapping("/dummy/join2")
    public String join2(@RequestParam("username") String u, @RequestParam("password")  String p,@RequestParam("email")  String e) {
        System.out.println("username = " + u);
        System.out.println("password = " + p);
        System.out.println("email = " + e);

        return "회원가입이 완료되었습니다2.";
    }
    @PostMapping("/dummy/join3")
    public String join3(User user) {    //아주 유용한 기능으로 스프링 Object도 파싱해서 데이터값을 넣어준다.
        System.out.println("user.getId() = " + user.getId());  // 자동으로 들어가는 값
        System.out.println("user.getCreateDate() = " + user.getCreateDate()); // 자바에서 생성시간을 자동으로 넣어줌
        System.out.println("user.getRole() = " + user.getRole()); //
        System.out.println("user.getUsername() = " + user.getUsername()); //
        System.out.println("user.getPassword() = " + user.getPassword()); //
        System.out.println("user.getEmail() = " + user.getEmail());       //

        user.setRole(RoleType.USER);

        userRepository.save(user);
        return "회원가입이 완료되었습니다3.";
    }

}
