package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberDbcheckController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		MemberDAO dao = new MemberDAO();
		String idDouble = dao.memberDbcheck(id);
		
		// ajax 함수에 만들어놓은 callback함수로 응답
		response.getWriter().print(idDouble);
		
		// 화면이 바뀌지 않도록 null 처리
		return null;
	}
	
}
