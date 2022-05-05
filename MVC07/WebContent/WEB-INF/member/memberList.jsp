<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.bit.model.*"%>
<%@ page import="java.util.*"%>
<%
	// ArrayList<MemberVO> list=(ArrayList<MemberVO>)request.getAttribute("list");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>
<script
	src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'></script>
<script
	src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>
<script type="text/javascript">
  // 페이지 열렸을 때 메세지 존재하면 회원인증 실패 메세지 출력
  $(document).ready(function(){
	  <c:if test="${!empty msg}">
	    alert("${msg}");
	    // session에 저장되어 있는 msg 값을 제거
	    <c:remove var="msg" scope="session"/>
	  </c:if>
  });
  function deleteFn(num){
	  location.href="${ctx}/memberDelete.do?num="+num; // ?num=12
  }
  function check() {
	  if ($('#user_id').val() == '') {
		  alert("아이디를 입력하세요");
		  return false;
	  }
	  if ($('#password').val() == '') {
		  alert("비밀번호를 입력하세요");
		  return false;
	  }
	  return true;
  }
  function logout(){
	  location.href="<c:url value='/memberLogout.do'/>";
  }
</script>

</head>
<body>
	<h2>회원관리 시스템</h2>
	<div class="panel panel-default">
		<div class="panel-heading">
		<c:if test="${sessionScope.userId==null || sessionScope.userId==''}">
			<form class="form-inline" action="${ctx}/memberLogin.do" method="post">
				<div class="form-group">
					<label for="user_id">ID:</label> <input type="text"
						class="form-control" id="user_id" name="user_id">
				</div>
				<div class="form-group">
					<label for="password">Password:</label> <input type="password"
						class="form-control" id="password" name="password">
				</div>
				<button type="submit" class="btn btn-default" onclick="return check()">로그인</button>
			</form>
		</c:if>
		
		<c:if test="${sessionScope.userId!=null && sessionScope.userId!=''}">
			${sessionScope.userName}님 환영합니다.
			<button type="button" class="btn btn-warning" onclick="logout()">로그아웃</button>
		</c:if>
		
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>비밀번호</th>
							<th>이름</th>
							<th>나이</th>
							<th>이메일</th>
							<th>전화번호</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="vo" items="${list}">
							<tr>
								<td>${vo.num}</td>
								<td><a href="${ctx}/memberContent.do?num=${vo.num}">${vo.id}</a></td>
								<td>${vo.pass}</td>
								<td>${vo.name}</td>
								<td>${vo.age}</td>
								<td>${vo.email}</td>
								<td>${vo.phone}</td>
								
								<!-- 로그인 성공 시 본인것만 삭제 -->
								<c:if test="${sessionScope.userId==vo.id}">
									<td><input type="button" value="삭제" class="btn btn-warning"
									onclick="deleteFn(${vo.num})"></td>
								</c:if>
								
								<!-- 다른 회원들 삭제 못하게 버튼 활성화 끄기 -->
								<c:if test="${sessionScope.userId!=vo.id}">
									<td><input type="button" value="삭제" class="btn btn-warning"
									onclick="deleteFn(${vo.num})" disabled="disabled"></td>
								</c:if>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="8" align="right"><input type="button"
								value="회원가입" class="btn btn-primary"
								onclick="location.href='${ctx}/memberRegister.do'" /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer">
				회원관리 ERP System(admin@naver.com)
			</div>
		</div>
	</div>
</body>
</html>