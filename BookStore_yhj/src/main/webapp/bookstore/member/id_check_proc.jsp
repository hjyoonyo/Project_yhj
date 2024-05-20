<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userid = request.getParameter("userid");
	MemberDao mdao = MemberDao.getInstance();
	boolean isDupl = mdao.searchId(userid);
	
	if(isDupl){
		out.print("unusable");
	}else{
		out.print("usable");
	}
%>