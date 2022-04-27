package kr.bit.forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rc.do")
public class RedirectController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String data = "apple";
		
		// 2. View(result.jsp)로 data 전달하여 View에서 출력
		// Controller에서 View로 페이지 전환하는 방법
		
		// 1) redirect : get방식으로 data 전달 -> 많은 정보를 옮기기 힘듦, 보안 문제
		response.sendRedirect("view/result.jsp?data="+data);
		
		// 2) forward
	}

}
