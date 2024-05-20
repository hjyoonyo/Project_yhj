<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userE = request.getParameter("userE");
	MemberDao mdao = MemberDao.getInstance();
	boolean isDupl = mdao.searchEmail(userE);
	
	if(isDupl){
		out.print("unusable");
	}else{
		out.print("usable");
	}
%>