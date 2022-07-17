package com.moodeon.controller;

import com.moodeon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/checkUserIdExist/{user_id}")
    public String checkUserIdExist(@PathVariable String user_id) {
        //값 하나만 받을 것이기 때문에 문자열로 받자
        boolean chk = userService.checkUserIdExist(user_id);

        return chk + "";
    }
}
