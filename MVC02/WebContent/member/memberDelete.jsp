<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*" %>

<%
int num = Integer.parseInt(request.getParameter("num"));

MemberDAO dao = new MemberDAO();
int cnt = dao.memberDelete(num);
if (cnt > 0) { // 삭제 성공
	// 응답을 목록페이지로 넘겨줌
	response.sendRedirect("memberList.jsp");
} else
	throw new ServletException("not delete");
%>