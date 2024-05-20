<%@page import="my.book.CategoryBean"%>
<%@page import="my.book.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
cate_delete.jsp<br>

<%
	int cnum = Integer.parseInt(request.getParameter("cnum"));

	CategoryDao cdao = CategoryDao.getInstance();
	
	int cnt = cdao.deleteCategory(cnum);
	
	String msg;
	if(cnt == 1){
		msg = "카데고리가 삭제되었습니다.";
	}else{
		msg = "카테고리 삭제에 실패하였습니다.";
	}
%>
<script type="text/javascript">
	alert("<%= msg %>");
	location.href="category.jsp";
</script>
