<%@page import="java.text.DecimalFormat"%>
<%@page import="my.book.BookBean"%>
<%@page import="my.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String bnum = request.getParameter("bnum");
	BookDao bdao = BookDao.getInstance();
	BookBean bb = new BookBean();
	if(bnum != null){
		bb = bdao.getBookByBnum(Integer.parseInt(bnum));
	}
	DecimalFormat df = new DecimalFormat("###,###");  
%>
<%@ include file="/top.jsp" %>
<div class="container py-5 px-5">
	<h5 align="center"></h5><br>
	
	<div class="row d-flex justify-content-center">
		<!-- 이미지 영역 -->
		<div class="col-lg-5">
			<div class="col-lg-2 py-5 px-5 ratio ratio-1x1">
				<div class="div bg-body-tertiary" style="align-items:center; display: flex; justify-content:center;">
					<img src="<%= request.getContextPath() %>/resource/img/<%= bb.getBimage() %>" class="img-fluid h-75 align-middle">
				</div>
			</div>
		</div>
		<!-- 책 정보 -->
		<div class="col-lg-5">
			<form name="f" action="mall_cartAdd.jsp" method="post">
			<input type="hidden" name="bnum" value="<%= bb.getBnum() %>">
			<input type="hidden" name="price" value="<%= bb.getPrice() %>">
			<input type="hidden" name="bqty" value="<%= bb.getBqty() %>">
			<table class="table table-borderless">
				<tr>
					<td colspan="2" ><h2><b><%= bb.getTitle() %></b></h2></td>
				</tr>
				<tr>
					<td colspan="2"><font><%= df.format(bb.getPrice()) %>원</font><br><br><br><b></td>
				</tr>
				<tr class="table-light">
					<td style="vertical-align : middle;">수량</td>
					<td style="text-align : right;"><font size=2><%= bb.getTitle() %> </font> <input class="form-control form-control-sm text-center mx-1 my-1 w-25" type="text" name="oqty" value="1" onblur="oqtyCheck(this)" style="display:inline;"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align : right;">총 금액 <span id="sum"></span>원</td>
				</tr>
				<tr>
					<td class="w-50"><button type="submit" class="btn w-100 border border-secondary-subtle">장바구니</button></td>
					<td><button type="button" class="btn w-100 border text-white"  onClick="" style="background-color : #1C7D2A">바로 구매</button></td>
				</tr>
			</table>
			</form>
		</div>
	</div>

	<!-- 내용 -->
	<div class="row d-flex justify-content-center">
		<div class="col-lg-10">
			<div class="container py-5 px-5">
				<b>책 소개</b><br>
				<hr>
				<p><%= bb.getContent() %></p>
			</div>
		</div>
	</div>
	
	
	
</div>
<%@ include file="/bottom.jsp" %>
<script type="text/javascript">
	const sum = document.getElementById("sum");
	const price = f.price.value*1; //toLocaleString()은 숫자에만 적용
	
	$(function(){
		sum.innerText = price.toLocaleString();
	})
	
	function oqtyCheck(){
		var oqty = f.oqty.value; //수량
		var bqty = f.bqty.value; //재고
		regex = /^[0-9]+$/;
		
		if(!regex.test(oqty) || oqty==0){
			f.oqty.value = 1;
			sum.innerText = price.toLocaleString();
		}else if(Number(oqty) > Number(bqty)){
			alert("상품 수량이 초과되었습니다.");
			f.oqty.value = 1;
			sum.innerText = price.toLocaleString();
		}else{
			new_sum = f.price.value*oqty; //수량*가격
			sum.innerText = new_sum.toLocaleString(); //총금액 도출
		}	
	}//oqtyCheck
	
	function goOrder(pnum){
		//alert(pnum);
		var oqty = f.oqty.value;
		location.href="<%= request.getContextPath()%>/myshop/display/mall_order.jsp?pnum="+pnum+"&oqty="+oqty;
	}
	
</script>