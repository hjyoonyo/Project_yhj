<%@page import="java.text.DecimalFormat"%>
<%@page import="my.book.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="my.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
		/* 캐러셀 */
		.carousel-inner {
			width : auto;
			height: 500px;
		}
		
		.carousel-item{
			width : auto;
			height : 100%;
		}
		.d-block {
			width : auto;
			height : 100%;
		}
		
		/* 카드 */
		.card {
			padding: 10px;
			align-items: center;
		}
		.card img{
			width : 100px;
			height : 100%;
		}
</style>
<%
	request.setCharacterEncoding("UTF-8");
	BookDao bdao = BookDao.getInstance();
	
	ArrayList<BookBean> list = bdao.getAllBook();
	
	DecimalFormat df = new DecimalFormat("###,###");  
%>
<%@ include file="top.jsp" %>


	<!-- 캐러셀 -->
	<div class="container py-3 px-5 row d-flex justify-content-center">
		<div id="carouselExampleAutoplaying" class="carousel slide"
			data-bs-ride="carousel">
			<!-- 하단버튼 -->
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleAutoplaying"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleAutoplaying"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
			</div>
			<!-- 이미지 -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="resource/img/main_banner1.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="resource/img/main_banner2.jpg" class="d-block w-100" alt="...">
				</div>
			</div>
			<!-- 좌우버튼 -->
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span> 
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	</div>
	
	<!-- content -->
	<div class="container py-3 px-5">
		<div class="row row-cols-1 row-cols-md-3 g-4 py-5 px-5">
<%
	for(BookBean book : list){
%>
		<div class="col">
			<div class="py-5 px-5 ratio ratio-1x1">
				<div id="imgbox" class="div bg-body-tertiary"	style="align-items: center; display: flex; justify-content: center;">
					<a href="<%=request.getContextPath()%>/bookstore/display/mall_bookView.jsp?bnum=<%= book.getBnum() %>" class="text-decoration-none">
						<img	src="<%=request.getContextPath()%>/resource/img/<%=book.getBimage()%>" class="img-fluid h-75 align-middle">
					</a>
				</div>
			</div>
			<div class="container py-2">
				<p class="card-text">
					<a href="<%=request.getContextPath()%>/bookstore/display/mall_bookView.jsp?bnum=<%= book.getBnum() %>" class="link-body-emphasis text-decoration-none">
						<font style="font-size: 1.1rem; font-weight: bold;"><%=book.getTitle()%></font>
					</a><br>
						<%= df.format(book.getPrice()) %>원<br><br>
				</p>
			</div>
		</div>
<%
	}
%>
		</div>
	</div>

<%@ include file="bottom.jsp" %>