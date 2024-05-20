<%@page import="my.order.CartBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="my.order.CartDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
mall_orderProc.jsp<br>
<%
	int memno = (Integer)session.getAttribute("memno");
	
	CartDao cdao = CartDao.getInstance();
	ArrayList<CartBean> clist = cdao.getCartByMemno(memno);
	
	Date now = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yy-MM-dd");
	String onum = sdf.format(now)+memno;
	String odate = sdf2.format(now);
	
	int cnt = cdao.insertOrder(clist, memno, onum, odate);
	
	String msg;
	if(cnt > 0){
		msg = "주문이 완료되었습니다.";
	}else{
		msg = "주문 실패";
	}
%>
<script type="text/javascript">
	alert("<%= msg%>");
	location.href="<%= request.getContextPath() %>/main.jsp";
</script>