<%@page import="my.member.MemberBean"%>
<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
loginProc.jsp<br>

<%
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	//System.out.println("id : "+id+" passwd : "+passwd+"\n");
	
	MemberDao mdao = MemberDao.getInstance(); 
	MemberBean mb = mdao.getMemberById(id, passwd);
	
	if(mb != null){ //id가 존재함
		session.setAttribute("id", id);
		session.setAttribute("memno", mb.getNo());
		
		if(id.equals("admin")){ //관리자
			response.sendRedirect(request.getContextPath()+"/bookstore/admin/main.jsp");
		}else{ //사용자
			response.sendRedirect(request.getContextPath()+"/main.jsp");
		}
	}else{
%>
<script type="text/javascript">
	alert("아이디 또는 비밀번호가 일치하지 않습니다.");
	location.href="<%=request.getContextPath() %>/bookstore/member/login.jsp";
</script>
<%
	}
	
	//session.setAttribute("id", id);
%>
