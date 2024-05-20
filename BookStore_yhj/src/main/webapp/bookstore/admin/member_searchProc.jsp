<%@page import="my.member.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
member_searchProc.jsp<br>

<%
	request.setCharacterEncoding("UTF-8");
	String keyword = request.getParameter("keyword");
	System.out.println(keyword);
	
	if(keyword == null){
		response.sendRedirect("member.jsp");
	}else{
		MemberDao mdao = MemberDao.getInstance();
		ArrayList<MemberBean> lists = mdao.getMembersByKeyword(keyword);
		System.out.println("member_searchProc.jsp lists 개수 :"+lists.size());
		session.setAttribute("search_mlist", lists);
		response.sendRedirect("member.jsp");
	}
%>