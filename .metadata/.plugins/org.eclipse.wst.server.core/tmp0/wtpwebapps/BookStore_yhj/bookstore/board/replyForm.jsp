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
</style>
<%
	int ref = Integer.parseInt(request.getParameter("ref"));
	int re_step = Integer.parseInt(request.getParameter("re_step"));
	int re_level = Integer.parseInt(request.getParameter("re_level"));
	String pageNum = request.getParameter("pageNum");
	System.out.println("부모 ref :"+ref+" re_step :"+re_step+" re_level :"+re_level);
%>
<div class="container py-5 px-5">
	<div class="row d-flex justify-content-center">
		<div class="col-xs-8 table-responsive">
		<form name="myform" action="replyProc.jsp" method="post">
			<input type="hidden" name="ref" value="<%= ref %>">
			<input type="hidden" name="re_step" value="<%= re_step %>">
			<input type="hidden" name="re_level" value="<%= re_level %>">
			<input type="hidden" name="pageNum" value="<%= pageNum %>">
			<table class="table w-100">
				<tr>
					<th align="right" colspan="2"><a href="list.jsp"  class="link-body-emphasis text-decoration-none">글목록</a></th>
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
						<input type="text" name="subject" maxlength="10" value="[답글]">
					</td>
				</tr>
				<tr>
					<th>Email</th>
					<td align="left">
						<input type="text" name="email" maxlength="10">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td align="left">
						<textarea rows="10" cols="50" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td align="left">
						<input type="password" name="passwd">
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input class="btn btn-light" type="submit" value="답글쓰기">
						<input class="btn btn-light" type="button" value="목록보기" onclick="location.href='list.jsp?pageNum=<%= pageNum %>'">
					</th>
				</tr>
			</table>
		</form>
		</div>
	</div>
</div>
<%@ include file="/bottom.jsp" %>