<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>

<div class="container py-5 px-5">
	<h3 align="center">비밀번호 찾기</h3><br> 
	<form action="findpwProc.jsp" method="post" name="findpwForm">
		<div class="row d-flex justify-content-center">
	 		<div class="col-md-1">
	 			<label>아이디</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="id" class="form-control">
				</div>
			</div>
		</div>

		<div class="row d-flex justify-content-center">
	 		<div class="col-md-1">
	 			<label>이름</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="name" class="form-control">
				</div>
			</div>
		</div>
	 	
	 	<div class="row d-flex justify-content-center">
	 		<div class="col-md-1">
	 			<label>이메일</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="email" class="form-control">
				</div>
			</div>
		</div>

	 	<div class="row d-flex justify-content-center my-3">
	 		<div class="col-md-5 text-center">
		 		<button type="submit" class="btn btn-dark w-50" onClick="return findPwCheck()">비밀번호 찾기</button>
			</div>
		</div>
	</form>
</div>

<%@ include file="/bottom.jsp" %>