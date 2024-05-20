import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembersDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "sqlid";
	String pw = "sqlpw";

	MembersBean mb = null;
	ArrayList<MembersBean> lists = null;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	MembersDao(){//생성자
		try {
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		}
	}

	public void connect() {//connect()
		try {
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속 성공");
		} catch (SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {//main()
		new MembersDao();
	}

	public ArrayList<MembersBean> getAllMembers() {//getAllMembers()
		connect();
		lists = new ArrayList<MembersBean>();

		try {
			String sql = "select * from members order by no";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				int no = rs.getInt("no");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String birth = String.valueOf(rs.getDate("birth"));
				String pnum = rs.getString("pnum");
				String nickname = rs.getString("nickname");
				int rating = rs.getInt("rating");
				String joinDate = String.valueOf(rs.getDate("joindate"));
				int pcount = rs.getInt("pcount");
				String profile = rs.getString("profile");

				mb = new MembersBean(no, id, pw, name, birth, pnum, nickname, rating, joinDate, pcount, profile);

				lists.add(mb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}//getAllMembers()

	public ArrayList<MembersBean> getAllByName(String word) {
		connect();
		lists = new ArrayList<MembersBean>();

		try {
			String sql = "select * from members where name like '%"+word+"%' order by no";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				int no = rs.getInt("no");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String birth = String.valueOf(rs.getDate("birth"));
				String pnum = rs.getString("pnum");
				String nickname = rs.getString("nickname");
				int rating = rs.getInt("rating");
				String joinDate = String.valueOf(rs.getDate("joindate"));
				int pcount = rs.getInt("pcount");
				String profile = rs.getString("profile");

				mb = new MembersBean(no, id, pw, name, birth, pnum, nickname, rating, joinDate, pcount, profile);

				lists.add(mb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;

	}//getAllByName()

	public MembersBean getMemberById(String id) {
		connect();

		try {
			String sql = "select * from members where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while(rs.next()) {
				int no = rs.getInt("no");
				String id2 = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String birth = String.valueOf(rs.getDate("birth"));
				String pnum = rs.getString("pnum");
				String nickname = rs.getString("nickname");
				int rating = rs.getInt("rating");
				String joinDate = String.valueOf(rs.getDate("joindate"));
				int pcount = rs.getInt("pcount");
				String profile = rs.getString("profile");

				mb = new MembersBean(no, id2, pw, name, birth, pnum, nickname, rating, joinDate, pcount, profile);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mb;
	}//getMemberById()

//	public ArrayList<MembersBean> getMembersOrderBy(String column, String sort) {
//		connect();
//		lists = new ArrayList<MembersBean>();
//
//		try {
//			String sql = "select * from members order by "+column+" "+sort+"";
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//
//			while(rs.next()) {
//				int no = rs.getInt("no");
//				String id = rs.getString("id");
//				String pw = rs.getString("pw");
//				String name = rs.getString("name");
//				String birth = String.valueOf(rs.getDate("birth"));
//				String pnum = rs.getString("pnum");
//				String nickname = rs.getString("nickname");
//				int rating = rs.getInt("rating");
//				String joinDate = String.valueOf(rs.getDate("joindate"));
//				int pcount = rs.getInt("pcount");
//				String profile = rs.getString("profile");
//
//				mb = new MembersBean(no, id, pw, name, birth, pnum, nickname, rating, joinDate, pcount, profile);
//
//				lists.add(mb);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return lists;
//	}//getMembersOrderBy()

	public int insertMembers(MembersBean mb) {
		connect();
		int cnt = -1;
		try {
			String sql = "insert into members values(memseq.nextval,?,?,?,?,?,?,0,default,default,null)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mb.getId());
			ps.setString(2, mb.getPw());
			ps.setString(3, mb.getName());
			ps.setString(4, mb.getBirth());
			ps.setString(5, mb.getPnum());
			ps.setString(6, mb.getNickname());
			cnt = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}//insertMembers()

	public int updateMembers(MembersBean mb) throws SQLException {
		connect();
		int cnt = -1;
		String sql = "update members set id = ?,pw=?,name=?,birth=?,pnum=?,nickname=?,rating=?,joindate=?,pcount=? where no = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, mb.getId());
		ps.setString(2, mb.getPw());
		ps.setString(3, mb.getName());
		ps.setString(4, mb.getBirth());
		ps.setString(5, mb.getPnum());
		ps.setString(6, mb.getNickname());
		ps.setInt(7, mb.getRating());
		ps.setString(8, mb.getJoinDate());
		ps.setInt(9, mb.getPcount());
		ps.setInt(10, mb.getNo());
		cnt = ps.executeUpdate();

		return cnt;
	}//updateMembers()
	
	public int updateProfile(String path, int no) {
		connect();
		int cnt = -1;

		try {
			String sql = "update members set profile = ? where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, path);
			ps.setInt(2, no);
			cnt = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
		
	}//updateProfile()

	public int deleteMembers(int no) {
		connect();
		int cnt = -1;
		try {
			String sql = "delete members where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			cnt = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}//deleteMembers


}
