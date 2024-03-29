package com.example.blog1.controller;

import com.example.blog1.model.KakaoProfile;
import com.example.blog1.model.OAuthToken;
import com.example.blog1.model.User;
import com.example.blog1.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥 주소가 / 이면 index.jsp 허용
//static이하에 있는  /js/** , /css/**, /image/**

@Controller
public class UserController {

    @Value("${cos.key}")
    private String cosKey;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth/joinForm")  //회원 가입
     public String joinForm() {
        return "user/joinForm";
     }

    @GetMapping("/auth/loginForm")  //로그인
     public String loginForm() {
        System.out.println("loginForm 불렀습니다.");
        return "user/loginForm";
     }

     @GetMapping("/user/updateForm")
     public String updateForm() {
         return "user/updateForm";
     }

     @GetMapping("/auth/kakao/callback")
     public String kakaoCallback(String code , Model model) {  //Data를 리턴해주는 컨트롤러 함수

         //POST방식으로 key=value 데이터를 요청(카카오쪽으로)
         //Retorofit2
         //OkHttp
         //RestTemplate

         RestTemplate rt = new RestTemplate();

         //HttpHeader 오브젝트 생성
         HttpHeaders headers = new HttpHeaders();
         headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

         //HttpBody 오브젝트 생성
         MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
         params.add("grant_type","authorization_code");
         params.add("client_id","7b6eb232a600b12d594cb29201a58923");
         params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
         params.add("code",code);

         //HtttpHeader와 HttpBody를 하나의 오브젝트에 담기
         HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

         //Http 요청하기 - Post방식으로 - 그리고 Resonse 변수의 응답 받음
         ResponseEntity<String> response = rt.exchange(
                 "https://kauth.kakao.com/oauth/token",
                 HttpMethod.POST,
                 kakaoTokenRequest,
                 String.class
         );

         //GSON , JSON Simple, ObjectMapper
          ObjectMapper objectMapper = new ObjectMapper();
          OAuthToken oAuthToken = null;
         try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
         } catch (JsonMappingException e) {
             e.printStackTrace();
         }catch (JsonProcessingException e) {
             e.printStackTrace();
         }

         System.out.println("카카오 엑세스 토큰:"+oAuthToken.getAccess_token());

         RestTemplate rt2 = new RestTemplate();

         //HttpHeader 오브젝트 생성
         HttpHeaders headers2 = new HttpHeaders();
         headers2.add("Authorization","Bearer "+oAuthToken.getAccess_token());
         headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

         //HtttpHeader와 HttpBody를 하나의 오브젝트에 담기
         HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

         //Http 요청하기 - Post방식으로 - 그리고 Resonse 변수의 응답 받음
         ResponseEntity<String> response2 = rt2.exchange(
                 "https://kapi.kakao.com/v2/user/me",
                 HttpMethod.POST,
                 kakaoProfileRequest2,
                 String.class
         );

         System.out.println(response2.getBody());

         ObjectMapper objectMapper2 = new ObjectMapper();
         KakaoProfile kakaoProfile = null;

         try {
             kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
         } catch (JsonMappingException e) {
             e.printStackTrace();
         }catch (JsonProcessingException e) {
             e.printStackTrace();
         }

          //User 오브젝트 : username, password, email
         System.out.println("카카오 아이디(번호): "+ kakaoProfile.getId());
         System.out.println("카카오 이메일 : "+ kakaoProfile.getKakao_account().getEmail());


         ///UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘

         User kakaoUser = User.builder()
                 .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
                 .password(cosKey)
                 .email(kakaoProfile.getKakao_account().getEmail())
                 .oauth("kakao")
                 .build();


         //가입자 혹은 비가입자 체크 해서 처리
         User originUser =  userService.findUser(kakaoUser.getUsername());


         if (originUser.getUsername() == null){
            System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다.");
            userService.save(kakaoUser);
        }

         System.out.println(" 자동 로그인을 진행합니다.");

         //로그인 처리
         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
       SecurityContextHolder.getContext().setAuthentication(authentication);


          Authentication storedAuthentication = SecurityContextHolder.getContext().getAuthentication();
         if (storedAuthentication != null) {
             System.out.println("Authentication 객체가 정상적으로 저장되었습니다.");
             System.out.println("Principal: " + storedAuthentication.getPrincipal());
             System.out.println("Authorities: " + storedAuthentication.getAuthorities());
         } else {
             System.out.println("Authentication 객체가 저장되지 않았습니다.");
         }
//         model.addAttribute("principal",storedAuthentication.getPrincipal());


         return "redirect:/";

         }


}
