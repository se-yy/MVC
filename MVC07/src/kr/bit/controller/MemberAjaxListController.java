package kr.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberAjaxListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.memberList();
		
		// GSON : 구글에서 만든 List ~> json으로 바꾸는 API
		// mvn에서 검색 후 jar 파일 다운받아 lib에 복붙
		Gson g = new Gson();
		String json = g.toJson(list);
		
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(json);
		
		return null;
	}

}
