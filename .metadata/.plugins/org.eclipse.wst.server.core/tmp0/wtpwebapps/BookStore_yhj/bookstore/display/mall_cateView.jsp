<%@page import="java.text.DecimalFormat"%>
<%@page import="my.book.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="my.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <style>
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
	int cnum =  Integer.parseInt(request.getParameter("cnum"));
	
	BookDao bdao = BookDao.getInstance();
	ArrayList<BookBean> list = bdao.getBookByCnumLev1(cnum);
	
	DecimalFormat df = new DecimalFormat("###,###");  
%>

<%@ include file="/top.jsp" %>
<div class="container py-3 px-5">
<%
		if(list.size() == 0){
%>		
			<div class="d-flex justify-content-center py-5">
				<div class="col-lg-10 text-center">
					카테고리에 책이 존재하지 않습니다.
				</div>
			</div>
<%
		}else{
%>

		<div class="row row-cols-1 row-cols-md-3 g-4">
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
				<div class="container">
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
<%
	}
%>
		
</div>
	
<%@ include file="/bottom.jsp" %>