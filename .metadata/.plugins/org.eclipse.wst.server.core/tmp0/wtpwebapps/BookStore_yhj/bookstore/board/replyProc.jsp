<%@page import="java.sql.Timestamp"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
replyForm.jsp(입력(5)+부모(3)) => replyProc.jsp<br>

<%
	request.setCharacterEncoding("UTF-8");
	String pageNum = request.getParameter("pageNum");
%>
<jsp:useBean id="bb" class="board.BoardBean">
	<jsp:setProperty name="bb" property="*"/>
</jsp:useBean>
<%
	String ip = request.getRemoteAddr();
	bb.setIp(ip);
	
	bb.setReg_date(new Timestamp(System.currentTimeMillis()));
	
	System.out.println("replyProc.jsp ref="+bb.getRef());
	BoardDao bdao = BoardDao.getInstance();
	bdao.replyArticle(bb);
	/* int cnt = bdao.replyArticle(bb);
	
	if(cnt == 1){
		response.sendRedirect("list.jsp");
	}else{
		response.sendRedirect("replyForm.jsp");
	} */
%>
<jsp:forward page="list.jsp?pageNum='<%= pageNum %>'" />