<%@page import="my.member.MemberBean"%>
<%@page import="my.member.MemberDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	td {
		font-size : 11px;
	}
</style>
<%
	request.setCharacterEncoding("UTF-8");
	
	MemberDao mdao = MemberDao.getInstance();
	ArrayList<MemberBean> list = (ArrayList) session.getAttribute("search_mlist");
	ArrayList<MemberBean> lists = null;
	
	if(list == null){
		lists = mdao.getAllMembers();
	}else{
		System.out.println("Member.jsp lists 개수 : "+list.size());
		lists = list;
	}
%>

<%@ include file="top.jsp" %>

<div class="container py-5 px-5">
	<h3 align="center">회원 관리 <font size=4>(회원수: <%=lists.size() %>)</font></h3>
	<div class="row d-flex justify-content-end">
		<div class="col-lg-2">
			<button type="button" class="btn btn-dark" onClick="deleteCheck()">삭제</button>
		</div>
		<div class="col-lg-7">
		</div>
		<!-- 회원 검색 -->
		<div class="col-lg-3 text-end">
			<form class="d-flex" role="search" action="member_searchProc.jsp">
				<input class="form-control" type="search" placeholder="검색" name="keyword" size="20">
				<button type="submit" style="background: none; border: none; width: 50px;">
					<svg xmlns="http://www.w3.org/2000/svg" width="20""
						fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
						<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
					</svg>
				</button>
			</form>
		</div>
	</div><br>
	
	<div class="row d-flex justify-content-center h-100">
		<div class="col-xs-12 table-responsive">
			<form name="myform" action="member_deleteCheck.jsp" method="post">
			<font size="3">* 아이디, 이름, 이메일, 주소, 전화번호에서 검색할 수 있습니다.</font>
			<table class="table table-hover w-100">
				<thead>
					<tr>
						<th scope="col" class="table-dark"><input type="checkbox" name="allcheck" onclick="checkAll(this)"></th>
						<th scope="col" class="table-dark">번호</th>
						<th scope="col" class="table-dark">아이디</th>
						<th scope="col" class="table-dark">비밀번호</th>
						<th scope="col" class="table-dark">이름</th>
						<th scope="col" class="table-dark">이메일</th>
						
						<th scope="col" class="table-dark">주소</th>
						<th scope="col" class="table-dark">전화번호</th>
						<th scope="col" class="table-dark">생년월일</th>
						<th scope="col" class="table-dark">가입일</th>
						
						<th scope="col" class="table-dark">수정</th>
						<th scope="col" class="table-dark">삭제</th>
					</tr>
				</thead>
				<tbody class="table-group-divider">
<%
				if(lists.size() == 0){
%>
				<tr>
						<td scope="col" colspan="12">데이터가 존재하지 않습니다.</td>
				</tr>
<%
				}else{
				for(MemberBean mb : lists){
%>
					<tr>
						<td scope="col"><input type="checkbox" name="rowcheck" value="<%= mb.getNo()%>"></td>
						<td scope="col"><%= mb.getNo() %></td>
						<td scope="col"><%= mb.getId() %></td>
						<td scope="col"><%= mb.getPasswd() %></td>
						<td scope="col"><%= mb.getName() %></td>
						<td scope="col"><%= mb.getEmail() %></td>
						
						<td scope="col"><%= mb.getAddress() %></td>
						<td scope="col"><%= mb.getPhone() %></td>
						<td scope="col"><%= mb.getBirth() %></td>
						<td scope="col"><%= mb.getRegdate() %></td>
						
						<td scope="col"><a href="member_updateForm.jsp?no=<%=mb.getNo()%>" class="link-body-emphasis text-decoration-none">수정</a></td>
						<td scope="col"><a href="member_delete.jsp?no=<%=mb.getNo()%>" class="link-body-emphasis text-decoration-none">삭제</a></td>
					</tr>
<%
					}
				}
				session.setAttribute("search_mlist", null);
%>
				</tbody>
			</table>
			</form>
		</div>
	</div>
</div>

<%@ include file="bottom.jsp" %>