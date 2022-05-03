package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 아이디,패스워드 받기
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		MemberVO vo = new MemberVO();
		vo.setId(user_id);
		vo.setPass(password);
		
		MemberDAO dao = new MemberDAO();
		// login 메서드 구현
		String user_name = dao.memberLogin(vo);
		
		// user_name에 값이 있으면 인증 성공, 없으면 인증 실패
		if (user_name != null && !("".equals(user_name))) { // null 체크 2번
			// 성공
			HttpSession session = request.getSession();
			session.setAttribute("userID", user_id);
			session.setAttribute("userName", user_name);
		} else { // 실패
			request.getSession().setAttribute("userID", "");
			request.getSession().setAttribute("userName", "");
			request.getSession().setAttribute("msg", "사용자 정보가 올바르지 않습니다.");
		}
		
		// 리스트 페이지로
		String ctx = request.getContextPath();
		String nextPage = "redirect:"+ctx+"/memberList.do";
		return nextPage;
	}

}
