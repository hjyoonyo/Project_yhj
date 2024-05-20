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

import com.oreilly.servlet.MultipartRequest;

import my.member.MemberBean;

public class BookDao {
	private static BookDao instance;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public static BookDao getInstance(){
		if(instance == null) {
			instance = new BookDao();
		}
		return instance;
	}//getInstance
	
	private BookDao() {
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
	
	public ArrayList<BookBean> getAllBook() {
		ArrayList<BookBean> lists = new ArrayList<BookBean>();
		String sql = "select * from book order by bnum";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BookBean bb = getBookBean(rs);
				lists.add(bb);
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
	}//getAllBook
	
	public BookBean getBookBean(ResultSet rs) throws SQLException {
		BookBean bb = new BookBean();
		
		bb.setBnum(rs.getInt("bnum"));
		bb.setCnum(rs.getInt("cnum"));
		bb.setTitle(rs.getString("title"));
		bb.setAuthor(rs.getString("author"));
		bb.setPublisher(rs.getString("publisher"));
		bb.setBimage(rs.getString("bimage"));
		
		bb.setBqty(rs.getInt("bqty"));
		bb.setPrice(rs.getInt("price"));
		bb.setPoint(rs.getInt("point"));
		bb.setSales(rs.getInt("sales"));
		bb.setContent(rs.getString("content"));
		bb.setPubdate(String.valueOf(rs.getDate("pubdate")));
		bb.setInputdate(String.valueOf(rs.getDate("inputdate")));
		
		return bb;
	}//getCategoryBean
	
	public int insertBook(MultipartRequest mr, int cnum){
		int cnt = -1;
		
		String sql = "insert into book(bnum,cnum,title,author,publisher,bimage, bqty, price, point, content, pubdate, inputdate) "
				+ "values(book_seq.nextval,?,?,?,?,?, ?,?,?,?,?,default)";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, cnum);
				
				ps.setString(2,  mr.getParameter("title"));
				
				ps.setString(3, mr.getParameter("author"));

				ps.setString(4, mr.getParameter("publisher"));
				
				String file = (String)mr.getFileNames().nextElement(); //중복되는 파일명을 넣어 줘야 함.
				System.out.println("file : "+file);
				ps.setString(5, mr.getFilesystemName(file));
				
				ps.setString(6, mr.getParameter("bqty"));
				ps.setString(7, mr.getParameter("price"));
				ps.setString(8, mr.getParameter("point"));
				ps.setString(9,mr.getParameter("content"));
				ps.setString(10, mr.getParameter("pubdate"));
				
				cnt = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(ps != null) {
						ps.close();
					}
				} catch (SQLException e) {
					System.out.println("insertBook 접속 끊기 실패");
				}
			}
		return cnt;
	}//insertBook
	
	public ArrayList<BookBean> getBookByKeyword(String keyword){
		ArrayList<BookBean> lists = new ArrayList<BookBean>();
		String sql = "select * from book where title like '%"+keyword+"%' or author like '%"+keyword+"%' or publisher like '%"+keyword+"%' order by bnum";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BookBean mb = getBookBean(rs);
				lists.add(mb);
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
		System.out.println("BookDao lists 개수 : "+lists.size());
		return lists;
	}//getBookByKeyword
	
	public int deleteCheckByBnum(int[] rowcheck){
		int cnt = -1;
		String sql = "delete from book where bnum=?";
		for(int i=0; i<rowcheck.length-1;i++) {
			sql += " or bnum=?";
		}
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0; i<rowcheck.length;i++) {
				ps.setInt(i+1, rowcheck[i]);
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
		System.out.println("삭제 성공 갯수 : "+cnt);
		return cnt;
	}//deleteCheckByBnum
	
	public int deleteBook(int bnum){
		int cnt = -1;
		String sql = "delete from book where bnum=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bnum);
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
		System.out.println("삭제 성공 갯수 : "+cnt);
		return cnt;
	}//deleteBook
	
	public BookBean getBookByBnum(int bnum) {
		BookBean bb = new BookBean();
		String sql = "select * from book where bnum =?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bnum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bb = getBookBean(rs);
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
		return bb;
	}//getBookByBnum
	
	public ArrayList<BookBean> getBookByCnumLev1(int cnum_lev1){ //대분류에서 하위 카테고리의 책까지 가져오기
		ArrayList<BookBean> lists = new ArrayList<BookBean>();
		ArrayList<Integer> cnum = new ArrayList<Integer>();
		cnum.add(cnum_lev1); // 대분류 카테고리의 cnum을 담는다
		int size = 1;
		String sql = "select * from book where cnum = ?";
		
		CategoryDao cdao = CategoryDao.getInstance();
		
		//lev=2인 중분류 카테고리
		ArrayList<CategoryBean> clist_lev2 = cdao.getCategoryByRef(cnum_lev1, 2); //대분류의 하위 카테고리 정보
		ArrayList<CategoryBean> clist_lev3 = new ArrayList<CategoryBean>();
		if(clist_lev2.size() >0) { //중분류가 존재
			for(CategoryBean cb : clist_lev2) { //중분류 하나씩 가져오기
				size++;
				System.out.println("중분류 getCname : "+cb.getCname());
				cnum.add(cb.getCnum()); //중분류 카테고리의 cnum을 하나씩 담는다
				sql += " or cnum = ?";
				System.out.println("sql1"+sql);
				//lev=3인 소분류 카테고리
				clist_lev3 = cdao.getCategoryByRef(cb.getCnum(), 3); //중분류의 하위 카테고리 정보
				
				if(clist_lev3.size() >0) { //소분류가 존재
					for(CategoryBean cb2 : clist_lev3) { //소분류 하나씩 가져오기
						size++;
						System.out.println("소분류 getCname : "+cb2.getCname());
						cnum.add(cb2.getCnum()); //소분류 카테고리의 cnum을 하나씩 담는다
						sql += " or cnum = ?";
						System.out.println("sql2"+sql);
					}//for
				}//if
				
			}//for
		}//if
		System.out.println("size : "+size);
		sql += " order by bnum";
		System.out.println("sql3"+sql);
		try {
			ps = conn.prepareStatement(sql);
			
			for(int i=0; i < size; i++) {
				ps.setInt(i+1, cnum.get(i));
				System.out.println(i+1+"/"+cnum.get(i));
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BookBean mb = getBookBean(rs);
				lists.add(mb);
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
	}//getBookByCnum
	
	public int updateBook(MultipartRequest mr, String img){
		int cnt = -1;
		String sql = "update book set cnum=?, title=?, author=?, publisher=?, bimage=?, bqty=?, price=?, point=?, sales=?, content=?, pubdate=? where bnum=?";
		CategoryDao cdao = CategoryDao.getInstance();
		CategoryBean cb = cdao.getCategoryByCname(mr.getParameter("cname"));
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cb.getCnum());
			ps.setString(2, mr.getParameter("title"));
			ps.setString(3, mr.getParameter("author"));
			ps.setString(4, mr.getParameter("publisher"));
			ps.setString(5, img);
			ps.setString(6, mr.getParameter("bqty"));
			ps.setString(7, mr.getParameter("price"));
			ps.setString(8, mr.getParameter("point"));
			ps.setString(9, mr.getParameter("sales"));
			ps.setString(10, mr.getParameter("content"));
			ps.setString(11, mr.getParameter("pubdate"));
			ps.setString(12, mr.getParameter("bnum"));
			
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
	}//updateBook
	
}
