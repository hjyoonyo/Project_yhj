<%@page import="my.member.MemberBean"%>
<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
findpwProc.jsp<br>

<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String email = request.getParameter("email");

	MemberDao mdao = MemberDao.getInstance(); 
	MemberBean mb = mdao.findPw(id, name, email);
	
	if(mb != null){
		response.sendRedirect("findpwResult.jsp?passwd="+mb.getPasswd());
	}
%>
<script>
	alert("입력한 정보에 일치하는 회원이 존재하지 않습니다.");
	location.href="findpw.jsp";
</script>