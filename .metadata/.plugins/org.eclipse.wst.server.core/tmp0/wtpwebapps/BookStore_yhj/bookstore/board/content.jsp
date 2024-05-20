<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.BoardBean"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="color.jsp" %>
<%@ include file="/top.jsp" %>
<style type="text/css">
	body{
		text-align: center;
	}
	table{
		text-align: center;
		margin : 5px auto;
	}
	th, td{
		padding : 3px;
	}
	th{
		background : <%=value_c%>;
	}
</style>
<%
	String pageNum = request.getParameter("pageNum");
	int num = Integer.parseInt(request.getParameter("num"));
	
	BoardDao bdao = BoardDao.getInstance();
	
	BoardBean bb = bdao.getArticle(num);
	System.out.println("content.jsp ref="+bb.getRef());
	
	SimpleDateFormat sdf = new SimpleDateFormat();
%>
<div class="container py-5 px-5">
	<div class="row d-flex justify-content-center h-100">
		<div class="col-xs-5 table-responsive">
			<table class="table w-100">
				<tr>
					<th width="100">글번호</th>
					<td width="200"><%= bb.getNum()%></td>
					<th width="100">조회수</th>
					<td width="200"><%= bb.getReadcount()%></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><%= bb.getWriter()%></td>
					<th>작성일</th>
					<td><%= sdf.format(bb.getReg_date())%></td>
				</tr>
				<tr>
					<th>글제목</th>
					<td colspan="3"><%= bb.getSubject()%></td>
				</tr>
				<tr>
					<th height="100">글내용</th>
					<td colspan="3"><%= bb.getContent()%></td>
				</tr>
				<tr>
					<th colspan="4" align="right">
<%
						String id = (String)session.getAttribute("id");
						if(id.equals("admin")){
%>
						<input class="btn btn-light"  type="button" value="글수정" onClick="location.href='updateForm.jsp?num=<%=bb.getNum()%>&pageNum=<%= pageNum %>'">
						<input class="btn btn-light" type="button" value="글삭제" onClick="location.href='deleteForm.jsp?num=<%=bb.getNum()%>&pageNum=<%= pageNum %>'">
						<input class="btn btn-light" type="button" value="답글쓰기" onClick="location.href='replyForm.jsp?ref=<%=bb.getRef()%>&re_step=<%=bb.getRe_step()%>&re_level=<%=bb.getRe_level()%>&pageNum=<%= pageNum %>'">
<%
						}
%>
						<input class="btn btn-light" type="button" value="글목록" onclick="location.href='list.jsp?pageNum=<%= pageNum %>'">
					</th>
				</tr>
			</table>
		</div>
	</div>
</div>
<%@ include file="/bottom.jsp" %>