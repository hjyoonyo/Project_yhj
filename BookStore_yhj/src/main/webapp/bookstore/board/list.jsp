<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.BoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/top.jsp" %>
<%@ include file="color.jsp" %>
<%
	int pageSize = 5; //페이지당 보여줄 row 개수
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String pageNum = request.getParameter("pageNum"); //선택한 페이지 번호를 문자열로 가져옴
	if(pageNum == null){
		pageNum = "1";
	}
	
	int currentPage = Integer.parseInt(pageNum); //현재페이지=내가 클릭한 페이지의 번호를 정수로 변환
	int startRow = (currentPage - 1)*pageSize + 1; //현재페이지의 시작번호
	int endRow = currentPage*pageSize; //현재페이지의 끝번호
	
	int count = 0;
	int number = 0;
	
	BoardDao bdao = BoardDao.getInstance();
	count = bdao.getArticleCount(); //전체 레코드 개수
	System.out.println("count:"+count);
	ArrayList<BoardBean> articleLists = bdao.getAllArticle(startRow,endRow);
	//articleLists.size(); 전체 갯수가 아니라 최대 10개만 불러옴.
	
	number = count - (currentPage-1)*pageSize;
	//number = 전체레코드 개수 - (현재페이지-1)*페이지당 레코드 개수
%>


<div class="container py-5 px-5">
	<h3 align="center">문의 게시판 <font size=4>(전체 글: <%=count %>)</font></h3>
	<div class="row d-flex justify-content-center h-100">
		<div class="col-xs-8 table-responsive">
			<a href="writeForm.jsp?pageNum=<%= pageNum %>" class="link-body-emphasis text-decoration-none">글쓰기</a>

<%
	if(count == 0){ //레코드가 없으면
%>
	<table class="table table-hover w-100">
		<tr>
			<td align="center">게시판에 저장된 글이 없습니다.</td>
		</tr>
	</table>
<%
	}else{ //레코드가 있으면
%>

	<table class="table table-hover w-100">
		<thead>
			<tr>
				<th scope="col" class="table-dark">번호</th>
				<th scope="col" class="table-dark">제	목</th>
				<th scope="col" class="table-dark">작성자</th>
				<th scope="col" class="table-dark">작성일</th>
				<th scope="col" class="table-dark">조회</th>
			</tr>
		</thead>
			<tbody class="table-group-divider">
<%
				for(BoardBean b : articleLists){
%>
				<tr>
					<td><%= number-- %></td>	
					<td align="left">
					<%
					int wid;
					 if(b.getRe_level() > 0){ //답글만 적용. 원글은 이미지삽입x
						 wid = 20 * b.getRe_level(); // 20 40 60 ... 
					%>
						<img src="images/level.gif" width="<%=wid%>" height="15">
						<img src="images/re.gif" height="15">
					<%}//if문 %>
					<a href="content.jsp?num=<%=b.getNum()%>&pageNum=<%= pageNum %>" class="link-body-emphasis text-decoration-none"><%= b.getSubject()%></a>
					</td>	
					<td><%= b.getWriter()%></td>	
					<td><%= sdf.format(b.getReg_date())%></td>	
					<td><%= b.getReadcount()%></td>	
				</tr>
<%
	}//for
%>
			</tbody>
		</table>
<%
}//else
%>
		</div>
		<div class="row d-flex justify-content-center">
		<div class="col-xs-8 text-center">
<%
	//페이지 설정
	if(count > 0){
		int pageCount = count/pageSize + (count%pageSize == 0 ? 0 : 1);
		//pageCount 전체 페이지 수.  row가 7개 : pageCount = 0 + 1 = 1
		int pageBlock = 3; //페이지 번호 => "<이전> 1-10 <다음>" 이거 설정
		int startPage = ((currentPage-1) / pageBlock*pageBlock) + 1;
		//
		int endPage = startPage + pageBlock -1;
		if(endPage > pageCount){ //전체페이지가 11 계산된 끝페이지가 12 => 끝페이지 = 전체페이지
			endPage = pageCount;
		}
		
		if(startPage > 3){
%>
			<a href="list.jsp?pageNum=<%= startPage-3 %>">[이전]</a>
<%
		}
		for(int i=startPage; i<=endPage ; i++){
%>
			<a href="list.jsp?pageNum=<%= i %>"> <%if(pageNum.equals(String.valueOf(i))){%><font color="<%=bar%>"><%} %> [<%= i %>]</font></a>
<%
		}
		if(endPage < pageCount){
%>
			<a href="list.jsp?pageNum=<%= startPage+3 %>">[다음]</a>
<%
		}
	}
%>
			</div>
		</div>
	</div>
</div>

<%@ include file="/bottom.jsp" %>