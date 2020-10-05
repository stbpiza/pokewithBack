package com.exam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession ss = request.getSession();
		String userId = (String)ss.getAttribute("userId");
		if (userId==null) {
			userId="";
		}
		if(userId.contentEquals("3268944226555507")) {
			return true;	
		}
		if(userId.contentEquals("1649416911892763")) {
			return true;	
		}
		response.sendRedirect("/");
		return false;
	}
}
