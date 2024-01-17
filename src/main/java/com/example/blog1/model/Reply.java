package com.example.blog1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라감
    private int id;

    @Column(nullable = false, length = 200)
    private String content; //내용

    @ManyToOne  //여러 답변은 하나의 글에만 생성가능
    @JoinColumn(name = "boardId")
    private Board board; //어떤 게시글에 댓글인지 알기위해서 참조

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;  //어떤 게시글에 댓글을 쓴 유저가 누군지 알기위해서 참조

    @CreationTimestamp
    private Timestamp createDate; //댓글 작성한 시간





}
