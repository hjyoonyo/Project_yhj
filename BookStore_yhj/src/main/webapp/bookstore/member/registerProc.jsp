<%@page import="java.awt.Window"%>
<%@page import="my.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
registerProc.jsp<br>
<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="mb" class="my.member.MemberBean">
	<jsp:setProperty name="mb" property="*" />
</jsp:useBean>

<%
	String phone1 = request.getParameter("phone1");
	String phone2 = request.getParameter("phone2");
	String phone3 = request.getParameter("phone3");
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String day = request.getParameter("day");
	String addr = request.getParameter("adress");
	String detailAddress = request.getParameter("detailAddress");
	String postcode = request.getParameter("postcode");

	mb.setPhone(phone1+"-"+phone2+"-"+phone3);
	mb.setBirth(year.substring(2)+"/"+month+"/"+day);
	mb.setAddress(addr+" "+detailAddress+" ("+postcode+")");
	
	MemberDao mdao = MemberDao.getInstance();
	
	int cnt = mdao.insertMember(mb);
	
	String url, msg;
	if(cnt == 1){
		url = request.getContextPath()+"/main.jsp";
		msg = "회원가입 되었습니다.";
	}else{
		url = request.getContextPath()+"/bookstore/member/register.jsp";
		msg = "회원가입에 실패하였습니다.";
	}
%>
<script type="text/javascript">
	alert("<%= msg %>");
	location.href="<%= url %>";
</script>