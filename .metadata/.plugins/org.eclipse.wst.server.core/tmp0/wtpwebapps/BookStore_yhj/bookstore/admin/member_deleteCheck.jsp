<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
member_deleteCheck.jsp<br>

<%
	String[] rcheck = request.getParameterValues("rowcheck");
	int[] rowcheck = new int[rcheck.length];
	
	for(int i=0; i<rcheck.length; i++){
		rowcheck[i] = Integer.parseInt(rcheck[i]);
	}
	
	MemberDao mdao = MemberDao.getInstance();
	int cnt = mdao.deleteCheckByNo(rowcheck);
	
	String msg;
	if(cnt == rowcheck.length){
		msg = "삭제되었습니다.";
	}else if(cnt == 0){
		msg = "조건에 맞는 데이터가 없습니다.";
	}else if(cnt > 0){
		msg = cnt+"개의 데이터가 삭제되었습니다.";
	}else{
		msg = "삭제 실패";
	}
%>
<script type="text/javascript">
	alert("<%=msg%>");
	location.href="member.jsp";
</script>