package com.example.blog1.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
public class Board {


    @Id  // PK값 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto_increment
    private int id;
    @Column(nullable = false, length = 100)
    private String title;  //제목
    @Lob //대용량 데이터 사용시
    private String content; //내용 -> 썸머노트 라이브러리를 사용할 예정 <html> 태그가 섞여서 디자인이 됨.
    @ColumnDefault("0")
    private int count; //조회수
    @ManyToOne   //Many= Board, one= User -> 여러개의 글은 유저한명한테서 나올수있다.
    @JoinColumn(name = "userId")
    private User user;   //DB는 오브젝트를 저장수 없다. Fk, 자바는 오브젝트를 저장할수 있다.
    @CreationTimestamp
    private Timestamp createDate;



}
