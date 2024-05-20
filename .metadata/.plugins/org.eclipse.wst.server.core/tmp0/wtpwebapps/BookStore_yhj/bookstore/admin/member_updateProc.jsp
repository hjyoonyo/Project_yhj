<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
member_updateProc.jsp<br>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="mb" class="my.member.MemberBean">
	<jsp:setProperty name="mb" property="*"/>
</jsp:useBean>

<%
	String phone1 = request.getParameter("phone1");
	String phone2 = request.getParameter("phone2");
	String phone3 = request.getParameter("phone3");
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String day = request.getParameter("day");
	mb.setPhone(phone1+"-"+phone2+"-"+phone3);
	mb.setBirth(year.substring(2)+"/"+month+"/"+day);

	MemberDao mdao = MemberDao.getInstance();
	int cnt = mdao.updateMember(mb);
	
	String msg, url;
	if(cnt == 1){
		msg = "회원 정보가 수정되었습니다.";
		url = "member.jsp";
	}else if(cnt == 0){
		msg = "조건에 맞는 데이터가 없습니다.";
		url = "member_updateForm.jsp?num="+mb.getNo();
	}else{
		msg = "수정 실패";
		url = "member_updateForm.jsp?num="+mb.getNo();
	}
%>
<script type="text/javascript">
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>