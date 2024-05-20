<%@page import="my.book.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cname = request.getParameter("cname");

	CategoryDao cdao = CategoryDao.getInstance();
	boolean isDupl = cdao.searchCname(cname);
	System.out.println("isDupl : "+isDupl);
	if(isDupl){
		out.print("isDupl");
	}else{
		out.print("notDupl");
	}
%>