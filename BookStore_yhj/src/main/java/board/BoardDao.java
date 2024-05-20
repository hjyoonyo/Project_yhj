package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDao {
	private static BoardDao instance;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	private BoardDao() {
		try {
			Context initContext;
			initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/OracleDB");
			conn = ds.getConnection();
			System.out.println("접속 성공");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}
	}//생성자
	
	public ArrayList<BoardBean> getAllArticle(int startRow, int endRow){
		ArrayList<BoardBean> lists = new ArrayList<BoardBean>();
		String sql = "select num, writer, email, subject, passwd, reg_date, readcount, ref, re_step, re_level, content, ip " ;		        
		sql += "from (select rownum as rank, num, writer, email, subject, passwd, reg_date, readcount, ref, re_step, re_level, content, ip ";
		sql += "from (select num, writer, email, subject, passwd, reg_date, readcount, ref, re_step, re_level, content, ip ";
		sql += "from board  ";
		sql += "order by ref desc, re_step asc )) ";
		sql += "where rank between ? and ? ";
		//rank가 1~10, 11~20인 레코드를 가져옴
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, endRow);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardBean bb = new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setWriter(rs.getString("writer"));
				bb.setEmail(rs.getString("email"));
				bb.setSubject(rs.getString("subject"));
				bb.setPasswd(rs.getString("passwd"));
				
				bb.setReg_date(rs.getTimestamp("reg_date"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setRef(rs.getInt("ref"));
				bb.setRe_step(rs.getInt("re_step"));
				bb.setRe_level(rs.getInt("re_level"));

				bb.setContent(rs.getString("content"));
				bb.setIp(rs.getString("ip"));
				
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
	}//getAllBord
	
	public int getArticleCount() {
		//전체 레코드 
		int cnt = -1;
		String sql = "select count(*) cnt from board"; //별칭 cnt
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
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
		
		return cnt;
	}//getArticleCount
	
	public BoardBean getArticle(int num) {
		BoardBean bb = new BoardBean();
		String sql2 = "update board set readcount = readcount+1 where num = ?";
		String sql = "select * from board where num = ?";
		try {
			
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, num);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bb.setNum(rs.getInt("num"));
				bb.setWriter(rs.getString("writer"));
				bb.setEmail(rs.getString("email"));
				bb.setSubject(rs.getString("subject"));
				bb.setPasswd(rs.getString("passwd"));
				
				bb.setReg_date(rs.getTimestamp("reg_date"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setRef(rs.getInt("ref"));
				bb.setRe_step(rs.getInt("re_step"));
				bb.setRe_level(rs.getInt("re_level"));

				bb.setContent(rs.getString("content"));
				bb.setIp(rs.getString("ip"));
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
		
		return bb;
	}//getArticle
	
	public int insertArticle(BoardBean bb) { //5+2 원글쓰기. 원글은 ref와 num이 같고, re_step과 re_level은 0이다
		int cnt = -1;
		
		String sql = "insert into board(num,writer,email,subject,passwd,reg_date,ref,re_step,re_level,content,ip) "
				+ "values(board_seq.nextval,?,?,?,?,?,board_seq.currval,0,0,?,?)"; 
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bb.getWriter());
			ps.setString(2, bb.getEmail());
			ps.setString(3, bb.getSubject());
			ps.setString(4, bb.getPasswd());
			ps.setTimestamp(5, bb.getReg_date());
			ps.setString(6, bb.getContent());
			ps.setString(7, bb.getIp());
			
			cnt = ps.executeUpdate();
			
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
		
		return cnt;
	}//insertArticle
	
	public int deleteArticle(int num, String passwd){
		int cnt = -1; // 실행에 문제 발생
		String sql = "select passwd from board where num=?"; 
		String sql2 = "delete from board where num = ?"; 
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				String dbpw = rs.getString("passwd");
				if(passwd.equals(dbpw)) {
					ps = conn.prepareStatement(sql2);
					ps.setInt(1, num);
					cnt = ps.executeUpdate();
				}else {
					cnt = 0; // 비번 일치x
				}
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
		return cnt;
	}//deleteArticle
	
	public int replyArticle(BoardBean bb) {
		System.out.println("BoardDao.jsp re_step="+bb.getRe_step());
		int cnt = -1;
		String sql = "update board set re_step=re_step+1 where ref= ? and re_step > 0 and re_step > ?";
		String sql2 = "insert into board(num,writer,email,subject,passwd,reg_date, ref,re_step,re_level,content,ip) "
				+ "values(board_seq.nextval,?,?,?,?,?, ?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bb.getRef());
			ps.setInt(2, bb.getRe_step());
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sql2);
			ps.setString(1, bb.getWriter());
			ps.setString(2, bb.getEmail());
			ps.setString(3, bb.getSubject());
			ps.setString(4, bb.getPasswd());
			ps.setTimestamp(5, bb.getReg_date());
			
			ps.setInt(6, bb.getRef());
			ps.setInt(7, bb.getRe_step()+1);
			ps.setInt(8, bb.getRe_level()+1);
			ps.setString(9, bb.getContent());
			ps.setString(10, bb.getIp());
			
			cnt = ps.executeUpdate();
			
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
		return cnt;
	}//replyArticle
	
	public BoardBean getArticleByNum(int num) {
		BoardBean bb = new BoardBean();
		String sql = "select * from board where num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bb.setNum(rs.getInt("num"));
				bb.setWriter(rs.getString("writer"));
				bb.setEmail(rs.getString("email"));
				bb.setSubject(rs.getString("subject"));
				bb.setPasswd(rs.getString("passwd"));
				
				bb.setReg_date(rs.getTimestamp("reg_date"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setRef(rs.getInt("ref"));
				bb.setRe_step(rs.getInt("re_step"));
				bb.setRe_level(rs.getInt("re_level"));

				bb.setContent(rs.getString("content"));
				bb.setIp(rs.getString("ip"));
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
		
		return bb;
	}//getArticleByNum
	
	public int updateArticle(BoardBean bb) {
		int cnt = -1;
		String sql2 = "select passwd from board where num=?";
		String sql = "update board set writer=?, subject=?, email=?, content=?, passwd=? where num=?";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, bb.getNum());
			rs = ps.executeQuery();
			if(rs.next()) {
				String dbpw = rs.getString("passwd");
				if(dbpw.equals(bb.getPasswd())) {
					ps = conn.prepareStatement(sql);
					ps.setString(1, bb.getWriter());
					ps.setString(2, bb.getSubject());
					ps.setString(3, bb.getEmail());
					ps.setString(4, bb.getContent());
					ps.setString(5, bb.getPasswd());
					ps.setInt(6, bb.getNum());
					
					cnt = ps.executeUpdate();
				}else {
					cnt = 0;
				}
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
	}//updateArticle
	
}
