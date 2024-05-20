<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="my.book.CategoryBean"%>
<%@page import="my.book.CategoryDao"%>
<%@page import="my.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
book_updateProc.jsp<br>

<%
	request.setCharacterEncoding("UTF-8");

	String uploadDir = "";
	uploadDir = config.getServletContext().getRealPath("/resource/img");
	System.out.println("uploadDir :"+uploadDir);
	
	MultipartRequest mr;
	mr = new MultipartRequest(request,
							uploadDir,
							1024*1024*5,
							"UTF-8",
							new DefaultFileRenamePolicy()
								); 
	/* System.out.println("mr.getParameter('pimage') :"+mr.getParameter("pimage"));*/
	//항상 null임
	
	/*  
	기존이미지O, 새이미지O => 기존이미지 폴더에서 삭제, 새이미지 업로드
	기존이미지O, 새이미지X => 기존이미지 그대로 사용
	기존이미지X, 새이미지O => 새이미지 업로드
	기존이미지X, 새이미지X => X
	*/
	
	String orgbimage = mr.getParameter("orgbimage"); //기존이미지
	//원래의 파일명이 아니라 실제로 저장되는 파일명 가져오기
	String bimg = (String)mr.getFileNames().nextElement();
	String bimage = mr.getFilesystemName(bimg); //새이미지
	System.out.println("기존이미지 : "+orgbimage+", 새이미지 :"+bimage);
	String img = null;
	
	String configFolder = config.getServletContext().getRealPath("/myshop/images"); //request.getContextPath는 안됐던 것 같음
	System.out.println("configFolder :"+configFolder);
	
	if(orgbimage == null){ //기존x
		if(bimage != null){ //새o
			img = bimage;
		}
	}else if(orgbimage != null){ //기존o
		if(bimage == null){//새x
			img = orgbimage;
		}else if(bimage != null){//새o
			img = bimage;
			File delfile = new File(configFolder, orgbimage);
			delfile.delete();
		}
	}
	System.out.println("img : "+img);
	
	BookDao bdao = BookDao.getInstance();
	int cnt = bdao.updateBook(mr, img);
	
	String msg, url;
	if(cnt>0){
		msg="책이 수정되었습니다.";
		url="book.jsp";
	}else{
		msg="수정 실패";
		url="book_updateForm.jsp?bnum="+mr.getParameter("bnum");
	}
	
%>
<script type="text/javascript">
	alert("<%= msg %>");
	location.href="<%= url %>";
</script>