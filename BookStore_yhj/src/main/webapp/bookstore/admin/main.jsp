<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="top.jsp" %>

<div class="container py-5 px-5">
	<h3 align="center"><%= session.getAttribute("id") %>님 환영합니다.</h3><br>
	<div class="row d-flex justify-content-center h-100">
 		<div class="col-lg-10 text-center">
 			<h3><br></h3>
 			<img src="<%= request.getContextPath() %>/resource/img/main_banner1.jpg" class="img-fluid">
 			<h3><br></h3>
		</div>
	</div><br>
</div>
<%@ include file="bottom.jsp" %>