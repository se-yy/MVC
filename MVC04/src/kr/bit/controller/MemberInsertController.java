package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberInsertController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. 파라미터 수집(VO에)
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		//MemberVO vo = new MemberVO(id, pass, name, age, email, phone);
		// setter로 수집하는게 일반적
		MemberVO vo = new MemberVO();
		vo.setId(id); vo.setPass(pass); vo.setName(name); vo.setAge(age); vo.setEmail(email); vo.setPhone(phone);
		
		// VO를 Model과 연동
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberInsert(vo);
		
		String nextPage = null;
		if (cnt > 0) {
			// 가입 성공
			// 다시 회원 리스트 보기 페이지로 가야함 (/MVC04/memberList.do)
			nextPage = "redirect:/MVC04/memberList.do";
		} else {
			// 가입 실패 -> 예외 객체를 만들어서 WAS에게 던지자
			throw new ServletException("not insert");
		}
		
		return nextPage;
	}
	
}
