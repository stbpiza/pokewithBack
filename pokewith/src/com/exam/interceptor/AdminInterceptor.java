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
		if(!userId.contentEquals("3268944226555507")) {
			response.sendRedirect("/");
			return false;
		}
		return true;	
	}
}
