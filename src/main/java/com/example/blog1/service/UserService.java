package com.example.blog1.service;

import com.example.blog1.model.RoleType;
import com.example.blog1.model.User;
import com.example.blog1.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service   //스프링 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 . Ioc를 해준다.
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void save(User user) {  //회원 가입
        String rawPassword= user.getPassword(); //1234 원문
        String encPassword = encoder.encode(rawPassword); //해쉬
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

}
