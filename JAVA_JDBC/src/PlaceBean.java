
public class PlaceBean {
	String id;
	String name;
	int num;
	String pname;
	String code;
	String date;
	String location;
	String addr;
	String link;
	int no;
	int likes;
	
	public PlaceBean() {
		
	}
	
	public PlaceBean(int num, String pname, String code, String date, String location, 
			String addr, String link, int no, int likes) {
		super();
		this.num = num;
		this.pname = pname;
		this.code = code;
		this.date = date;
		this.location = location;
		this.addr = addr;
		this.link = link;
		this.no = no;
		this.likes = likes;
	}
	
	public PlaceBean(String name, String id, int num, String pname, String code, String date, String location, 
			String addr, String link, int no, int likes) {
		super();
		this.id = id;
		this.num = num;
		this.name = name;
		this.pname = pname;
		this.code = code;
		this.date = date;
		this.location = location;
		this.addr = addr;
		this.link = link;
		this.no = no;
		this.likes = likes;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
}
