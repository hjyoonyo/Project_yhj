<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
member_delete.jsp<br>
<%
	int no = Integer.parseInt(request.getParameter("no"));
	System.out.println("deleteProc no : "+no);
	
	MemberDao mdao = MemberDao.getInstance();
	int cnt = mdao.deleteMemberByNo(no);
	
	String msg;
	if(cnt == 1){
		msg = "회원이 삭제되었습니다.";
	}else if(cnt == 0){
		msg = "조건에 맞는 데이터가 없습니다.";
	}else{
		msg = "삭제 실패";
	}
%>
<script type="text/javascript">
	alert("<%=msg%>");
	location.href="member.jsp";
</script>