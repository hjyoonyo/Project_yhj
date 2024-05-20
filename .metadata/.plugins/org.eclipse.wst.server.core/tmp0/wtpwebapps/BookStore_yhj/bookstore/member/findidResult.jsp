<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
%>
<%@ include file="/top.jsp" %>
<div class="container py-5 px-5">
	<h3 align="center">아이디 찾기</h3><br>
	<div class="row d-flex justify-content-center h-100 bg-light">
 		<div class="col-md-5 text-center">
 			<h3><br></h3>
 			<label>찾으신 아이디는 <%= id%>입니다.</label>
 			<h3><br></h3>
		</div>
	</div><br>
	
	<div class="row d-flex justify-content-center">
 		<div class="col-md-5 text-center">
	 		<button type="button" class="btn btn-dark w-50" onclick="location.href='login.jsp'">로그인</button>
		</div>
	</div>
</div>


<%@ include file="/bottom.jsp" %>