<%@page import="my.book.CategoryBean"%>
<%@page import="my.book.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
cate_updateProc.jsp<br>

<%
	request.setCharacterEncoding("UTF-8");
	
	String cname_upper = request.getParameter("cname_upper");
	String cname = request.getParameter("cname");
	String code = request.getParameter("code");
	int cnum = Integer.parseInt(request.getParameter("cnum"));
	System.out.println(cname_upper+"/"+cname+"/"+code);
	
	int lev, ref;
	CategoryDao cdao = CategoryDao.getInstance();
	
	if(cname_upper.equals("없음")){
		lev = 1;
		ref=0;
	}else{
		CategoryBean cb = cdao.getCategoryByCname(cname_upper);
		lev = cb.getLev()+1;
		ref = cb.getCnum();
	}
	
	int cnt = cdao.updateCategory(cnum, cname, code, lev, ref);
	//lev, ref
	//없음 => lev=1, ref=currval
	//문학 => lev=문학+1, ref=문학no
	//소설 => lev=소설+1, ref=소설no
	
	String msg;
	if(cnt == 1){
		msg = "카데고리가 수정되었습니다.";
	}else{
		msg = "카테고리 수정에 실패하였습니다.";
	}
%>
<script type="text/javascript">
	alert("<%= msg %>");
	location.href="category.jsp";
</script>
