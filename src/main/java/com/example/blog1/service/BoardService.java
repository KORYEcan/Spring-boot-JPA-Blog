package com.example.blog1.service;

import com.example.blog1.model.Board;
import com.example.blog1.model.User;
import com.example.blog1.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글쓰기 저장
    @Transactional
    public void save(Board board, User user) {    //title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
    @Transactional(readOnly = true)
    public Page<Board> showContentList(Pageable pageable) {
       return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board showContent(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException(" 글 상세보기 실패: 아이디를 찾을수 없습니다.");
                });
    }

    @Transactional
    public void deleteContent(int id) {
        System.out.println("글삭제하기:" + id);
        boardRepository.deleteById(id);
    }


    @Transactional
    public void updateContent(int id,Board requestBoard) {
       Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException(" 글 업데이트 실패: 아이디를 찾을수 없습니다.");
                        }); //영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        //해당 함수로 종료시(Service가 종료될때) 트랜잭션이 종료됩니다. 이때 더티 체킹 - 자동 업데이트가 db flush됨

        boardRepository.save(board);
    }
}
