package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberDeleteController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberDelete(num);
		
		String ctx = request.getContextPath(); // /MVC04
		String nextPage = null;
		
		if (cnt > 0) { // 삭제 성공
			// 응답을 목록페이지로 넘겨줌
			nextPage = "redirect:"+ ctx +"/memberList.do";
		} else
			throw new ServletException("not delete");
		return nextPage;
	}
	
}
