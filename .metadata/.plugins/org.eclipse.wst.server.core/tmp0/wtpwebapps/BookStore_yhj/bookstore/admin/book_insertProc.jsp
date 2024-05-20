<%@page import="my.book.CategoryDao"%>
<%@page import="my.book.BookBean"%>
<%@page import="my.book.BookDao"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
book_insertProc.jsp<br>

<%
	request.setCharacterEncoding("UTF-8");

	String uploadDir = "";
	int maxSize = 1024*1024*5;
	String saveFolder = "resource/img";
	String encoding = "UTF-8";
	
	//폴더 경로 가져오기
	ServletContext sc = config.getServletContext();
	uploadDir = sc.getRealPath("/resource/img");
	System.out.println("uploadDir :"+uploadDir);
	
	MultipartRequest multi;
	multi = new MultipartRequest(request,
								uploadDir,
								maxSize,
								encoding,
								new DefaultFileRenamePolicy()
								);
	
	//저장되는 파일명 가져오기
	Enumeration files = multi.getFileNames();
	String file = (String)files.nextElement();
	String fname = multi.getFilesystemName(file);
	
	//원래의 파일명 가져오기
	String orgname = multi.getOriginalFileName("bimage");
	
	String requestDir = request.getContextPath()+"/"+saveFolder;
	String fullpath = requestDir+"/"+fname;
	System.out.println("fullpath :"+fullpath);
	
	String cname = multi.getParameter("cname");
	CategoryDao cdao = CategoryDao.getInstance();
	int cnum = cdao.getCategoryByCname(cname).getCnum();

	BookBean pb = new BookBean();
	BookDao pdao = BookDao.getInstance();
	
	int cnt = pdao.insertBook(multi, cnum);
	
	String msg, url;
	if(cnt>0){
		msg="책이 등록되었습니다.";
		url="book.jsp";
	}else{
		msg="책 등록 실패";
		url="book_insert.jsp";
	}
	
%>
<script type="text/javascript">
	alert("<%= msg %>");
	location.href="<%= url %>";
</script>
