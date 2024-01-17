package com.example.blog1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
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
    private int count; //게시글 조회수

    @ManyToOne   //Many= Board, one= User -> 여러개의 글은 유저한명한테서 나올수있다.
    @JoinColumn(name = "userId")  //DB는 오브젝트를 저장수 없다. Fk, 자바는 오브젝트를 저장할수 있다.
    private User user;  //어떤 유저가 작성한 게시글인지 유저 참조

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)  //mappedBy 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 컬럼을 만들기 X / 테이블생성되는 FK가 아니고 그냥 DB만 끄
    //@JoinColumn(name = "replyId")가 필요없는 이유? ->
    private List<Reply> reply;  //댓글은 여러개가 달릴수있기때문에 List로 생성

    @CreationTimestamp  //자바 현재시간을 넣어줌
    private Timestamp createDate; //게시글 등록시간



}
