package com.example.blog1.controller;

import com.example.blog1.config.auth.PrincipalDetail;
import com.example.blog1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //@AuthenticationPrincipal PrincipalDetail principal
    //컨트롤러에서 세션을 어떻게 찾을까?

    @GetMapping("/board/{id}")
    public  String findById(@PathVariable int id, Model model ) {
        model.addAttribute("board", boardService.showContent(id));
        return "board/detail";
    }

    @GetMapping({"","/"})
    public String index(Model model , @PageableDefault (size=3,sort="id", direction=Sort.Direction.DESC)Pageable pageable) {
        model.addAttribute("boards",boardService.showContentList(pageable));

        return "index";  //viewResolver 작동!!
    }

    // /board/${board.id}/updateForm"
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(Model model, @PathVariable int id ) {

    model.addAttribute("board",boardService.showContent(id));
        return "board/updateForm";
    }


   //USER권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

}
