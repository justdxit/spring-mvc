package com.moodeon.interceptor;

import com.moodeon.beans.BoardInfoBean;
import com.moodeon.beans.UserBean;
import com.moodeon.service.TopMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TopMenuInterceptor implements HandlerInterceptor {

    @Autowired
    private TopMenuService topMenuService;

    @Resource(name = "loginUserBean")
    @Lazy //xml 생성시 Session scope보다 빠르게 Bean 주입을 하는 것을 늦춘다
    private UserBean loginUserBean;

    //상속받은 HandlerInterceptor의 메서드들은 default 메서드로 모두 구현하지 않아도 됨
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<BoardInfoBean> topMenuList = topMenuService.getTopMenuList();
        request.setAttribute("topMenuList", topMenuList);
        request.setAttribute("loginUserBean", loginUserBean);
        return true;
    }
}
