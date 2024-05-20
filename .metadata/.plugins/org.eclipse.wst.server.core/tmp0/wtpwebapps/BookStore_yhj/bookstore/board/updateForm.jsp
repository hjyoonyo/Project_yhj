<%@page import="board.BoardBean"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="color.jsp" %>
<%@ include file="/top.jsp" %>
<style type="text/css">
	table{
		margin : auto;
	}
	th{
		padding : 3px;
		background : <%=value_c%>;
	}
	td:first-child{
		text-align: center;
	}
</style>
<%
	String pageNum = request.getParameter("pageNum");
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDao bdao = BoardDao.getInstance();
	BoardBean bb = bdao.getArticleByNum(num);
%>
<script type="text/javascript">
	function check(){
		if(document.myform.passwd.value == ""){
			alert("비밀번호를 입력하세요.");
			document.myform.passwd.focus();
			return false;
		}else if(document.myform.passwd.value != "<%= bb.getPasswd()%>"){
			alert("비밀번호가 일치하지 않습니다.");
			document.myform.passwd.select();
			return false;
		}
	}
</script>
<div class="container py-5 px-5">
	<div class="row d-flex justify-content-center h-100">
		<div class="col-xs-8 table-responsive">
		<form name="myform" action="updateProc.jsp?pageNum=<%= pageNum %>&num=<%= num %>" method="post">
			<table class="table w-100">
				<tr>
					<th align="right" colspan="2"><a href="list.jsp" class="link-body-emphasis text-decoration-none">글목록</a></th>
				</tr>
				<tr>
					<th>아이디</th>
					<td align="left">
						<%= session.getAttribute("id")%>
						<input type="hidden" name="writer" value="<%=  session.getAttribute("id")%>">
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td align="left">
						<input type="text" name="subject" maxlength="10" value="<%= bb.getSubject()%>">
					</td>
				</tr>
				<tr>
					<th>Email</th>
					<td align="left">
						<input type="text" name="email" maxlength="10" value="<%= bb.getEmail()%>">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td align="left">
						<textarea rows="10" cols="50" name="content"><%= bb.getContent()%></textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td align="left">
						<input type="password" name="passwd"  value="<%= bb.getPasswd()%>">
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input class="btn btn-light" type="submit" value="글수정" onclick="return check()">
						<input class="btn btn-light" type="button" value="목록보기" onclick="location.href='list.jsp?pageNum=<%= pageNum %>'">
					</th>
				</tr>
			</table>
		</form>
		</div>
	</div>
</div>
<%@ include file="/bottom.jsp" %>