package com.example.blog1.controller.api;

import com.example.blog1.config.auth.PrincipalDetail;
import com.example.blog1.dto.ResponseDto;
import com.example.blog1.model.Board;
import com.example.blog1.model.RoleType;
import com.example.blog1.model.User;
import com.example.blog1.service.BoardService;
import com.example.blog1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {


    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> saveContent(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.save(board,principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); // 자바 오프젝트를 JSON으로 변환해서 리턴(Jackson)
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.deleteContent(id);
      return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
   }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id , @RequestBody Board board) {
        boardService.updateContent(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }




}
