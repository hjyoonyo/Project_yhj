<%@page import="my.order.CartDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
mall_cart.jsp => mall_cartAdd.jsp<br>
<jsp:useBean id="cb" class="my.order.CartBean">
	<jsp:setProperty name="cb" property="*"/>
</jsp:useBean>
<%
	int memno = (Integer)session.getAttribute("memno");
	cb.setMemno(memno);
	
	CartDao cdao = CartDao.getInstance();
	int cnt = cdao.insertCart(cb);
	
	String msg, url;
	if(cnt>0){
		msg="장바구니에 상품이 담겼습니다.";
		url="mall_cart.jsp";
	}else{
		msg="장바구니 담기 실패";
		url="mall_bookView.jsp?bnum="+cb.getBnum();
	}
	
%>
<script type="text/javascript">
	alert("<%= msg %>");
	location.href="<%= url %>";
</script>
