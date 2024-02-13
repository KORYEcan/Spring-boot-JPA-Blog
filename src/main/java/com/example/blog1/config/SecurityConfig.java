package com.example.blog1.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration      //빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
public class SecurityConfig    {

    @Bean  //Ioc
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable() //csrf 토큰 비활성화 (테스트 시 걸어두는게 좋음)
                        .authorizeHttpRequests()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/","/auth/**","/js/**","/css/**","/image/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/auth/loginForm");




        return http.build();
    }


}
