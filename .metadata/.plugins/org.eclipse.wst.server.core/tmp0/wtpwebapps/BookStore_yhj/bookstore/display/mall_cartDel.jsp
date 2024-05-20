<%@page import="my.order.CartDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
mall_cartDel.jsp<br>

<%
	int bnum = Integer.parseInt(request.getParameter("bnum"));
	int memno = (Integer)session.getAttribute("memno");
	CartDao cdao = CartDao.getInstance();
	cdao.deleteCart(memno, bnum);	
	
	response.sendRedirect("mall_cart.jsp");
%>