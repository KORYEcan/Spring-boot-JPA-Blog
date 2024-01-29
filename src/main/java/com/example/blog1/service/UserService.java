package com.example.blog1.service;

import com.example.blog1.model.User;
import com.example.blog1.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service   //스프링 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 . Ioc를 해준다.
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int save(User user) {  //회원 가입
        try {
            userRepository.save(user);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("UserService: 회원가입() :" + e.getMessage());
        }
        return -1;
    }

}
