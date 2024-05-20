import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;

public class FManager1 extends JFrame implements ActionListener{
	private int i;
	private MembersDao dao = new MembersDao();
	private PlaceDao pdao = new PlaceDao();
	private ArrayList<MembersBean> lists = null;
	private ArrayList<PlaceBean> plists = null;
	private Object[] columnNames = {"NO","ID","비밀번호","이름","생년월일","전화번호","별명","레벨","가입일자","등록수"};
	private Object[] pcolumnNames = {"NUM","가게명","분류","등록일","지역명","주소","링크","등록NO","좋아요수"};
	private Object[][] rowData;
	private JTable tableM =null;
	private JTable tableP =null;

	private JScrollPane scrollPane = null;
	private JScrollPane pscrollPane = new JScrollPane();
	private Container contentPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JButton members = null;
	private JButton place = null;
	private JButton searchB = null;
	private JButton logout = null;
	private JLabel searchL = null;
	private JLabel profileLabel = new JLabel();
	private JTextField searchT = new JTextField(15);
	private JTextField[] textFields = new JTextField[columnNames.length];
	private JButton[] btn = new JButton[4];

	FManager1(){
		super("토마토 플레이스 - 관리자님 환영합니다.");
		setSize(1320,880);
		setLocationRelativeTo(null); // 이러면 화면 중앙에 배치됨
		setResizable(false);

		//회원테이블
		lists = dao.getAllMembers();
		rowData = new Object[lists.size()][columnNames.length];
		fillData();

		tableM = new JTable(rowData, columnNames);
		scrollPane = new JScrollPane(tableM);
		
		//맛집테이블
		plists = pdao.getAllPlace();
		rowData = new Object[plists.size()][pcolumnNames.length]; 
		fillPData(); 

		tableP = new JTable(rowData, pcolumnNames);
		pscrollPane = new JScrollPane(tableP);
		setPTable();

		compose(); //compose()를 31행에 배치해서 오류 발생 => Cannot read field "parent" because "comp" is null (추가하려는 컴포넌트가 null인 상태라 오류 발생)
		setTable();

		setVisible(true); // 이걸 compose() 앞에 배치하니까 버튼이 늦게 뜸.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//생성자

	private void setTable() {

		tableM.setAutoCreateRowSorter(true);
		TableRowSorter sorter = new TableRowSorter(tableM.getModel());
		tableM.setRowSorter(sorter); //table 오름차순 내림차순 정렬하는 메서드

		tableM.addMouseListener(new MouseHandler());

		tableM.getColumn("NO").setPreferredWidth(10);
		tableM.getColumn("ID").setPreferredWidth(50);
		tableM.getColumn("비밀번호").setPreferredWidth(50);
		tableM.getColumn("이름").setPreferredWidth(25);
		tableM.getColumn("생년월일").setPreferredWidth(50);
		tableM.getColumn("전화번호").setPreferredWidth(75);
		tableM.getColumn("별명").setPreferredWidth(40);
		tableM.getColumn("레벨").setPreferredWidth(10);
		tableM.getColumn("가입일자").setPreferredWidth(50);
		tableM.getColumn("등록수").setPreferredWidth(20);

	}//setTable

	private void setPTable() {

		tableP.setAutoCreateRowSorter(true);
		TableRowSorter sorter = new TableRowSorter(tableP.getModel());
		tableP.setRowSorter(sorter); //table 오름차순 내림차순 정렬하는 메서드

		//"NUM","가게명","분류","등록일","지역명","주소","링크","등록NO","좋아요수"
		tableP.getColumn("NUM").setPreferredWidth(10);
		tableP.getColumn("가게명").setPreferredWidth(80);
		tableP.getColumn("분류").setPreferredWidth(30);
		tableP.getColumn("등록일").setPreferredWidth(50);
		tableP.getColumn("지역명").setPreferredWidth(20);
		tableP.getColumn("주소").setPreferredWidth(75);
		tableP.getColumn("링크").setPreferredWidth(50);
		tableP.getColumn("등록NO").setPreferredWidth(10);
		tableP.getColumn("좋아요수").setPreferredWidth(10);

	}//makeTable


	private void fillData() {
		//members 테이블 한 줄씩 가져와서 값을 2차원 배열 rowData에 넣는다.
		int j=0;
		for(i=0;i<lists.size();i++) {
			rowData[i][j++] = lists.get(i).getNo();
			rowData[i][j++] = lists.get(i).getId();
			rowData[i][j++] = lists.get(i).getPw();
			rowData[i][j++] = lists.get(i).getName();
			rowData[i][j++] = lists.get(i).getBirth();
			rowData[i][j++] = lists.get(i).getPnum();
			rowData[i][j++] = lists.get(i).getNickname();
			rowData[i][j++] = lists.get(i).getRating();
			rowData[i][j++] = lists.get(i).getJoinDate();
			rowData[i][j++] = lists.get(i).getPcount();
			j = 0;
		}
	}//fillData

	private void fillPData() {
		//members 테이블 한 줄씩 가져와서 값을 2차원 배열 rowData에 넣는다.
		int j=0;
		for(i=0;i<plists.size();i++) {
			rowData[i][j++] = plists.get(i).getNum();
			rowData[i][j++] = plists.get(i).getPname();
			rowData[i][j++] = plists.get(i).getCode();
			rowData[i][j++] = plists.get(i).getDate();
			rowData[i][j++] = plists.get(i).getLocation();
			rowData[i][j++] = plists.get(i).getAddr();
			rowData[i][j++] = plists.get(i).getLink();
			rowData[i][j++] = plists.get(i).getNo();
			rowData[i][j++] = plists.get(i).getLikes();
			j = 0;
		}
	}//fillPData

	private void compose() {
		contentPane = getContentPane();
		contentPane.setLayout(null);

		//패널1
		panel1 = new JPanel();
		panel1.setSize(600,800);
		panel1.setLocation(20, 20);
		panel1.setBorder(new LineBorder(Color.black,1)); //패널 테두리 설정
		panel1.setBorder(new LineBorder(Color.black,1)); //패널 테두리 설정
		panel1.setLayout(null);
		contentPane.add(panel1);

		scrollPane.setBounds(0, 30, 600, 770);
		scrollPane.setBorder(new LineBorder(Color.black,1));
		panel1.add(scrollPane);

		//검색 라벨
		searchL = new JLabel("회원 검색"); //줄바꿈이 적용되지 않아서 html 문법 활용.
		searchL.setBounds(0,0,100,30);
		searchL.setFont(new Font("맑은 고딕",Font.PLAIN,14));
		searchL.setOpaque(true);        // JLabel 색상변경하기 위해서는 setOpaque를 true로 해줘야한다
		searchL.setHorizontalAlignment(JLabel.CENTER);
		searchL.setBackground(Color.gray);
		searchL.setForeground(Color.white);
		panel1.add(searchL);

		//검색 텍스트 필드
		searchT.setBounds(100, 0, 150, 30);
		searchT.setBorder(null);
		panel1.add(searchT);

		//검색 버튼
		ImageIcon searchIcon = new ImageIcon("images/search.png");
		searchB = new JButton(searchIcon);
		searchB.setBounds(250,0,30,30);
		searchB.setBackground(Color.white);
		searchB.addActionListener(this);
		panel1.add(searchB);

		//패널2
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setSize(600,800);
		panel2.setLocation(640, 20);
		panel2.setBorder(new LineBorder(Color.black,1));
		contentPane.add(panel2);

		//수정, 삭제, 이미지업로드, 이미지삭제 버튼
		String[] btnNames = {"수정","삭제","이미지 업로드","이미지 삭제"};

		for(i=0;i<btn.length;i++) {
			btn[i] = new JButton(btnNames[i]);
			btn[i].setMargin(new Insets(0,0,0,0));
			btn[i].setFont(new Font("맑은 고딕",Font.PLAIN,14));
			btn[i].setContentAreaFilled(false);
			btn[i].setFocusPainted(false);
			btn[i].addActionListener(this);
			panel2.add(btn[i]);
		}

		btn[0].setBounds(20,20,50,30);
		btn[1].setBounds(70,20,50,30);
		btn[2].setBounds(120,20,120,30);
		btn[3].setBounds(240,20,100,30);


		//패널3
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(20, 50, 560, 350);
		panel3.setBackground(Color.white);
		panel3.setBorder(new LineBorder(Color.black,1));
		panel2.add(panel3);

		//회원 정보 라벨
		JLabel[] label = new JLabel[columnNames.length];

		for(i=0;i<columnNames.length;i++) {
			label[i] = new JLabel(columnNames[i].toString());
			setLabel(label[i], 25+30*i);
		}

		//텍스트필드
		for(i=0;i<columnNames.length;i++) {
			textFields[i] = new JTextField(15);
			textFields[i].setBounds(100, 28+30*i, 150, 20);
			panel3.add(textFields[i]);
		}
		textFields[0].setEnabled(false);

		//이미지
		String path = "images/tomato.png";
		setProfile(path);

		//패널4
		//		panel4 = new JPanel();
		//		panel4.setLayout(null);
		//		panel4.setBounds(20, 420, 560, 350);
		//		panel4.setBackground(Color.white);
		//		panel4.setBorder(new LineBorder(Color.black,1));
		//		panel2.add(panel4);

		//회원이 등록한 맛집테이블
		//		scrollPane.setBounds(0, 30, 600, 370);
		//		scrollPane.setBorder(new LineBorder(Color.black,1));
		//		panel1.add(scrollPane);
		//panel3.setBounds(20, 50, 560, 350);
		pscrollPane.setBounds(20, 420, 560, 350);
		pscrollPane.setBorder(new LineBorder(Color.black,1));
		panel2.add(pscrollPane);
		pscrollPane.setVisible(false);

		//회원 버튼
		members = new JButton("<HTML>회<br>원<HTML>"); //줄바꿈이 적용되지 않아서 html 문법 활용.
		members.setMargin(new Insets(0,0,0,0));
		members.setBounds(1240,20,40,100);
		members.setFont(new Font("맑은 고딕",Font.PLAIN,18));
		members.setBackground(Color.DARK_GRAY);
		members.setForeground(Color.white);
		members.setFocusPainted(false);
		members.addActionListener(this);
		contentPane.add(members);

		//맛집 버튼
		place = new JButton("<HTML>맛<br>집<HTML>");
		place.setMargin(new Insets(0,0,0,0));
		place.setBounds(1240,130,40,100);
		place.setFont(new Font("맑은 고딕",Font.PLAIN,18));
		place.setBackground(Color.DARK_GRAY);
		place.setForeground(Color.white);
		place.setFocusPainted(false);
		place.addActionListener(this);
		contentPane.add(place);
		
		//로그아웃 버튼
		logout = new JButton("로그아웃");
		logout.setMargin(new Insets(0,0,0,0));
		logout.setBounds(1240,800,50,20);
		logout.setFont(new Font("맑은 고딕",Font.PLAIN,10));
		logout.setForeground(Color.black);
		logout.setFocusPainted(false);
		logout.addActionListener(this);
		logout.setContentAreaFilled(false);
		contentPane.add(logout);
	}//compose

	private void setProfile(String path) {

		ImageIcon profileImage = new ImageIcon(path); //이미지 불러오기
		Image profileImg = profileImage.getImage();
		Image updateProfile = profileImg.getScaledInstance(240, 310, Image.SCALE_SMOOTH); //불러온 이미지 크기 조절
		ImageIcon profile = new ImageIcon(updateProfile); //사이즈 조절된 이미지로 아이콘 생성

		profileLabel.removeAll(); //100퍼 이해는 안가지만 일단 기능은 구현 완

		profileLabel.setIcon(profile);
		profileLabel.setBounds(300, 20, 240, 310);
		profileLabel.setHorizontalAlignment(JLabel.CENTER);
		panel3.add(profileLabel);
	}//setProfile

	private void setLabel(JLabel label, int y) {
		label.setBounds(20,y,80,20);
		label.setFont(new Font("맑은 고딕",Font.PLAIN,14));
		label.setOpaque(true);        // JLabel 색상변경하기 위해서는 setOpaque를 true로 해줘야한다
		label.setBackground(Color.white);
		panel3.add(label);
	}//setLabel

	public static void main(String[] args) {
		new FManager1();
	}//main

	public class MouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tableM.getSelectedRow();

			if(row != -1) {
				//회원 정보 띄우기
				for(i=0;i<tableM.getColumnCount();i++) {
					textFields[i].setText(tableM.getValueAt(row, i).toString());
				}
				String id = tableM.getValueAt(row, 1).toString();
				MembersBean mb = dao.getMemberById(id);
				String path = mb.getProfile();
				setProfile(path);

				//회원이 등록한 맛집테이블 띄우기
				pdao = new PlaceDao();
				int no = Integer.valueOf(tableM.getValueAt(row, 0).toString());
				plists = pdao.getPlaceByNo(no);
				getPlacebyNo(plists);
				pscrollPane.setVisible(true);
				//회원이 등록한 맛집테이블
				//선택한 row에서 회원 no을 가져옴
				//no을 가지고 가서 맛집테이블 검색 "select * from place where no = ?"
				//결과 lists에 담아서 가져온뒤에 tableP에 담고 패널 2에 올리기
			}
		}
	}//MouseHandler

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj == searchB) { //회원검색
			String word = searchT.getText();
			lists = dao.getAllByName(word);
			getAllMembers(lists);
			searchT.setText("");
			clearPanel3();
		}

		if(obj == btn[0]) { //수정
			updateMembers();
		}
		if(obj == btn[1]) { //삭제
			deleteMembers();
		}
		if(obj == btn[2]) { //이미지 업로드
			updateProfile();
		}
		if(obj == btn[3]) { //이미지 삭제
			deleteProfile();
		}
		
		if(obj == members) {
			new FManager1();
			dispose();
		}

		if(obj == place) {
			new FManager2();
			dispose();
		}
		
		if(obj == logout) {
			new Main();
			dispose();
		}
		
	}//actionPerformed

	private void deleteProfile() {
		int row = tableM.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "레코드를 선택하세요","오류발생",JOptionPane.WARNING_MESSAGE);
			return;
		}

		int no = Integer.valueOf(textFields[0].getText());
		int result = dao.updateProfile("", no);

		if(result != -1) {
			setProfile("");
		}

	}//deleteProfile

	private void updateProfile() {

		int row = tableM.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "레코드를 선택하세요","오류발생",JOptionPane.WARNING_MESSAGE);
			return;
		}

		JFileChooser fileChooser = new JFileChooser("C:\\Java_yhj\\1차프로젝트_윤효정\\images");
		fileChooser.setDialogTitle("이미지 불러오기");
		fileChooser.setFileFilter(new FileNameExtensionFilter("PNG File","png"));
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.showOpenDialog(this);

		File file = fileChooser.getSelectedFile();
		if(file != null) {
			String path = file.toString();
			int no = Integer.valueOf(textFields[0].getText());
			int result = dao.updateProfile(path, no);

			if(result != -1) {
				setProfile(path);
			}
		}

	}//updateProfile

	private void deleteMembers() {

		int cnt = -1;
		int row = tableM.getSelectedRow();

		if(textFields[0].getText().equals("1")) { //문자열 비교는 equals.. 제발 잊지말자
			JOptionPane.showMessageDialog(this, "관리자 계정은 삭제할 수 없습니다.","오류발생",JOptionPane.WARNING_MESSAGE);
			return;
		}

		if(row != -1) {
			int no = Integer.valueOf(textFields[0].getText());
			cnt = dao.deleteMembers(no);

			if(cnt != -1) {
				getAllMembers();
				clearPanel3();
				setTable();
			}
		}else {
			JOptionPane.showMessageDialog(this, "삭제할 레코드를 선택하세요.","오류발생",JOptionPane.WARNING_MESSAGE);
		}
	}//deleteMembers

	private void clearPanel3() {
		for(i=0;i<textFields.length;i++) {
			textFields[i].setText("");
		}
		setProfile("");
	}//clearPanel3

	private void updateMembers() {

		int row = tableM.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "수정할 레코드를 선택하세요","오류발생",JOptionPane.WARNING_MESSAGE);
			return;
		}

		boolean result = checkData();
		if(result) {

			int no = Integer.valueOf(textFields[0].getText());
			String id = textFields[1].getText();
			String pw = textFields[2].getText();
			String name = textFields[3].getText();
			String birth = textFields[4].getText();
			String pnum = textFields[5].getText();
			String nickname = textFields[6].getText();
			int rating = Integer.valueOf(textFields[7].getText());
			String joinDate = textFields[8].getText();
			int pcount = Integer.valueOf(textFields[9].getText());

			MembersBean mb = new MembersBean();
			mb.setNo(no);
			mb.setId(id);
			mb.setPw(pw);
			mb.setName(name);
			mb.setBirth(birth);
			mb.setPnum(pnum);
			mb.setNickname(nickname);
			mb.setRating(rating);
			mb.setJoinDate(joinDate);
			mb.setPcount(pcount);

			int cnt = -1;
			try {
				cnt = dao.updateMembers(mb);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "중복된 ID입니다.","오류발생",JOptionPane.INFORMATION_MESSAGE);
				textFields[1].setText("");
				textFields[1].requestFocus();
			}

			if(cnt != -1) {
				JOptionPane.showMessageDialog(this, "수정 완료되었습니다.","수정 성공!",JOptionPane.PLAIN_MESSAGE);
				getAllMembers();
			}
		}
		//업데이트할 때 id가 unique 제약에 걸려서 오류 발생하면 예외처리 해주기.
	}//updateMembers

	private void getAllMembers() {
		lists = dao.getAllMembers(); //전체 테이블 데이터 가져오기
		rowData = new Object[lists.size()][columnNames.length]; //2차원 배열 만들기
		fillData(); //만든 2차원 배열에 테이블 데이터 값 넣기
		//*******순서 유의

		tableM = new JTable(rowData, columnNames); //(내용,제목)
		scrollPane.setViewportView(tableM);//새로 만들어진 테이블을 스크롤페인에 올리기 = 새로고침
		tableM.addMouseListener(new MouseHandler()); //새로만든테이블에도 리스너부착
		setTable();
	}//getAllMembers


	private void getAllMembers(ArrayList<MembersBean> lists) {
		rowData = new Object[lists.size()][columnNames.length]; 
		fillData(); 

		tableM = new JTable(rowData, columnNames); 
		scrollPane.setViewportView(tableM);
		tableM.addMouseListener(new MouseHandler()); 
		setTable();
	}//getAllMembers
	
	private void getPlacebyNo(ArrayList<PlaceBean> plists) {
		rowData = new Object[plists.size()][pcolumnNames.length]; 
		fillPData(); 
		
		tableP = new JTable(rowData, pcolumnNames); 
		pscrollPane.setViewportView(tableP);
		setPTable();
	}//getPlacebyNo

	private boolean checkData() {
		if(textFields[1].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "ID를 입력하세요.","오류발생",JOptionPane.INFORMATION_MESSAGE);
			textFields[1].requestFocus(); //오류발생한 곳에 커서를 깜박거리게 함
			return false; // 밑으로 내려가지 못하게 함 => 문제가 발생하면 false 리턴
		}else if(textFields[1].getText().length()>12 || textFields[1].getText().length()<4) {
			JOptionPane.showMessageDialog(this, "ID는 4~12자리로 입력하세요.","오류발생",JOptionPane.INFORMATION_MESSAGE);
			textFields[1].setText("");
			textFields[1].requestFocus();
			return false;
		}

		if(textFields[2].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.","오류발생",JOptionPane.ERROR_MESSAGE);
			textFields[2].setText("");
			textFields[2].requestFocus();
			return false;
		}else if(textFields[2].getText().length()>12 || textFields[2].getText().length()<4) {
			JOptionPane.showMessageDialog(this, "비밀번호는 4~12자리로 입력하세요.","오류발생",JOptionPane.INFORMATION_MESSAGE);
			textFields[2].setText("");
			textFields[2].requestFocus();
			return false;
		}

		if(textFields[3].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "이름을 입력하세요.","오류발생",JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane.YES_NO_CANCEL_OPTION
			textFields[3].setText("");
			textFields[3].requestFocus();
			return false;
		}else if(textFields[3].getText().length() >10) {
			JOptionPane.showMessageDialog(this, "이름은 1~10자리로 입력하세요.","오류발생",JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane.YES_NO_CANCEL_OPTION
			textFields[3].setText("");
			textFields[3].requestFocus();
			return false;
		}

		if(textFields[4].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "생년월일을 입력하세요.","오류발생",JOptionPane.QUESTION_MESSAGE);
			textFields[4].setText("");
			textFields[4].requestFocus();
			return false;
		}else {
			try { //날짜형식에 맞게 입력됐는지 확인
				Date.valueOf(textFields[4].getText());			
			}catch(IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this, "생년월일을 날짜 형식에 맞게 입력하세요.","오류발생",JOptionPane.WARNING_MESSAGE);
				textFields[4].setText("");
				textFields[4].requestFocus();
				return false;
			}
		}

		if(textFields[5].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "전화번호를 입력하세요.","오류발생",JOptionPane.WARNING_MESSAGE);
			textFields[5].setText("");
			textFields[5].requestFocus();
			return false;
		}if(Pattern.matches("^01[0-9]-[0-9]{4}-[0-9]{4}$", textFields[5].getText())) {
			// ^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$
		}else {
			JOptionPane.showMessageDialog(textFields[5], "010-0000-0000 형식으로 입력하세요","오류발생",JOptionPane.INFORMATION_MESSAGE);
			textFields[5].setText("");
			textFields[5].requestFocus();
			return false;
		}

		if(textFields[6].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "닉네임을 입력하세요.","오류발생",JOptionPane.WARNING_MESSAGE);
			textFields[6].setText("");
			textFields[6].requestFocus();
			return false;
		}else if(textFields[6].getText().length() >10) {
			JOptionPane.showMessageDialog(this, "닉네임은 1~10자리로 입력하세요.","오류발생",JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane.YES_NO_CANCEL_OPTION
			textFields[6].setText("");
			textFields[6].requestFocus();
			return false;
		}

		return true;
	}//checkData

}
