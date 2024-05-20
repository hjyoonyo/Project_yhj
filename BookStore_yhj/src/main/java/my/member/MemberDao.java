package my.member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import my.book.BookBean;

public class MemberDao {
	private static MemberDao instance;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public static MemberDao getInstance(){
		if(instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}//getInstance
	
	private MemberDao() {
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
	
	public MemberBean getMemberById(String id, String passwd) {
		MemberBean mb = null;
		String sql = "select * from members where id = ? and passwd = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, passwd);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setNo(rs.getInt("no"));
				mb.setId(rs.getString("id"));
				mb.setPasswd(rs.getString("passwd"));
				mb.setName(rs.getString("name"));
				mb.setEmail(rs.getString("email"));
				mb.setAddress(rs.getString("address"));
				mb.setPhone(rs.getString("phone"));
				mb.setRegdate(rs.getString("regdate"));
				mb.setBirth(rs.getString("birth"));
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
		return mb;
	}
	
	
	public boolean searchId(String id) {
		boolean isDupl = false;
		
		String sql = "select * from members where upper(id) = upper(?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				isDupl = true;
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
		
		return isDupl;
	}//searchId
	
	public boolean searchEmail(String email){
		boolean isDupl = false;
		
		String sql = "select * from members where upper(email) = upper(?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				isDupl = true;
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
		
		return isDupl;
	}
	
	public int insertMember(MemberBean mb) {
		int cnt = -1;
		
		String sql = "insert into members(no, id, passwd, name, email, address, phone, birth, regdate) "
				+ "values(mem_seq.nextval, ?, ?, ?, ?, ?, ?, ?, default)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, mb.getId());
			ps.setString(2, mb.getPasswd());
			ps.setString(3, mb.getName());
			ps.setString(4, mb.getEmail());
			ps.setString(5, mb.getAddress());
			ps.setString(6, mb.getPhone());
			ps.setString(7, mb.getBirth());
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
	}//insertMember
	
	public MemberBean findId(String name, String email){
		MemberBean mb = null;
		String sql = "select * from members where name=? and email=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				mb = getMemberBean(rs);
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
		
		return mb;
	}//findId
	
	public MemberBean getMemberBean(ResultSet rs) {
		MemberBean mb = new MemberBean();
		
		try {
			mb.setNo(rs.getInt("no"));
			mb.setId(rs.getString("id"));
			mb.setPasswd(rs.getString("passwd"));
			mb.setName(rs.getString("name"));
			mb.setEmail(rs.getString("email"));
			
			mb.setAddress(rs.getString("address"));
			mb.setPhone(rs.getString("phone"));
			mb.setBirth(String.valueOf(rs.getDate("birth")));
			mb.setRegdate(String.valueOf(rs.getDate("regdate")));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mb;
	}//getMemberBean
	
	public MemberBean findPw(String id, String name, String email){
		System.out.println(id+"/"+name+"/"+email);
		MemberBean mb = null;
		String sql = "select * from members where id=? and name=? and email=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				mb = getMemberBean(rs);
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
		
		return mb;
	}//findPw
	
	public ArrayList<MemberBean> getAllMembers(){
		ArrayList<MemberBean> lists = new ArrayList<MemberBean>();
		String sql = "select * from members order by no";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberBean mb = getMemberBean(rs);
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
	}//getAllMembers
	
	public int deleteCheckByNo(int[] rowcheck){
		int cnt = -1;
		String sql = "delete from members where no=?";
		for(int i=0; i<rowcheck.length-1;i++) {
			sql += " or no=?";
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
	}//deleteCheckByNo
	
	public int deleteMemberByNo(int no){
		int cnt = -1;
		String sql = "delete from members where no=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
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
	}//deleteMemberByNo
	
	public MemberBean getMemberByNo(int no) {
		MemberBean mb = new MemberBean();
		String sql = "select * from members where no =?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				mb = getMemberBean(rs);
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
		return mb;
	}//getMemberByNo
	
	public int updateMember(MemberBean mb) {
		int cnt = -1;
		String sql = "update members set name=?, phone=?, email=?, birth=?, address=? where no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, mb.getName());
			ps.setString(2, mb.getPhone());
			ps.setString(3, mb.getEmail());
			ps.setString(4, mb.getBirth());
			ps.setString(5, mb.getAddress());
			ps.setInt(6, mb.getNo());
			
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
		System.out.println("수정 성공 갯수 : "+cnt);
		return cnt;
	}//updateMember
	
	public ArrayList<MemberBean> getMembersByKeyword(String keyword){
		ArrayList<MemberBean> lists = new ArrayList<MemberBean>();
		String sql = "select * from members where id like '%"+keyword+"%' or name like '%"+keyword+"%' or email like '%"+keyword+"%' or address like '%"+keyword+"%' or phone like '%"+keyword+"%' order by no";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberBean mb = getMemberBean(rs);
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
		System.out.println("MemberDao lists 개수 : "+lists.size());
		return lists;
	}//getMembersByKeyword
	
}
