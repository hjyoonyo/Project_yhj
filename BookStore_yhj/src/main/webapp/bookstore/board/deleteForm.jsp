<%@page import="board.BoardBean"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="color.jsp" %>
<%@ include file="/top.jsp" %>
<style type="text/css">
	body{
		text-align : center;
	}
	table{
		margin : auto;
		text-align : center;
	}
	th{
		background : <%=value_c%>
	}
	
</style>
<script>
	function check(){
		if(delform.passwd.value == ""){
			alert("비밀번호를 입력하세요.");
			delform.passwd.focus();
			return false;
		}
	}
</script>
<%
	String pageNum = request.getParameter("pageNum");
	int num = Integer.parseInt(request.getParameter("num"));
	System.out.println("deleteForm.jsp num :"+num);
	
%>
deleteForm.jsp<br>

<body>
	<h4>글삭제</h4>
	<form name="delform" action="deleteProc.jsp" method="post">
		<input type="hidden" name="num" value="<%= num %>">
		<input type="hidden" name="pageNum" value="<%= pageNum %>">
		<table border="1" width="400">
			<tr>
				<th>비밀번호를 입력해주세요.</th>
			</tr>
			<tr>
				<td>
				비밀번호 : <input type="password" name="passwd" size="10">
				</td>
			</tr>
			<tr>
				<th>
					<input type="submit" value="글삭제" onclick="return check()">
					<input type="button" value="글목록" onclick="location.href='list.jsp?pageNum=<%= pageNum %>'">
				</th>
			</tr>
		</table>
	</form>
</body>
<%@ include file="/bottom.jsp" %>