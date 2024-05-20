package my.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CategoryDao {
	private static CategoryDao instance;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public static CategoryDao getInstance(){
		if(instance == null) {
			instance = new CategoryDao();
		}
		return instance;
	}//getInstance
	
	private CategoryDao() {
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
	
	public ArrayList<CategoryBean> getAllCategory() {
		ArrayList<CategoryBean> lists = new ArrayList<CategoryBean>();
		String sql = "select * from category order by cnum";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setCnum(rs.getInt("cnum"));
				cb.setCode(rs.getString("code"));
				cb.setCname(rs.getString("cname"));
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
	}//getAllCategory
	
	
	public ArrayList<CategoryBean> getCategoryByLev(int lev){
		ArrayList<CategoryBean> lists = new ArrayList<CategoryBean>();
		String sql = "select * from category where lev = ? order by cnum";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, lev);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb.setCnum(rs.getInt("cnum"));
				cb.setCode(rs.getString("code"));
				cb.setCname(rs.getString("cname"));
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
	}//getCategoryByLev
	
	public boolean searchCname(String cname){
		boolean isDupl = false;
		String sql = "select * from category where cname = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cname);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				isDupl = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isDupl;
	}//searchCname
	
	public boolean searchCode(String code){
		boolean isDupl = false;
		ResultSet rs = null;
		String sql = "select * from category where code = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				isDupl = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDupl;
	}//searchCode
	
	public CategoryBean getCategoryByCname(String cname){
		CategoryBean cb = new CategoryBean();
		String sql = "select * from category where cname = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, cname);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				cb = getCategoryBean(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cb;
	}//searchCname
	
	public CategoryBean getCategoryByCnum(int cnum){
		CategoryBean cb = new CategoryBean();
		String sql = "select * from category where cnum = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				cb = getCategoryBean(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cb;
	}//getCategoryByCnum
	
	public CategoryBean getCategoryBean(ResultSet rs) {
		CategoryBean cb = new CategoryBean();
		
		try {
			cb.setCnum(rs.getInt("cnum"));
			cb.setCname(rs.getString("cname"));
			cb.setCode(rs.getString("code"));
			cb.setLev(rs.getInt("lev"));
			cb.setRef(rs.getInt("ref"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cb;
	}//getCategoryBean
	
	public int insertCategory(String cname, String code, int lev, int ref) {
		int cnt = -1;
		
		String sql = "insert into category(cnum, code, cname, lev, ref) "
				+ "values(cate_seq.nextval, ?, ?, ?, ?)";
		String sql2 = "insert into category(cnum, code, cname, lev, ref) "
				+ "values(cate_seq.nextval, ?, ?, ?, cate_seq.currval)";
		try {
			if(ref == 0) { //대분류 카테고리 입력
				ps = conn.prepareStatement(sql2);
				ps.setString(1, code);
				ps.setString(2, cname);
				ps.setInt(3, lev); //1
				cnt = ps.executeUpdate();
			}else {
				ps = conn.prepareStatement(sql);
				ps.setString(1, code);
				ps.setString(2, cname);
				ps.setInt(3, lev);
				ps.setInt(4, ref);
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
	}//insertCategory
	
	public int updateCategory(int cnum, String cname, String code, int lev, int ref) {
		int cnt = -1;
		
		String sql = "update category set cname=?, code=?, lev=?, ref=? where cnum = ?";
		try {
			if(ref == 0) { //대분류 카테고리 입력
				ps = conn.prepareStatement(sql);
				ps.setString(1, cname);
				ps.setString(2, code);
				ps.setInt(3, lev); //1
				ps.setInt(4, cnum); 
				ps.setInt(5, cnum); 
				cnt = ps.executeUpdate();
			}else {
				ps = conn.prepareStatement(sql);
				ps.setString(1, cname);
				ps.setString(2, code);
				ps.setInt(3, lev);
				ps.setInt(4, ref);
				ps.setInt(5, cnum);
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
	}//updateCategory
	
	public int deleteCategory(int cnum){
		int cnt = -1;
		String sql = "delete from category where cnum = ?";
		try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, cnum);
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
	}//deleteCategory
	
	public ArrayList<CategoryBean> getCategoryByRef(int ref, int lev){
		ArrayList<CategoryBean> clist = new ArrayList<CategoryBean>();
		
		
		String sql = "select * from category where ref = ? and lev=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ref);
			ps.setInt(2, lev);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CategoryBean cb = new CategoryBean();
				cb = getCategoryBean(rs);
				clist.add(cb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return clist;
	}//getCategoryByRef
}
