<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
updateProc<br>

<%
	request.setCharacterEncoding("UTF-8");
	String pageNum = request.getParameter("pageNum");
%>

<jsp:useBean id="bb" class="board.BoardBean">
	<jsp:setProperty name="bb" property="*"/>
</jsp:useBean>
<%
	BoardDao bdao = BoardDao.getInstance();
	int cnt = bdao.updateArticle(bb);
	
	int num = Integer.parseInt(request.getParameter("num"));
	out.println("updateProc.jsp num :"+num);
	
	String msg, url;
	if(cnt > 0){
		msg = "수정 완료";
		url = "content.jsp?pageNum="+pageNum+"&num="+num;
	}else if(cnt == 0){
		msg = "비밀번호가 일치하지 않습니다.";
		url = "updateForm.jsp?pageNum="+pageNum+"&num="+num;
		//url = "list.jsp?pageNum="+pageNum;
	}else{
		msg = "수정 실패";
		url = "updateForm.jsp?pageNum="+pageNum+"&num="+num;
	}
%>
<script type="text/javascript">
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>