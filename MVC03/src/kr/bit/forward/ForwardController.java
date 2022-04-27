package kr.bit.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberVO;

@WebServlet("/fc.do")
public class ForwardController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "park";
		int age = 45;
		String email = "aaa@email.com";
		
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		
		// 특정 메모리(request)에 객체(vo) 바인딩(set)
		request.setAttribute("vo", vo);
		
		// 객체 의뢰(누구한테 의뢰할지 선택)
		RequestDispatcher rd = request.getRequestDispatcher("view/forward.jsp");
		
		// 포워딩 (요청, 응답 객체 넘겨줌)
		rd.forward(request, response);
	}

}
