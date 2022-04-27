package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		
		// 3. 회원 전체 리스트를 HTML로 만들어서 응답하기
		// 지금은 servlet으로 하지만 나중에 View 로 빼서 함
		response.setContentType("text/html;charset=utf-8"); // MIME Type
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table class='table table-bordered'>");
		out.println("<thead>");
		  out.println("<tr>");
		    out.println("<th>번호</th>");
		    out.println("<th>아이디</th>");
		    out.println("<th>비밀번호</th>");
		    out.println("<th>이름</th>");
		    out.println("<th>나이</th>");
		    out.println("<th>이메일</th>");
		    out.println("<th>전화번호</th>");
		    out.println("<th>삭제</th>");
		    out.println("</tr>");
		  out.println("</thead>");
		out.println("<tbody>");
		
		// 리스트에 들어가있는 데이터 수만큼 반복 출력
		for (MemberVO vo : list) {
			out.println("<tr>");
		    out.println("<td>"+vo.getNum()+"</td>");
		    out.println("<td><a href='/MVC01/memberContent.do?num="+vo.getNum()+"'>"+vo.getId()+"</a></td>");
		    out.println("<td>"+vo.getPass()+"</td>");
		    out.println("<td>"+vo.getName()+"</td>");
		    out.println("<td>"+vo.getAge()+"</td>");
		    out.println("<td>"+vo.getEmail()+"</td>");
		    out.println("<td>"+vo.getPhone()+"</td>");
		    out.println("<td><a href='/MVC01/memberDelete.do?num="+vo.getNum()+"'>삭제</a></td>");
		    out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("<tr>");
		out.println("<td colspan='8' align='right'>");
			out.println("<a href='member/memberRegister.html'>회원가입</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
