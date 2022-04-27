package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MyCalc;

@WebServlet("/calc.do")
public class CalcController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 클라이언트에서 넘어오는 폼 파라미터 받기(파라미터 수집) -> su1, su2
		int su1 = Integer.parseInt(request.getParameter("su1"));
		int su2 = Integer.parseInt(request.getParameter("su2"));
		
		/*
		// 2. 비즈니스 로직 (su1 ~ su2 사이의 합) -> 원래는 모델로 뺌
		int sum = 0;
		for (int i = su1; i <= su2; i++)
			sum += i;
		*/
		// 비즈니스 로직을 Model로 분리
		// Model 과 연동 -> 객체 생성
		MyCalc my = new MyCalc();
		int sum = my.hap(su1, su2);
		
		// 3. 응답 부분(Presentation Logic = View) -> 나중에 JSP가 담당
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<td>TOTAL</td>");
		out.println("<td>");
		out.println(sum);
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
