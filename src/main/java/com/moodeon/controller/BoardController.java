package com.moodeon.controller;

import com.moodeon.beans.ContentBean;
import com.moodeon.beans.PageBean;
import com.moodeon.beans.UserBean;
import com.moodeon.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    //게시글 수정, 삭제 기능을 위해 주입
    @Resource(name = "loginUserBean")
    @Lazy
    private UserBean loginUserBean;

    @GetMapping("/main")
    public String main(@RequestParam("board_info_idx") int board_info_idx,
                       @RequestParam(value = "page", defaultValue = "1") int page,
                       Model model) {
        model.addAttribute("board_info_idx", board_info_idx);

        //service의 메서드 호출하기
        String boardInfoName = boardService.getBoardInfoName(board_info_idx);
        model.addAttribute("boardInfoName", boardInfoName);

        List<ContentBean> contentList = boardService.getContentList(board_info_idx, page);
        model.addAttribute("contentList", contentList);

        PageBean pageBean = boardService.getContentCnt(board_info_idx, page);
        model.addAttribute("pageBean", pageBean);

        model.addAttribute("page", page);

        return "board/main";
    }

    @GetMapping("/read")
    public String read(@RequestParam("board_info_idx") int board_info_idx,
                       @RequestParam("content_idx") int content_idx,
                       @RequestParam("page") int page,
                       Model model) {

        model.addAttribute("board_info_idx", board_info_idx);

        model.addAttribute("content_idx", content_idx);
        //특정 게시글 번호를 가지고 수정, 삭제 예정

        model.addAttribute("loginUserBean", loginUserBean);
        //로그인 유저 정보

        ContentBean readContentBean = boardService.getContentInfo(content_idx);
        model.addAttribute("readContentBean", readContentBean);

        model.addAttribute("page", page);
        return "board/read";
    }

    @GetMapping("/write")
    public String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
                        @RequestParam("board_info_idx") int board_info_idx) {

        writeContentBean.setContent_board_idx(board_info_idx);

        return "board/write";
    }

    @PostMapping("/write_done")
    public String write_done(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean, BindingResult result) {
        if (result.hasErrors()) {
            return "board/write";
        }
        boardService.addContentInfo(writeContentBean);
        return "board/write_success";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("board_info_idx") int board_info_idx,
                         @RequestParam("content_idx") int content_idx,
                         @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
                         @RequestParam("page") int page,
                         Model model) {

        model.addAttribute("board_info_idx", board_info_idx);
        model.addAttribute("content_idx", content_idx);
        model.addAttribute("page", page);

        ContentBean tempContentBean = boardService.getContentInfo(content_idx);

        modifyContentBean.setContent_writer_name(tempContentBean.getContent_writer_name());
        modifyContentBean.setContent_writer_idx(tempContentBean.getContent_writer_idx());
        modifyContentBean.setContent_board_idx(board_info_idx);
        modifyContentBean.setContent_idx(content_idx);
        modifyContentBean.setContent_date(tempContentBean.getContent_date());
        modifyContentBean.setContent_subject(tempContentBean.getContent_subject());
        modifyContentBean.setContent_text(tempContentBean.getContent_text());
        modifyContentBean.setContent_file(tempContentBean.getContent_file());

        return "board/modify";
    }

    @PostMapping("/modify_done")
    public String modify_done(@Valid @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
                             BindingResult result,
                             @RequestParam("page") int page,
                             Model model) {

        model.addAttribute("page", page);

        if (result.hasErrors()) {
            return "board/modify";
        }
        boardService.modifyContentInfo(modifyContentBean);

        return "board/modify_success";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("board_info_idx") int board_info_idx,
                         @RequestParam("content_idx") int content_idx,
                         Model model) {

        boardService.deleteContentInfo(content_idx);

        //삭제 후 글 목록으로 이동시키기 위한 board_info_idx를 model에 저장
        model.addAttribute("board_info_idx", board_info_idx);

        return "board/delete";
    }

    @GetMapping("/not_writer")
    public String not_writer() {
        return "board/not_writer";
    }
}
