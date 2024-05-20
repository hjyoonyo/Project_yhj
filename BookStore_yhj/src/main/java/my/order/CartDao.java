package my.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import my.book.BookDao;
import my.member.MemberBean;

public class CartDao {
	private static CartDao instance;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public static CartDao getInstance(){
		if(instance == null) {
			instance = new CartDao();
		}
		return instance;
	}//getInstance
	
	private CartDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/OracleDB");
			conn = ds.getConnection();
			System.out.println("접속 성공");
			
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("접속 실패1");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패2");
		}
	}//생성자
	
	public int searchCart(int memno, int bnum){
		int orgqty = -1;
		String sql = "select * from cart where memno = ? and bnum=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memno);
			ps.setInt(2, bnum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				orgqty = rs.getInt("oqty");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orgqty;
	}
	
	public int insertCart(CartBean cb) {
		int cnt = -1;
		int orgqty = searchCart(cb.getMemno(), cb.getBnum());
		
		try {
			if(orgqty > 0) { // 이미 상품 존재함
				String sql = "update cart set oqty=? where memno =? and bnum=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, cb.getOqty()+orgqty);
				ps.setInt(2, cb.getMemno());
				ps.setInt(3, cb.getBnum());
			}else {
				String sql = "insert into cart(memno, bnum, oqty) values(?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, cb.getMemno());
				ps.setInt(2, cb.getBnum());
				ps.setInt(3, cb.getOqty());
			}
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}//insertCart
	
	public ArrayList<CartBean> getCartByMemno(int memno) {
		System.out.println("CartDao memno : "+memno);
		ArrayList<CartBean> cart = new ArrayList<CartBean>();
		String sql = "select * from cart where memno = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memno);
			rs = ps.executeQuery();
			while(rs.next()) {
				CartBean cb = new CartBean();
				cb.setMemno(rs.getInt("memno"));
				cb.setBnum(rs.getInt("bnum"));
				cb.setOqty(rs.getInt("oqty"));
				cart.add(cb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cart;
	}//getCartByMemno
	
	public int updateCart(CartBean cb) {
		int cnt = -1;
		try {
			String sql = "update cart set oqty=? where memno =? and bnum=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cb.getOqty());
			ps.setInt(2, cb.getMemno());
			ps.setInt(3, cb.getBnum());
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}//updateCart
	
	public int deleteCart(int memno, int bnum){
		int cnt = -1;
		try {
			String sql = "delete cart where memno =? and bnum=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memno);
			ps.setInt(2, bnum);
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}//deleteCart
	
	public int insertOrder(ArrayList<CartBean> clist, int memno, String onum, String odate){
		int cnt = -1;
		try {
			String sql = "insert into orders(onum, memno, odate) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, onum);
			ps.setInt(2, memno);
			ps.setString(3, odate);
			
			cnt = ps.executeUpdate();
			
			if(cnt > 0) {
				cnt = 0;
				for(CartBean cb : clist) {
					sql = "insert into order_detail(odnum, onum, bnum, oqty) values(od_seq.nextval, ?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, onum);
					ps.setInt(2, cb.getBnum());
					ps.setInt(3, cb.getOqty());
					
					cnt += ps.executeUpdate();
				}//for
			}//if
			
			if(cnt == clist.size()) {
				sql = "delete cart where memno =?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, memno);
				cnt = ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}//insertOrder
	
	public ArrayList<CartBean> getAllOrders(){
		ArrayList<CartBean> lists = new ArrayList<CartBean>();
		String sql = "select * from orders";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CartBean cb = new CartBean();
				cb.setOnum(rs.getString("onum"));
				cb.setMemno(rs.getInt("memno"));
				cb.setOdate(String.valueOf(rs.getDate("odate")));
				lists.add(cb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lists;
	}//getAllOrders
	
}
