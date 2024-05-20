<%@page import="my.book.CategoryDao"%>
<%@page import="my.book.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	CategoryDao cdao = CategoryDao.getInstance();
	//ArrayList<CategoryBean> lists = cdao.getAllCategory();
	
	int i;
	String[] lev = {"대분류","중분류","소분류"};
%>

<%@ include file="top.jsp" %>

<div class="container py-5 px-5">
	<h3 align="center">카테고리 등록</h3><br>
	<form action="cateProc.jsp" method="post" name="findidForm">
		<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>상위 카테고리</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="cname_upper" class="form-control" onblur="upperCheck()" placeholder="없음(대분류시 입력)">
				</div>
			</div>
		</div>
	 	
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>카테고리명</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="cname" class="form-control" onblur="cnameCheck()">
				</div>
			</div>
		</div>
		
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>카테고리 코드</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="code" class="form-control" onblur="codeCheck()">
				</div>
			</div>
		</div>

	 	<div class="row d-flex justify-content-center my-3">
	 		<div class="col-md-3 text-center">
		 		<button type="submit" class="btn btn-dark w-100" onClick="return cinputCheck()">등록</button>
			</div>
		</div>
	</form>
	
	<h3><br></h3>
	<h3><br></h3>
	
	<h3 align="center">카테고리 목록</h3><br>
	<div class="row d-flex justify-content-left h-100">
<%
		for(i=1; i<=lev.length; i++){
			ArrayList<CategoryBean> lists = cdao.getCategoryByLev(i);
%>
			<div class="col-md-4 text-center">
				<table class="table">
					<thead>
						<tr>
							<th colspan="2" scope="col" class="table-dark"><%= lev[i-1]+"(코드/번호)" %></th>
						</tr>
					</thead>
					<tbody class="table-group-divider">
<%
			for (CategoryBean cb : lists) {
%>
						<tr>
							<td width="70%"><b><%= cb.getCname()+"("+cb.getCode()+"/"+cb.getCnum()+")" %></b></td>
							<td width="30%">
								<a href="cate_update.jsp?cnum=<%= cb.getCnum() %>" class="link-body-emphasis text-decoration-none">수정</a>
								<a href="cate_delete.jsp?cnum=<%= cb.getCnum() %>" class="link-body-emphasis text-decoration-none">삭제</a>
							</td>
						</tr>
<%			} %>
					</tbody>
				</table>
			</div>
<%		}%>
	</div>
</div>

<%@ include file="bottom.jsp" %>