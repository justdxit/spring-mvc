package com.moodeon.interceptor;

import com.moodeon.beans.ContentBean;
import com.moodeon.beans.UserBean;
import com.moodeon.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckWriterInterceptor implements HandlerInterceptor {

    @Resource(name="loginUserBean")
    @Lazy
    private UserBean loginUserBean;

    //사용할 서비스
    @Autowired
    private BoardService boardService;

    //검사 구현
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //주입 불가능, servlet JSP처럼 진행
        String str1 = request.getParameter("content_idx");
        int content_idx = Integer.parseInt(str1);

        //현재 게시글 정보 가져오기
        ContentBean currentContentBean = boardService.getContentInfo(content_idx);

        if (currentContentBean.getContent_writer_idx() != loginUserBean.getUser_idx()) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/board/not_writer");
            return false;
        }
        return true;
    }

}
