<%@page import="my.book.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String code = request.getParameter("code");

	CategoryDao cdao = CategoryDao.getInstance();
	boolean isDupl = cdao.searchCode(code);
	
	if(isDupl){
		out.print("isDupl");
	}else{
		out.print("notDupl");
	}
%>