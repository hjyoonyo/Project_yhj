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
		#navLeft{
			font-family: "스웨거", "나눔스퀘어 네오";
			font-weight: bold;
		}
		
		#imgbox:hover{
			opacity: 0.7;
			transition: .3s ease-in-out;
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
			</div>
	</header>
	
	<!-- nav bottom -->
	<nav class="py-2  border-bottom">
		<div class="container d-flex flex-wrap">
			<ul class="nav me-auto" id="navLeft">
				<li class="nav-item">
					<a href="<%= request.getContextPath() %>/bookstore/display/mall_cateView.jsp?cnum=2" class="nav-link link-body-emphasis px-2">문학</a>
				</li>
				<li class="nav-item">
					<a href="<%= request.getContextPath() %>/bookstore/display/mall_cateView.jsp?cnum=5" class="nav-link link-body-emphasis px-2">인문 | 사회 | 과학</a>
				</li>
				<li class="nav-item">
					<a href="<%= request.getContextPath() %>/bookstore/display/mall_cateView.jsp?cnum=15" class="nav-link link-body-emphasis px-2">그림책</a>
				</li>
<%
				if(session.getAttribute("id") != null){
%>
				<li class="nav-item">
					<a href="<%= request.getContextPath() %>/bookstore/board/list.jsp" class="nav-link link-body-emphasis px-2">커뮤니티</a>
				</li>
<%
				}
%>			
			</ul>
			<ul class="nav">
<%
				if(session.getAttribute("id") == null){
%>
				<li class="nav-item"><a href="<%= request.getContextPath() %>/bookstore/member/login.jsp"
					class="nav-link link-body-emphasis px-2">로그인</a></li>
				<li class="nav-item"><a href="<%= request.getContextPath() %>/bookstore/member/register.jsp"
					class="nav-link link-body-emphasis px-2">회원가입</a></li>
<%
				}else{
%>
<%
					if(session.getAttribute("id").equals("admin")){
%>
						<li class="nav-item"><a href="<%= request.getContextPath() %>/bookstore/admin/main.jsp"
							class="nav-link link-body-emphasis px-2">관리자 홈</a></li>
<%
					}
%>

					<li class="nav-item"><a href="<%= request.getContextPath() %>/bookstore/display/mall_cart.jsp"
						class="nav-link link-body-emphasis px-2">장바구니</a></li>
					<li class="nav-item"><a href="<%= request.getContextPath() %>/bookstore/member/logout.jsp"
						class="nav-link link-body-emphasis px-2">로그아웃</a></li>
<%
				}//else(id != null)
%>
			</ul>
		</div>
	</nav>

