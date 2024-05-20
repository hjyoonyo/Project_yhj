<%@page import="my.book.CategoryDao"%>
<%@page import="my.book.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	int cnum = Integer.parseInt(request.getParameter("cnum"));
	System.out.println(cnum);

	CategoryDao cdao = CategoryDao.getInstance();
	CategoryBean cb = cdao.getCategoryByCnum(cnum);
	CategoryBean cb_upper = cdao.getCategoryByCnum(cb.getRef());
	String cname_upper = cb_upper.getCname();
	String cname = cb.getCname();

	if(cname.equals(cname_upper)){
		cname_upper = "없음";
	}
%>

<%@ include file="top.jsp" %>

<div class="container py-5 px-5">
	<h3 align="center">카테고리 수정</h3><br>
	<form action="cate_updateProc.jsp" method="post" name="findidForm">
		<input type="hidden" name="cnum" value="<%= cnum%>">
		<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>상위 카테고리</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="cname_upper" class="form-control" onblur="upperCheck()" value="<%= cname_upper %>">
				</div>
			</div>
		</div>
	 	
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>카테고리명</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="hidden" name="orgcname" class="form-control" value="<%= cname%>">
					<input type="text" name="cname" class="form-control" onblur="cnameCheck()" value="<%= cname%>">
				</div>
			</div>
		</div>
		
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>카테고리 코드</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="hidden" name="orgcode" class="form-control" value="<%= cb.getCode() %>">
					<input type="text" name="code" class="form-control" onblur="codeCheck()" value="<%= cb.getCode() %>">
				</div>
			</div>
		</div>

	 	<div class="row d-flex justify-content-center my-3">
	 		<div class="col-md-3 text-center">
		 		<button type="submit" class="btn btn-dark w-100" onClick="return cupdateCheck()">수정</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="bottom.jsp" %>