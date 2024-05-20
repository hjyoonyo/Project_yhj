<%@page import="java.sql.Timestamp"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
writeProc.jsp<br>
<%
	request.setCharacterEncoding("UTF-8");
	String pageNum = request.getParameter("pageNum");
%>

<jsp:useBean id="bb" class="board.BoardBean">
	<jsp:setProperty name="bb" property="*"/>
</jsp:useBean>
<%
	//입력한 정보 5개 + 추가로 입력해야 할 정보 reg_date, ip
	bb.setReg_date(new Timestamp(System.currentTimeMillis())); //long을 리턴 => Timestamp 객체로 생성하면 날짜형식으로 변경
	bb.setIp(request.getRemoteAddr()); //요청한 쪽의 ip주소를 가져오는 메서드
	
	BoardDao bdao = BoardDao.getInstance();
	int cnt = bdao.insertArticle(bb);
	
	String msg, url;
	if(cnt == 1){
		msg = "등록 완료";
		url = "list.jsp";
	}else{
		msg = "등록 실패";
		url = "writeForm.jsp?pageNum="+pageNum;
	}
%>
<script type="text/javascript">
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>

<!-- 
private static 변수 생성
public static 메서드 정의
생성자를 private으로 변경 -->