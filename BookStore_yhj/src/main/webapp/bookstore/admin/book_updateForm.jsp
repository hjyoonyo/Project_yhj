<%@page import="my.book.CategoryBean"%>
<%@page import="my.book.CategoryDao"%>
<%@page import="my.book.BookBean"%>
<%@page import="my.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	img{
		width :150px; 
	}
</style>
<%
	int bnum = Integer.parseInt(request.getParameter("bnum"));
	BookDao bdao = BookDao.getInstance();
	CategoryDao cdao = CategoryDao.getInstance();
	
	BookBean bb = bdao.getBookByBnum(bnum);
	CategoryBean cb = cdao.getCategoryByCnum(bb.getCnum());
	
%>
<%@ include file="top.jsp" %>

<div class="container py-5 px-5">
	<h3 align="center">책 수정</h3><br>
	<form action="book_updateProc.jsp" method="post" name="findidForm" enctype="multipart/form-data">
		<input type="hidden" name="bnum" class="form-control" value="<%= bb.getBnum() %>" >
		<div class="row d-flex justify-content-center">
	 		<div class="col-md-2 text-center">
	 			<label>제목</label>
			</div>
	 		<div class="col-md-3">
				<div class="input-group mb-3">
					<input type="text" name="title" class="form-control" value="<%= bb.getTitle() %>" >
				</div>
			</div>
	 		<div class="col-md-2 text-center">
	 			<label>저자</label>
			</div>
	 		<div class="col-md-3">
				<div class="input-group mb-3">
					<input type="text" name="author" class="form-control" value="<%= bb.getAuthor() %>" >
				</div>
			</div>
		</div>
	 	
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2 text-center">
	 			<label>카테고리</label>
			</div>
	 		<div class="col-md-3">
				<div class="input-group mb-3">
					<input type="text" name="cname" class="form-control" value="<%= cb.getCname() %>">
				</div>
			</div>
	 		<div class="col-md-2 text-center">
	 			<label>출판사</label>
			</div>
	 		<div class="col-md-3">
				<div class="input-group mb-3">
					<input type="text" name="publisher" class="form-control" value="<%= bb.getPublisher() %>">
				</div>
			</div>
		</div>
		
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2 text-center">
	 			<label>가격</label>
			</div>
	 		<div class="col-md-3">
				<div class="input-group mb-3">
					<input type="text" name="price" class="form-control" value="<%= bb.getPrice() %>">
				</div>
			</div>
	 		<div class="col-md-2 text-center">
	 			<label>수량</label>
			</div>
	 		<div class="col-md-3">
				<div class="input-group mb-3">
					<input type="text" name="bqty" class="form-control" value="<%= bb.getBqty() %>">
				</div>
			</div>
		</div>
		
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2 text-center">
	 			<label>포인트</label>
			</div>
	 		<div class="col-md-3">
				<div class="input-group mb-3">
					<input type="text" name="point" class="form-control" value="<%= bb.getPoint() %>">
				</div>
			</div>
	 		<div class="col-md-2 text-center">
	 			<label>판매량</label>
			</div>
	 		<div class="col-md-3">
	 			<input type="text" name="sales" class="form-control" value="<%= bb.getSales() %>">
			</div>
		</div>

	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2 text-center">
	 			<label>출간일</label>
			</div>
	 		<div class="col-md-3">
				<div class="input-group mb-3">
					<input type="date" name="pubdate" class="form-control" id="pubdate" value="<%= bb.getPubdate() %>">
				</div>
			</div>
	 		<div class="col-md-2 text-center">
	 			<label>표지</label>
			</div>
	 		<div class="col-md-3">
	 			<div class="input-group mb-3">
	 				<input type="hidden" value="<%= bb.getBimage() %>" name="orgbimage" class="form-control">
	 				<input type="file" value="파일 선택" name="bimage" class="form-control" id="uploadFile">
				</div>
			</div>
		</div>

	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2 text-center">
	 			<label>책 소개</label>
			</div>
	 		<div class="col-md-5">
				<div class="input-group mb-3">
					<textarea rows="10" cols="30" name="content" class="form-control"><%= bb.getContent() %> </textarea>
				</div>
			</div>
	 		<div class="col-md-3 text-center" id="imgArea">
	 			<img src="<%= request.getContextPath() %>/resource/img/<%= bb.getBimage() %>" class="img-fluid border border-3 border-light">
			</div>
		</div>

	 	<div class="row d-flex justify-content-center my-3">
	 		<div class="col-md-3 text-center">
		 		<button type="submit" class="btn btn-dark w-100">수정</button>
			</div>
	 		<div class="col-md-3 text-center">
		 		<button type="button" class="btn btn-light w-100" onClick="location.href='book.jsp'">취소</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="bottom.jsp" %>