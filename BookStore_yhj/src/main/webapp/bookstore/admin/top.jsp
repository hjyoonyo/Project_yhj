<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Main</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
	<style type="text/css">
		body{
			font-family: "Noto Sans KR", "Noto Sans", "나눔스퀘어 네오";
		}
		#nav{
			font-family: "스웨거", "나눔스퀘어 네오";
			font-weight: bold;
		}
	</style>
	
</head>
<body>
<div class="container w-75">
	<!-- nav top -->
	<header class="py-3">
		<div class="container d-flex flex-wrap justify-content-center">
			<!-- 로고 -->
			<a href="<%= request.getContextPath() %>/main.jsp"
				class="d-flex align-items-center mb-3 mb-lg-0 me-lg-auto link-body-emphasis text-decoration-none">
				<img src="<%= request.getContextPath() %>/resource/icon/tree-solid.svg" width="30" height="30"> 
				<span class="fs-4 w-25"><img src="<%= request.getContextPath() %>/resource/img/어쩌다산책_본명조.png" alt="어쩌다산책" class="img-fluid"></span>
			</a>
			<!-- 검색 -->
				
		</div>
	</header>
	
	<!-- nav bottom -->
	<nav class="py-2  border-bottom" id="nav">
		<div class="container d-flex flex-wrap">
			<ul class="nav me-auto">
				<li class="nav-item">
					<a href="category.jsp" class="nav-link link-body-emphasis px-2">카테고리</a>
				</li>
				<li class="nav-item">
					<a href="book.jsp" class="nav-link link-body-emphasis px-2">책</a>
				</li>
				<li class="nav-item">
					<a href="member.jsp" class="nav-link link-body-emphasis px-2">회원</a>
				</li>
				<li class="nav-item">
					<a href="order.jsp" class="nav-link link-body-emphasis px-2">주문</a>
				</li>
				<li class="nav-item">
					<a href="<%= request.getContextPath() %>/bookstore/board/list.jsp" class="nav-link link-body-emphasis px-2">커뮤니티</a>
				</li>
				<li class="nav-item">
					<a href="<%= request.getContextPath() %>/main.jsp"" class="nav-link link-body-emphasis px-2">사용자 홈</a>
				</li>
			</ul>
		</div>
	</nav>

