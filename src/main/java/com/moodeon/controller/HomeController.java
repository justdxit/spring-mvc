package com.moodeon.controller;

import com.moodeon.beans.UserBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
/**
	//root-context에 주입한 LoginUserBean 확인하기
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;

	//Session Scope 등록한 빈!
	//xml을 사용하면 서버를 가동할 때 자동 주입 시도함, 세션 스코프와 맞지 않아서 오류 발생
	//Lazy 어노테이션을 이용해 서버 가동시 자동 주입을 연기함
 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {

//		System.out.println(loginUserBean);

		/*
		실제 실행되는 위치 확인
		System.out.println(request.getServletContext().getRealPath("/"));
		C:/spring-mvc/target/spring-mvc-1.0.0-BUILD-SNAPSHOT
		*/

		return "redirect:/main";
	}

}


