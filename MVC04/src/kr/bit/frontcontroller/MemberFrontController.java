package kr.bit.frontcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.controller.Controller;
import kr.bit.controller.MemberContentController;
import kr.bit.controller.MemberDeleteController;
import kr.bit.controller.MemberInsertController;
import kr.bit.controller.MemberListController;
import kr.bit.controller.MemberRegisterController;
import kr.bit.controller.MemberUpdateController;
import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("*.do")
public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String url = request.getRequestURI();
		String ctx = request.getContextPath();
		// Client가 실제로 요청한 명령
		String command = url.substring(ctx.length()); // /memberList.do
		
		Controller controller = null;
		String nextPage = null;
		
		// 핸들러매핑
		HandlerMapping mappings = new HandlerMapping();
		controller = mappings.getController(command);
		nextPage = controller.requestHandler(request, response);
		
		// forward or redirect
		if (nextPage != null) {
			if (nextPage.indexOf("redirect:") != -1) {
				response.sendRedirect(nextPage.split(":")[1]); // redirect
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(ViewResolver.makeView(nextPage)); // forward
				rd.forward(request, response);
			}
		}
		
	}
}
