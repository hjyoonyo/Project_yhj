<%@page import="my.book.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="my.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	td {
		font-size : 11px;
	}
</style>
<%
	request.setCharacterEncoding("UTF-8");
	BookDao bdao = BookDao.getInstance();
	
	ArrayList<BookBean> list = (ArrayList) session.getAttribute("search_blist");
	ArrayList<BookBean> lists = null;
	
	if(list == null){
		lists = bdao.getAllBook();
	}else{
		System.out.println("book.jsp lists 개수 : "+list.size());
		lists = list;
	}
	
%>

<%@ include file="top.jsp" %>

<div class="container py-5 px-5">
	<h3 align="center">책 관리 <font size=4> (책권수: <%=lists.size() %>)</font></h3>
	<div class="row d-flex">
		<div class="col-lg-2">
			<button type="button" class="btn btn-light" style="display:inline-block" onClick="location.href='book_insert.jsp'">책 등록</button>
			<button type="button" class="btn btn-dark" style="display:inline-block" onClick="deleteCheck()">삭제</button>
		</div>
		<div class="col-lg-7">
		</div>
		<!-- 책 검색 -->
		<div class="col-lg-3 text-end">
			<form class="d-flex" role="search" action="book_searchProc.jsp">
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
			<form name="myform" action="book_deleteCheck.jsp" method="post">
			<font size="3">* 제목, 작가, 출판사에서 검색할 수 있습니다.</font>
			<table class="table w-100">
				<thead>
					<tr>
						<th scope="col" class="table-dark"><input type="checkbox" name="allcheck" onclick="checkAll(this)"></th>
						<th scope="col" class="table-dark">책번호</th>
						<th scope="col" class="table-dark">카테고리</th>
						<th scope="col" class="table-dark">제목</th>
						<th scope="col" class="table-dark">저자</th>
						<th scope="col" class="table-dark">출판사</th>
						
						<th scope="col" class="table-dark">출간일</th>
						<th scope="col" class="table-dark">재고</th>
						<th scope="col" class="table-dark">가격</th>
						<th scope="col" class="table-dark">포인트</th>
						<th scope="col" class="table-dark">판매량</th>
						
						<th scope="col" class="table-dark">수정</th>
						<th scope="col" class="table-dark">삭제</th>
					</tr>
				</thead>
				<tbody class="table-group-divider">
<%
				if(lists.size() == 0){
%>
				<tr>
						<td scope="col" colspan="13">데이터가 존재하지 않습니다.</td>
				</tr>
<%
				}else{
					for(BookBean bb : lists){
%>
					<tr>
						<td scope="col"><input type="checkbox" name="rowcheck" value="<%= bb.getBnum()%>"></td>
						<td scope="col"><%= bb.getBnum() %></td>
						<td scope="col"><%= bb.getCnum() %></td>
						<td scope="col"><%= bb.getTitle() %></td>
						<td scope="col"><%= bb.getAuthor() %></td>
						<td scope="col"><%= bb.getPublisher() %></td>
						
						<td scope="col"><%= bb.getPubdate() %></td>
						<td scope="col"><%= bb.getBqty() %></td>
						<td scope="col"><%= bb.getPrice() %></td>
						<td scope="col"><%= bb.getPoint() %></td>
						<td scope="col"><%= bb.getSales() %></td>
						
						<td scope="col"><a href="book_updateForm.jsp?bnum=<%=bb.getBnum()%>" class="link-body-emphasis text-decoration-none">수정</a></td>
						<td scope="col"><a href="book_delete.jsp?bnum=<%=bb.getBnum()%>" class="link-body-emphasis text-decoration-none">삭제</a></td>
					</tr>
<%
					}//for
				}//else
				session.setAttribute("search_blist", null);
%>
				</tbody>
			</table>
			</form>
		</div>
	</div>
</div>

<%@ include file="bottom.jsp" %>