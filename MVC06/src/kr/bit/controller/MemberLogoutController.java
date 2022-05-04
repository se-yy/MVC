package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLogoutController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 세션을 가져와서 세션 제거
		// 세션 제거 방법
		// 1. 강제로
		String ctx = request.getContextPath();
		request.getSession().invalidate();
		
		// 2. 브라우저 종료(JSESSIONID 가 브라우저 쿠키에 저장)
		// 3. 세션이 종료될 때까지 기다림(세션타임아웃 : 기본30분=1800초 -> server의 web.xml에 설정)
		
		return "redirect:"+ctx+"/memberList.do";
	}
	
}
