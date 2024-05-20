<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
deleteProc.jsp<br>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	String passwd = request.getParameter("passwd");
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	
	BoardDao bdao = BoardDao.getInstance();
	int cnt = bdao.deleteArticle(num,passwd);
	
	//남아있는 레코드 갯수: 21 => 20 줄었으면 5p => 4p
	int count = bdao.getArticleCount();
	int pageBlock = 3; //화면에 띄우는 페이지 번호 갯수
	int pageSize = 5; //페이지당 열개수
	
	//총페이지 갯수
	int pageCount = count/pageSize + (count%pageSize == 0 ? 0:1);
	
	if(pageNum > pageCount){ //원래 페이지가 5 > 삭제한 뒤 총 페이지 개수 = 4
		pageNum -= 1;
		//pageNum = pageCount;
	}
	
	String msg, url;
	if(cnt == 1){
		msg = "삭제 성공";
		url = "list.jsp?pageNum="+pageNum;
	}else if(cnt == 0){
		msg = "비밀번호가 일치하지 않습니다.";
		url = "deleteForm.jsp?num="+num+"&pageNum="+pageNum;
	}else{
		msg = "삭제 오류";
		url = "deleteForm.jsp?num="+num+"&pageNum="+pageNum;
	}
%>
<script type="text/javascript">
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>
