package com.moodeon.controller;

import com.moodeon.beans.BoardInfoBean;
import com.moodeon.beans.ContentBean;
import com.moodeon.service.MainService;
import com.moodeon.service.TopMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @Autowired
    private TopMenuService topMenuService;

    @GetMapping("/main")
    public String main(Model model) {

        //List<ContentBean> -> 게시글 목록 하나
        ArrayList<List<ContentBean>> list = new ArrayList<List<ContentBean>>();

        //게시판 4개 최신 게시글 보여주기
        for (int i = 1; i <= 4; i++) {
            List<ContentBean> list1 = mainService.getMainList(i);
            list.add(list1);
        }

        model.addAttribute("list", list);


        List<BoardInfoBean> board_list = topMenuService.getTopMenuList();
        model.addAttribute("board_list", board_list);

        return "main";
    }
}
