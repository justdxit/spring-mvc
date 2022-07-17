package com.moodeon.validator;

import com.moodeon.beans.UserBean;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserBean.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //유효성 검사
        //유효성 검사 대상 Bean객체 target을 형변환
        UserBean userBean = (UserBean) target;

        //로그인시 사용하는 tempLoginUserBean가 UserBean타입
        //UserBean타입은 UserValidator에서 유효성 검사를 하게됨
        //로그인 시에 회원가입시 하는 유효성 검사를 하게 되니 오류가 발생
        //에러가 발생하는 객체를 찾아내서 해당 객체만 유효성 검사하게 처리
        String beanName = errors.getObjectName();
        //System.out.println(beanName);

        if (beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {
            if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
                errors.rejectValue("user_pw", "NotEquals");
                errors.rejectValue("user_pw2", "NotEquals");
            }
        }

        if (beanName.equals("joinUserBean")) {
            if (userBean.isUserIdExist() == false) {
                errors.rejectValue("user_id", "XXCheckUserIdExist");
            }
        }
    }
}

