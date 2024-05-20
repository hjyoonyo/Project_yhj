
public class MembersBean {
	int no;
	String id;
	String pw;
	String name;
	String birth;
	String pnum;
	String nickname;
	int rating;
	String joinDate;
	int pcount;
	String profile;
	
	MembersBean(){
	}
	
	public MembersBean(int no, String id, String pw, String name, String birth, String pnum, String nickname,
			int rating, String joinDate, int pcount, String profile) {
		this.no = no;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.pnum = pnum;
		this.nickname = nickname;
		this.rating = rating;
		this.joinDate = joinDate;
		this.pcount = pcount;
		this.profile = profile;
	}
	
	public MembersBean(String id, String pw, String name, String birth, String pnum, String nickname) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.pnum = pnum;
		this.nickname = nickname;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public int getPcount() {
		return pcount;
	}
	public void setPcount(int pcount) {
		this.pcount = pcount;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
}
