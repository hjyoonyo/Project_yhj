<%@page import="my.book.BookBean"%>
<%@page import="my.order.CartBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="my.order.CartDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="my.book.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	int memno = (Integer)session.getAttribute("memno");
	
	BookDao bdao = BookDao.getInstance();
	
	CartDao cdao = CartDao.getInstance();
	ArrayList<CartBean> clist = cdao.getCartByMemno(memno);
	
	DecimalFormat df = new DecimalFormat("###,###");  
%>
<%@ include file="/top.jsp" %>
<div class="container py-5 px-5">

<%
	if(clist.size() >0){
%>
	<h5 align="center">장바구니</h5><br>
	<div class="d-flex justify-content-center">
		<div class="col-lg-10 text-center">
			<form name="f">
				<table class="table w-100 align-middle">
					<thead>
						<tr>
							<th scope="col" class="table-dark">번호</th>
							<th scope="col" class="table-dark" colspan="2">상품명</th>
							<th scope="col" class="table-dark">수량</th>
							<th scope="col" class="table-dark">판매가/포인트</th>
							<th scope="col" class="table-dark">소계</th>
							<th scope="col" class="table-dark">삭제</th>
						</tr>
					</thead>
					<tbody class="table-group-divider">
<%
					int i =0;
					int sum = 0;
					for(CartBean cb : clist){
						i++;
						BookBean bb = bdao.getBookByBnum(cb.getBnum());
						sum += bb.getPrice()*cb.getOqty();
%>
						<tr>
							<td><%= i %></td>
							<td>
								<div class="container-fluid py-5 px-5 ratio ratio-1x1" onclick="location.href='mall_bookView.jsp?bnum=<%= bb.getBnum() %>'" style="cursor:pointer;">
									<div class="div bg-secondary-subtle" style="align-items:center; display: flex; justify-content:center;">
										<img src="<%= request.getContextPath() %>/resource/img/<%= bb.getBimage() %>" class="img-fluid h-75 align-middle">
									</div>
								</div>
							</td>
							<td>
								<a href="mall_bookView.jsp?bnum=<%= bb.getBnum() %>" class="link-body-emphasis text-decoration-none">
								<%= bb.getTitle() %>
								</a>
							</td>
							<td>
								<input type="hidden" name="orgqty" value="<%= cb.getOqty() %>">
								<input type="hidden" name="bqty" value="<%= bb.getBqty() %>">
								<input type="hidden" name="bnum" value="<%= bb.getBnum() %>">
								<input class="form-control form-control-sm text-center mx-1 my-1" type="text" name="oqty" value="<%= cb.getOqty() %>" size="1">
								<input type="button" class="btn btn-sm border border-secondary-subtle"  value="변경" onClick="oqtyCheck('<%= i %>')">
							</td>
							<td><%= df.format(bb.getPrice()) %>원</td>
							<td><%= df.format(bb.getPrice()*cb.getOqty()) %>원</td>
							<td>
								<a href="mall_cartDel.jsp?bnum=<%= bb.getBnum() %>" class="link-body-emphasis text-decoration-none">
								삭제
								</a>
							</td>
						</tr>
<%
					}
%>
						<tr class="table-dark">
							<td colspan="7" class="text-white"  style="text-align : right;">총액 <%= df.format(sum) %>원</td>
						</tr>
					</tbody>
				</table>
				<button type="button" class="btn w-25 border border-secondary-subtle py-2" onClick="location.href='mall_order.jsp'"><font style="font-size: 1.1rem; font-weight: bold;">주문하기</font></button>
			</form>
		</div>
	</div>

<%
	}else{
%>
	<div class="d-flex justify-content-center">
		<div class="col-lg-10 text-center">
			장바구니가 비어 있습니다.
		</div>
	</div>
<%
	}
%>
</div>
<%@ include file="/bottom.jsp" %>
<script type="text/javascript">
	function oqtyCheck(i){
		var index = Number(i)-1;
		var arr_orgqty = document.getElementsByName("orgqty"); // 원래 수량[]
		var arr_oqty = document.getElementsByName("oqty"); // 수량[]
		var arr_bqty = document.getElementsByName("bqty"); //재고[]
		var arr_bnum = document.getElementsByName("bnum"); //책번호[]
		regex = /^[0-9]+$/;
		//alert(arr_oqty[index].value+"/"+arr_bqty[index].value);
		var orgqty = arr_orgqty[index].value;
		var oqty = arr_oqty[index].value;
		var bqty = arr_bqty[index].value;
		var bnum = arr_bnum[index].value;

		if(!regex.test(oqty) || oqty==0){
			arr_oqty[index].value = 1;
			return;
		}else if(Number(oqty) > Number(bqty)){
			alert("상품 수량이 초과되었습니다.");
			arr_oqty[index].value = 1;
			return;
		}else if(Number(oqty) == Number(orgqty)){
			alert("동일한 수량입니다.");
			return;
		}
		location.href="mall_cartEdit.jsp?oqty="+oqty+"&bnum="+bnum;
	}//oqtyCheck
</script>