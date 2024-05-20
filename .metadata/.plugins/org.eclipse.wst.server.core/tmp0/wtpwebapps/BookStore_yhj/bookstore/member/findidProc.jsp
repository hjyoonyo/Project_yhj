<%@page import="my.member.MemberBean"%>
<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
findidProc.jsp<br>

<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String email = request.getParameter("email");

	MemberDao mdao = MemberDao.getInstance(); 
	MemberBean mb = mdao.findId(name, email);
	
	
	if(mb != null){
		response.sendRedirect("findidResult.jsp?id="+mb.getId());
	}
%>
<script>
	alert("입력한 정보에 일치하는 아이디가 존재하지 않습니다.");
	location.href="findid.jsp";
</script>