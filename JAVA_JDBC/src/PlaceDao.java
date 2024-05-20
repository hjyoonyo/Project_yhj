import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "sqlid";
	String pw = "sqlpw";

	PlaceBean pb = null;
	ArrayList<PlaceBean> lists = null;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	PlaceDao(){//생성자
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
		new PlaceDao();
	}

	public ArrayList<PlaceBean> getAllPlace() {//getAllPlace()
		connect();
		lists = new ArrayList<PlaceBean>();

		try {
			String sql = "select * from place order by num";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				int num = rs.getInt("num");
				String pname = rs.getString("pname");
				String code = rs.getString("code");
				String date = String.valueOf(rs.getDate("pdate"));
				String location = rs.getString("location");
				String addr = rs.getString("addr");
				String link = rs.getString("link");
				int no = rs.getInt("no");
				int likes = rs.getInt("likes");

				pb = new PlaceBean(num,pname,code,date,location,addr,link,no,likes);
				
				lists.add(pb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}//getAllMembers()

	public ArrayList<PlaceBean> getAllByName(String word) {
		connect();
		lists = new ArrayList<PlaceBean>();

		try {
			String sql = "select no, id, name, num, pname, code, pdate, location, addr, link, likes from members natural join place where pname like '%"+word+"%' order by num";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				int no = rs.getInt("no");
				String id = rs.getString("id");
				String name = rs.getString("name");	
				int num = rs.getInt("num");
				String pname = rs.getString("pname");
				String code = rs.getString("code");
				String date = String.valueOf(rs.getDate("pdate"));
				String location = rs.getString("location");
				String addr = rs.getString("addr");
				String link = rs.getString("link");
				int likes = rs.getInt("likes");
				
				pb = new PlaceBean(name, id, num,pname,code,date,location,addr,link,no,likes);
				
				lists.add(pb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;

	}//getAllByName()

	public ArrayList<PlaceBean> getPlaceByNo(int no2) {
		connect();
		lists = new ArrayList<PlaceBean>();
		
		try {
			String sql = "select * from place where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no2);
			rs = ps.executeQuery();

			while(rs.next()) {
				int num = rs.getInt("num");
				String pname = rs.getString("pname");
				String code = rs.getString("code");
				String date = String.valueOf(rs.getDate("pdate"));
				String location = rs.getString("location");
				String addr = rs.getString("addr");
				String link = rs.getString("link");
				int no = rs.getInt("no");
				int likes = rs.getInt("likes");
				
				pb = new PlaceBean(num,pname,code,date,location,addr,link,no,likes);
				
				lists.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}//getPlaceByNo()

	public int insertPlace(PlaceBean mb) {
		connect();
		int cnt = -1;
		try {
			String sql = "insert into place values(plseq.nextval,?,?,default,?,?,?,?,default)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mb.getPname());
			ps.setString(2, mb.getCode());
			ps.setString(3, mb.getLocation());
			ps.setString(4, mb.getAddr());
			ps.setString(5, mb.getLink());
			ps.setInt(6, mb.getNo());
			cnt = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}//insertMembers()

	public int updatePlace(PlaceBean mb) throws SQLException {
		connect();
		int cnt = -1;
		String sql = "update place set pname = ?,code=?,pdate=?,location=?,addr=?,link=?,no=?,likes=? where num = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, mb.getPname());
		ps.setString(2, mb.getCode());
		ps.setString(3, mb.getDate());
		ps.setString(4, mb.getLocation());
		ps.setString(5, mb.getAddr());
		ps.setString(6, mb.getLink());
		ps.setInt(7, mb.getNo());
		ps.setInt(8, mb.getLikes());
		ps.setInt(9, mb.getNum());
		cnt = ps.executeUpdate();

		return cnt;
	}//updatePlace()
	
	public int deletePlace(int num) {
		connect();
		int cnt = -1;
		
		try {
			String sql = "delete place where num = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			cnt = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}//deletePlace
	
	public ArrayList<PlaceBean> getJoinTable(){
		connect();
		lists = new ArrayList<PlaceBean>();

		try {
			String sql = "select no, id, name, num, pname, code, pdate, location, addr, link, likes from members natural join place order by num";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				int no = rs.getInt("no");
				String id = rs.getString("id");
				String name = rs.getString("name");				
				int num = rs.getInt("num");
				String pname = rs.getString("pname");
				String code = rs.getString("code");
				String date = String.valueOf(rs.getDate("pdate"));
				String location = rs.getString("location");
				String addr = rs.getString("addr");
				String link = rs.getString("link");
				int likes = rs.getInt("likes");
				
				pb = new PlaceBean(name, id, num, pname, code, date, location, addr, link, no, likes);
				
				lists.add(pb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}//getJoinTable
}
