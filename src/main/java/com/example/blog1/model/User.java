package com.example.blog1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

 //User 클래스가 DB의 레코드에 매핑됨
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity
//@DynamicInsert  //insert시에 null필드는 제외시켜줌
public class User {
    @Id  //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; //시퀀스, auto_increment

    @Column(nullable = false ,length = 30) //아이디가 널이될수 없고 , 길이은 30으로 세팅)
    private String username; //아이디

    @Column(nullable = false, length = 100) //123456->  해쉬로 비밀번호를 암호화하기때문에 길이를 100으로 함
    private String password;  //유저 비밀번호

    @Column(nullable = false, length = 50)
    private String email;  //유저가 등록한 이메일

     //@ColumnDefault-> 해당 컬럼의 기본값을 설정
     //DB는 RoleType이라는게 없기때문에 해당 Enum인 String이라는걸 알려주기 위해 @Enumerated(EnumType.STRING) 사용
     @Enumerated(EnumType.STRING)
    private RoleType role; // 권한 :Enum을 쓰는게 좋다 /이유는 오타낼수있기때문에 도메인을 정하는게 좋다.  -> role을 ADMIN, USER

    @CreationTimestamp  //시간이 자동입력
    private Timestamp createDate;  //유저가 가입한 날짜

}

//ORM -> Java(다른언어) Object -> 테이블 매핑해주는 기술


