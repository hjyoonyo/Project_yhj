<%@page import="my.member.MemberBean"%>
<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
logout.jsp<br>

<%
	session.invalidate();
	response.sendRedirect(request.getContextPath()+"/main.jsp");
%>
