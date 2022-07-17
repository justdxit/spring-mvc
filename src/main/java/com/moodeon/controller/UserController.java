package com.moodeon.controller;

import com.moodeon.beans.UserBean;
import com.moodeon.service.UserService;
import com.moodeon.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //세션 영역에 저장되어 있는 UserBean을 주입 받는다
    @Resource(name = "loginUserBean")
    @Lazy
    private UserBean loginUserBean;

    @GetMapping("/login")
    public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
                        @RequestParam(value = "loginFail", defaultValue = "false") boolean loginFail, Model model) {
                        //loginFail이란 이름의 파라미터에 true 값이 들어오면 로그인 실패창을 띄운다
        model.addAttribute("loginFail", loginFail);

        return "user/login";
    }

    @PostMapping("login_done")
    public String login_done(@Valid @ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, BindingResult result) {

        if (result.hasErrors()) {
            return "user/login";
        }

        //tempLoginUserBean의 로그인된 ID,PW 전달
        userService.getLoginUserInfo(tempLoginUserBean);

        if (loginUserBean.isUserLogin() == true) {
            return "user/login_success";
        } else {
            return "user/login_fail";
        }
    }

    @GetMapping("/join")
    public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
        return "user/join";
    }

    @PostMapping("/join_done") //파라미터로 넘어오는 데이터를 받을 Bean 설정, 유효성검사(@Valid)결과는 BindingResult로 처리
    public String join_done(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
        if (result.hasErrors()) { //유효성 검사에 문제가 있다면
            return "user/join";
        }
        userService.addUserInfo(joinUserBean);

        return "user/join_success";
    }

    @GetMapping("/modify")
    public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
        userService.getModifyUserInfo(modifyUserBean);
        return "user/modify";
    }

    @PostMapping("/modify_done")
    public String modify_done(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result) {
        if (result.hasErrors()) {
            return "user/modify";
        }

        userService.modifyUserInfo(modifyUserBean);
        return "user/modify_success";
    }

    @GetMapping("/logout")
    public String logout() {
        loginUserBean.setUserLogin(false);
        return "user/logout";
    }

    @GetMapping("/not_login")
    public String not_login() {
        return "user/not_login";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        UserValidator validator1 = new UserValidator();
        binder.addValidators(validator1);
    }
}
