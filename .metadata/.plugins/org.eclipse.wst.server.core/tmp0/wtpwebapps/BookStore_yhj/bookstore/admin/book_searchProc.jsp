<%@page import="my.book.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="my.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
book_searchProc.jsp<br>

<%
	request.setCharacterEncoding("UTF-8");
	String keyword = request.getParameter("keyword");
	System.out.println(keyword);
	
	if(keyword == null){
		response.sendRedirect("book.jsp");
	}else{
		BookDao mdao = BookDao.getInstance();
		ArrayList<BookBean> lists = mdao.getBookByKeyword(keyword);
		System.out.println("book_searchProc.jsp lists 개수 :"+lists.size());
		session.setAttribute("search_blist", lists);
		response.sendRedirect("book.jsp");
	}
%>