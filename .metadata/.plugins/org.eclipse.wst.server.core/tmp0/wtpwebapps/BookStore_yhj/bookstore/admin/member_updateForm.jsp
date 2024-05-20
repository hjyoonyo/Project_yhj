<%@page import="my.member.MemberBean"%>
<%@page import="my.member.MemberDao"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
member_updateForm.jsp<br>
<%
	int no = Integer.parseInt(request.getParameter("no"));
	MemberDao mdao = MemberDao.getInstance();
	MemberBean mb = mdao.getMemberByNo(no);
	
	String[] birth = mb.getBirth().split("-");
	String[] phone = mb.getPhone().split("-");
	System.out.println(birth[0]+"/"+phone[0]);
	
%>
<%@ include file="top.jsp" %>
<div class="container py-5 px-5">
	<h3 align="center">회원 수정</h3><br><br>
	<form action="member_updateProc.jsp" method="post" name="myForm">
		<input type="hidden" name="no" class="form-control" value="<%= no %>">
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>아이디</label>
			</div>
	 		<div class="col-lg-3 mb-3 pe-0">
				<input type="text" name="id" class="form-control" value="<%=mb.getId() %>" disabled>
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>비밀번호</label>
			</div>
	 		<div class="col-lg-3 mb-3 pe-0">
				<input type="text" name="passwd" class="form-control" value="<%=mb.getPasswd() %>" disabled>
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>가입일</label>
			</div>
	 		<div class="col-lg-3 mb-3 pe-0">
				<input type="text" name="regdate" class="form-control" value="<%=mb.getRegdate() %>" disabled>
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>이름</label>
			</div>
	 		<div class="col-lg-3 mb-3 pe-0">
				<input type="text" name="name" class="form-control" value="<%=mb.getName() %>">
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>전화번호</label>
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<select class="form-select" name="phone1">
					<option value="010" <% if(phone[0].equals("010")){ %> selected <%} %>>010</option>
					<option value="011" <% if(phone[0].equals("011")){ %> selected <%} %>>011</option>
					<option value="012" <% if(phone[0].equals("012")){ %> selected <%} %>>012</option>
				</select>
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<input type="text" name="phone2" class="form-control" maxlength="4" value="<%= phone[1] %>">
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<input type="text" name="phone3" class="form-control" maxlength="4" value="<%= phone[2] %>">
			</div>
		</div>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>이메일</label>
			</div>
	 		<div class="col-lg-3 mb-3 pe-0">
				<input type="hidden" name="orgemail" class="form-control" value="<%=mb.getEmail() %>">
				<input type="text" name="email" class="form-control" value="<%=mb.getEmail() %>" onblur="eCheck()">
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
					<option value="<%= i %>" <% if(i == Integer.parseInt(birth[0])){ %> selected <%} %>><%= i %></option>
	<%			} %>
				</select>
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<select class="form-select" name="month">
	<%
				for(i=1;i<=12;i++){ //월 1~12
	%>
					<option value="<%= i %>" <% if(i == Integer.parseInt(birth[1])){ %> selected <%} %>><%= i %></option>
	<%			} %>
				</select>
			</div>
	 		<div class="col-lg-1 mb-3 pe-0">
				<input type="text" name="day" class="form-control" value="<%= birth[2] %>">
			</div>
		</div>
		
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
	 			<label>주소</label>
			</div>
	 		<div class="col-lg-3 pe-0">
				<input type="text" name="address" class="form-control" value="<%=mb.getAddress() %>">
			</div>
		</div><br>
		
		<div class="row d-flex justify-content-center">
	 		<div class="col-lg-2">
		 		<button type="submit" class="btn btn-dark w-100" onclick="return mupdateCheck()">수정</button>
			</div>
	 		<div class="col-lg-2">
		 		<button type="button" class="btn btn-light w-100" onclick="location.href='member.jsp'">취소</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="bottom.jsp" %>