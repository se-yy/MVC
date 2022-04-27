package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

// 전체 리스트를 가지고와 응답
@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 클라이언트의 요청을 받기 ->"); memberList.do
		// 넘어오는 파라미터 없으므로 파라미터 수집할 필요 없음
		
		// 2. 회원 전체 리스트 가져오기 ->"); Model(DAO) 연동해서 model의 메서드 사용
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.memberList();
		
		// 객체 바인딩(request바인딩)
		request.setAttribute("list", list);
		
		// forward기법
		RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
		rd.forward(request, response);
		
	}
}
