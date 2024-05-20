<%@page import="my.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
book_delete.jsp<br>
<%
	int bnum = Integer.parseInt(request.getParameter("bnum"));
	System.out.println("delete bnum : "+bnum);
	
	BookDao bdao = BookDao.getInstance();
	int cnt = bdao.deleteBook(bnum);
	
	String msg;
	if(cnt == 1){
		msg = "책이 삭제되었습니다.";
	}else if(cnt == 0){
		msg = "조건에 맞는 데이터가 없습니다.";
	}else{
		msg = "삭제 실패";
	}
%>
<script type="text/javascript">
	alert("<%=msg%>");
	location.href="book.jsp";
</script>