<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>

<div class="container py-5 px-5">
	<h3 align="center">로그인</h3><br> 
	<form action="loginProc.jsp" method="post">
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
	 			<label>비밀번호</label>
			</div>
	 		<div class="col-md-4">
				<div class="input-group mb-3">
					<input type="text" name="passwd" class="form-control">
				</div>
			</div>
		</div>

	 	<div class="row d-flex justify-content-center mb-4">
	 		<div class="col-md-5 text-end">
	 			<a href="findid.jsp" class="link-body-emphasis text-decoration-none">아이디 찾기</a> |
	 			<a href="findpw.jsp" class="link-body-emphasis text-decoration-none">비밀번호 찾기</a>
			</div>
		</div>
		
	 	<div class="row d-flex justify-content-center my-3">
	 		<div class="col-md-5">
		 		<button type="submit" class="btn btn-dark w-100" onclick="return loginCheck()">로그인</button>
			</div>
		</div>
		
	 	<div class="row d-flex justify-content-center my-3">
	 		<div class="col-md-5">
		 		<button type="button" class="btn btn-light w-100" onclick="location.href='register.jsp'">회원가입</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="/bottom.jsp" %>