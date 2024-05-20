package my.order;

public class CartBean {
	//order, order_detail 테이블의 값도 여기서 받아온다
	int memno;
	int bnum;
	int oqty;
	String onum;
	String odate;
	
	public int getMemno() {
		return memno;
	}
	public void setMemno(int memno) {
		this.memno = memno;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public int getOqty() {
		return oqty;
	}
	public void setOqty(int oqty) {
		this.oqty = oqty;
	}
	public String getOnum() {
		return onum;
	}
	public void setOnum(String onum) {
		this.onum = onum;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	
}
