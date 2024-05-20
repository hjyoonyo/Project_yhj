<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="top.jsp" %>

<div class="container py-5 px-5">
	<h3 align="center">책 등록</h3><br>
	<form action="book_insertProc.jsp" method="post" name="findidForm" enctype="multipart/form-data">
		<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>제목</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="title" class="form-control" >
				</div>
			</div>
	 		<div class="col-md-2">
	 			<label>저자</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="author" class="form-control">
				</div>
			</div>
		</div>
	 	
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>카테고리</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="cname" class="form-control" onblur="cnameCheck()">
				</div>
			</div>
	 		<div class="col-md-2">
	 			<label>출판사</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="publisher" class="form-control" >
				</div>
			</div>
		</div>
		
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>가격</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="price" class="form-control" >
				</div>
			</div>
	 		<div class="col-md-2">
	 			<label>수량</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="bqty" class="form-control" >
				</div>
			</div>
		</div>
		
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>포인트</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="point" class="form-control" >
				</div>
			</div>
	 		<div class="col-md-2">
	 			<label>표지</label>
			</div>
	 		<div class="col-md-4">
	 			<div class="input-group mb-3">
	 				<input type="file" value="파일 선택" name="bimage" class="form-control">
					
				</div>
			</div>
		</div>

	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>출간일</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="date" name="pubdate" class="form-control" >
				</div>
			</div>
	 		<div class="col-md-2">
			</div>
	 		<div class="col-md-4">
	 			<div class="input-group mb-3">
				</div>
			</div>
		</div>

	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-2">
	 			<label>책 소개</label>
			</div>
	 		<div class="col-md-10">
				<div class="input-group mb-3">
					<textarea rows="10" cols="30" name="content" class="form-control"></textarea>
				</div>
			</div>
		</div>

	 	<div class="row d-flex justify-content-center my-3">
	 		<div class="col-md-3 text-center">
		 		<button type="submit" class="btn btn-dark w-100" onClick="return binsertCheck()">등록</button>
			</div>
	 		<div class="col-md-3 text-center">
		 		<button type="button" class="btn btn-light w-100" onClick="location.href='book.jsp'">취소</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="bottom.jsp" %>	