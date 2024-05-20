<%@page import="my.book.BookBean"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="my.order.CartBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="my.order.CartDao"%>
<%@page import="my.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	int memno = (Integer)session.getAttribute("memno");
	
	BookDao bdao = BookDao.getInstance();
	
	CartDao cdao = CartDao.getInstance();
	ArrayList<CartBean> clist = cdao.getAllOrders();
	
	DecimalFormat df = new DecimalFormat("###,###");  
%>
<%@ include file="top.jsp" %>
<div class="container py-5 px-5">

<%
	if(clist.size() >0){
%>
	<h5 align="center">주문 내역</h5><br>
	<div class="d-flex justify-content-center">
		<div class="col-lg-10 text-center">
				<table class="table w-100 align-middle">
					<thead>
						<tr>
							<th scope="col" class="table-dark">번호</th>
							<th scope="col" class="table-dark">주문번호</th>
							<th scope="col" class="table-dark">회원</th>
							<th scope="col" class="table-dark">주문 날짜</th>
						</tr>
					</thead>
					<tbody class="table-group-divider">
<%
					int i =0;
					for(CartBean cb : clist){
						i++;
%>
						<tr>
							<td><%= i %></td>
							<td><%= cb.getOnum() %></td>
							<td><%= cb.getMemno() %></td>
							<td><%= cb.getOdate() %></td>
						</tr>
<%
					}
%>
					</tbody>
				</table>
		</div>
	</div>

<%
	}else{
%>
	<div class="d-flex justify-content-center">
		<div class="col-lg-10 text-center">
			주문 내역이 존재하지 않습니다.
		</div>
	</div>
<%
	}
%>
</div>
<%@ include file="bottom.jsp" %>