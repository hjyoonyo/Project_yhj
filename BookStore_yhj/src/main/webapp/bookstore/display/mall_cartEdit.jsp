<%@page import="my.order.CartDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
mall_cart.jsp 수량 변경(bnum, oqty) => mall_cartEdit.jsp<br>
<jsp:useBean id="cb" class="my.order.CartBean">
	<jsp:setProperty name="cb" property="*"/>
</jsp:useBean>
<%
	//out.println(cb.getBnum()+" / "+cb.getOqty());
	int memno = (Integer)session.getAttribute("memno");
	cb.setMemno(memno);
	
	CartDao cdao = CartDao.getInstance();
	cdao.updateCart(cb);	
	
	response.sendRedirect("mall_cart.jsp");
%>
