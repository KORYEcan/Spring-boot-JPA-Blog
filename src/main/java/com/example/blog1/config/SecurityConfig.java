package com.example.blog1.config;

import com.example.blog1.config.auth.PrincipalDetailService;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration      //빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
public class SecurityConfig    {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean  //Ioc
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }


    //시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
    // 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    //같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교 할수 있음

//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(principalDetailService).passwordEncoder(encode());
//    }



    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable() //csrf 토큰 비활성화 (테스트 시 걸어두는게 좋음)
                        .authorizeHttpRequests()
                        .requestMatchers("/**","/auth/**","/js/**","/css/**","/image/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/auth/loginForm")
                        .loginProcessingUrl("/auth/loginProc")//스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채고 대신 로그인 해준다.
                        .defaultSuccessUrl("/");  //정상적으로 요청이 완료-> "/" 홈으로




        return http.build();
    }


}
