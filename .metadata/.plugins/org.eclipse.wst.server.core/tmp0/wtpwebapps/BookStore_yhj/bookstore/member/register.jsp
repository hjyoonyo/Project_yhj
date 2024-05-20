<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>

<div class="container py-5 px-5">
	<h3 align="center">회원가입</h3><br><br>
	<form action="registerProc.jsp" method="post" name="myForm">
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>아이디</label>
			</div>
	 		<div class="col-lg-3 mb-3 pe-0">
				<input type="text" name="id" class="form-control" onblur="idCheck()">
				<div id="idMsg" class="form-text"></div>
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>비밀번호</label>
			</div>
	 		<div class="col-lg-3 mb-3 pe-0">
				<input type="password" name="passwd" class="form-control" onblur="pwCheck()">
				<div id="pwMsg" class="form-text"></div>
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>비밀번호 확인</label>
			</div>
	 		<div class="col-lg-3 mb-3 pe-0">
				<input type="password" name="repasswd" class="form-control" onKeyUp="repwCheck()">
				<div id="repwMsg" class="form-text"></div>
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>이름</label>
			</div>
	 		<div class="col-lg-3 mb-3 pe-0">
				<input type="text" name="name" class="form-control">
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>전화번호</label>
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<select class="form-select" name="phone1">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="012">012</option>
				</select>
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<input type="text" name="phone2" class="form-control"maxlength="4">
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<input type="text" name="phone3" class="form-control"maxlength="4">
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>이메일</label>
			</div>
	 		<div class="col-lg-3 mb-3 pe-0">
				<input type="text" name="email" class="form-control" onblur="eCheck()">
				<div id="eMsg" class="form-text"></div>
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>생년월일</label>
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<select class="form-select" name="year">
	<%
				Date d = new Date();
				int i;
				for(i=d.getYear()+1900;i>1900;i--){ //연도 2014~1901
	%>
					<option value="<%= i %>"><%= i %></option>
	<%			} %>
				</select>
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<select class="form-select" name="month">
	<%
				for(i=1;i<=12;i++){ //월 1~12
	%>
					<option value="<%= i %>"><%= i %></option>
	<%			} %>
				</select>
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<input type="text" name="day" class="form-control" placeholder="일">
			</div>
		</div>
		
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>주소</label>
			</div>
	 		<div class="col-lg-3 pe-0">
				<div class="input-group mb-3 pe-0">
					<input type="text" name="postcode" class="form-control" id="sample6_postcode" placeholder="우편번호">
					<input type="button" class="btn btn-dark" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				</div>
				<div class="input-group mb-3 pe-0">
					<input type="text" name="adress" class="form-control" id="sample6_address" placeholder="주소">
				</div>
				<div class="input-group mb-3 pe-0">
					<input type="text" name="detailAddress" class="form-control" id="sample6_detailAddress" placeholder="상세주소">
				</div>
				<div class="input-group mb-3 pe-0">
					<input type="hidden" id="sample6_extraAddress" class="form-control" placeholder="참고항목">
				</div>
			</div>
		</div><br>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-5">
		 		<button type="submit" class="btn btn-dark w-100" onclick="return registerCheck()">회원가입</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="/bottom.jsp" %>