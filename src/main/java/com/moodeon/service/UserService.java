package com.moodeon.service;

import com.moodeon.beans.UserBean;
import com.moodeon.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Resource(name = "loginUserBean")
    @Lazy
    private UserBean loginUserBean;

    public boolean checkUserIdExist(String user_id) {
        //DB에서 사용자 아이디 가져오기
        String user_name = userDao.checkUserIdExist(user_id);

        if (user_name == null) {
            return true; //사용 가능 id
        } else {
            return false; //사용 불가 id
        }
        //응답 결과로 보낼 것이 데이터 자체면 RestController 이용이 편리함
        //Spring MVC 컨트롤러는 View를 반환
        //REST 컨트롤러는 객체 데이터를 반환(JSON/XML 형식의 HTTP 응답에 직접 작성됨)
    }

    public void addUserInfo(UserBean joinUserBean) {
        userDao.addUserInfo(joinUserBean);
    }

    public void getLoginUserInfo(UserBean tempLoginUserBean) {

        UserBean tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);

        if (tempLoginUserBean2 != null) {
            loginUserBean.setUser_idx(tempLoginUserBean2.getUser_idx());
            loginUserBean.setUser_name(tempLoginUserBean2.getUser_name());
            loginUserBean.setUserLogin(true);
        }
    }

    public void getModifyUserInfo(UserBean modifyUserBean) {
        UserBean tempModifyUserBean = userDao.getModifyUserInfo(loginUserBean.getUser_idx());

        modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
        modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
        modifyUserBean.setUser_idx(tempModifyUserBean.getUser_idx());
    }

    public void modifyUserInfo(UserBean modifyUserBean) {
        modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
        userDao.modifyUserInfo(modifyUserBean);
    }

}
