import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;

public class FManager2 extends JFrame implements ActionListener{
	private int i;
	private PlaceDao dao = new PlaceDao();
	private ArrayList<PlaceBean> lists = null;
	private Object[] columnNames = {"NO","ID","이름","NUM","가게명","분류","등록일","지역명","주소","링크","좋아요수"};
	private Object[][] rowData;
	private JTable tableM =null;

	private JScrollPane scrollPane = null;
	private Container contentPane;
	private JPanel panel;
	private JPanel panel2;
	private JButton members = null;
	private JButton place = null;
	private JButton searchB = null;
	private JButton logout = null;
	private JLabel searchL = null;
	private JLabel profileLabel = new JLabel();
	private JTextField searchT = new JTextField(15);
	private JTextField[] textFields = new JTextField[columnNames.length];
	private JButton[] btn = new JButton[3];
	private Choice code = new Choice();
	private Choice location = new Choice();

	FManager2(){
		super("토마토 플레이스 - 관리자님 환영합니다.");
		setSize(1320,880);
		setLocationRelativeTo(null); // 이러면 화면 중앙에 배치됨
		setResizable(false);

		//맛집회원 조인테이블
		lists = dao.getJoinTable();
		rowData = new Object[lists.size()][columnNames.length];
		fillData();

		tableM = new JTable(rowData, columnNames);
		scrollPane = new JScrollPane(tableM);

		compose(); 
		setTable();

		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//생성자

	private void setTable() {

		tableM.setAutoCreateRowSorter(true);
		TableRowSorter sorter = new TableRowSorter(tableM.getModel());
		tableM.setRowSorter(sorter); //table 오름차순 내림차순 정렬하는 메서드

		tableM.addMouseListener(new MouseHandler());

		tableM.getColumn("NO").setPreferredWidth(10);
		tableM.getColumn("ID").setPreferredWidth(50);
		tableM.getColumn("이름").setPreferredWidth(25);
		tableM.getColumn("NUM").setPreferredWidth(10);
		tableM.getColumn("가게명").setPreferredWidth(100);
		tableM.getColumn("분류").setPreferredWidth(10);
		tableM.getColumn("등록일").setPreferredWidth(50);
		tableM.getColumn("지역명").setPreferredWidth(20);
		tableM.getColumn("주소").setPreferredWidth(75);
		tableM.getColumn("링크").setPreferredWidth(50);
		tableM.getColumn("좋아요수").setPreferredWidth(10);

	}//setTable

	private void fillData() {
		//members 테이블 한 줄씩 가져와서 값을 2차원 배열 rowData에 넣는다.
		int j=0;
		for(i=0;i<lists.size();i++) {
			rowData[i][j++] = lists.get(i).getNo();
			rowData[i][j++] = lists.get(i).getId();
			rowData[i][j++] = lists.get(i).getName();
			rowData[i][j++] = lists.get(i).getNum();
			rowData[i][j++] = lists.get(i).getPname();
			rowData[i][j++] = lists.get(i).getCode();
			rowData[i][j++] = lists.get(i).getDate();
			rowData[i][j++] = lists.get(i).getLocation();
			rowData[i][j++] = lists.get(i).getAddr();
			rowData[i][j++] = lists.get(i).getLink();
			rowData[i][j++] = lists.get(i).getLikes();
			j = 0;
		}
	}//fillData

	private void compose() {
		contentPane = getContentPane();
		contentPane.setLayout(null);
		//setSize(1320,880);
		//패널1
		panel = new JPanel();
		panel.setSize(1220,800);
		panel.setLocation(20, 20);
		panel.setBorder(new LineBorder(Color.black,1)); //패널 테두리 설정
		panel.setLayout(null);
		contentPane.add(panel);

		//테이블 스크롤페인
		scrollPane.setBounds(0, 450, 1220, 350);
		scrollPane.setBorder(new LineBorder(Color.black,1));
		panel.add(scrollPane);

		//검색 라벨
		searchL = new JLabel("맛집 검색"); //줄바꿈이 적용되지 않아서 html 문법 활용.
		searchL.setBounds(940,420,100,30);
		searchL.setFont(new Font("맑은 고딕",Font.PLAIN,14));
		searchL.setOpaque(true);        // JLabel 색상변경하기 위해서는 setOpaque를 true로 해줘야한다
		searchL.setHorizontalAlignment(JLabel.CENTER);
		searchL.setBackground(Color.gray);
		searchL.setForeground(Color.white);
		panel.add(searchL);

		//검색 텍스트 필드
		searchT.setBounds(1040, 420, 150, 30);
		searchT.setBorder(null);
		panel.add(searchT);

		//검색 버튼
		ImageIcon searchIcon = new ImageIcon("images/search.png");
		searchB = new JButton(searchIcon);
		searchB.setBounds(1190,420,30,30);
		searchB.setBackground(Color.white);
		searchB.addActionListener(this);
		panel.add(searchB);

		//패널2
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(20,50,1180,350);
		panel2.setBackground(Color.white);
		panel2.setBorder(new LineBorder(Color.black,1));
		panel.add(panel2);

		//수정, 삭제, 추가 버튼
		String[] btnNames = {"수정","삭제","추가"};

		for(i=0;i<btn.length;i++) {
			btn[i] = new JButton(btnNames[i]);
			btn[i].setMargin(new Insets(0,0,0,0));
			btn[i].setFont(new Font("맑은 고딕",Font.PLAIN,14));
			btn[i].setContentAreaFilled(false);
			btn[i].setFocusPainted(false);
			btn[i].addActionListener(this);
			panel.add(btn[i]);
		}

		btn[0].setBounds(20,20,50,30);
		btn[1].setBounds(70,20,50,30);
		btn[2].setBounds(120,20,50,30);

		//맛집 정보 라벨
		JLabel[] label = new JLabel[columnNames.length];

		for(i=0;i<columnNames.length;i++) {
			label[i] = new JLabel(columnNames[i].toString());

			if(i<7) {
				label[i].setBounds(40, 28+40*i, 80, 20);
			}else {
				label[i].setBounds(40+350, 28+40*(i-7), 80, 20);
			}

			label[i].setFont(new Font("맑은 고딕",Font.PLAIN,14));
			label[i].setOpaque(true);        // JLabel 색상변경하기 위해서는 setOpaque를 true로 해줘야한다
			label[i].setBackground(Color.white);
			panel2.add(label[i]);
		}

		//맛집 정보 텍스트필드
		for(i=0;i<columnNames.length;i++) {
			if(i == 5 || i == 7) {
				continue;
			}
			textFields[i] = new JTextField(15);

			if(i<7) {
				textFields[i].setBounds(130, 28+40*i, 180, 20);
			}else {
				textFields[i].setBounds(130+350, 28+40*(i-7), 180, 20);
			}
			panel2.add(textFields[i]);
		}
		textFields[0].setEnabled(false);
		textFields[1].setEnabled(false);
		textFields[2].setEnabled(false);
		textFields[3].setEnabled(false);

		//초이스
		code = new Choice();
		code.setBounds(130, 28+40*5, 180, 20);
		code.setFont(new Font("Default",Font.PLAIN,12));
		code.add("1. 한식");
		code.add("2. 중식");
		code.add("3. 일식");
		code.add("4. 양식");
		code.add("5. 기타");
		code.add("6. 카페");
		panel2.add(code);

		location = new Choice();
		location.setBounds(130+350, 28, 180, 20);
		location.setFont(new Font("맑은 고딕",Font.PLAIN,12));
		location.add("동교동");
		location.add("서교동");
		location.add("연남동");
		panel2.add(location);

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
		panel.add(profileLabel);
	}//setProfile

	public static void main(String[] args) {
		new FManager2();
	}//main

	public class MouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tableM.getSelectedRow();

			if(row != -1) {
				//회원 정보 띄우기
				for(i=0;i<tableM.getColumnCount();i++) {
					if(i == 5) {
						code.select(tableM.getValueAt(row, i).toString());
						continue;
					}
					if(i == 7) {
						location.select(tableM.getValueAt(row, i).toString());
						continue;
					}
					textFields[i].setText(tableM.getValueAt(row, i).toString());
				}
				//				//프로필 띄우기
				//				String id = tableM.getValueAt(row, 1).toString();
				//				MembersBean mb = dao.getMemberById(id);
				//				String path = mb.getProfile();
				//				setProfile(path);

			}
		}
	}//MouseHandler

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj == searchB) { //맛집검색
			String word = searchT.getText();
			lists = dao.getAllByName(word);
			getAllPlace(lists);
			searchT.setText("");
			clearPanel1();
		}

		if(obj == btn[0]) { //수정
			updatePlace();
		}
		if(obj == btn[1]) { //삭제
			deletePlace();
		}
		if(obj == btn[2]) { //추가
			//			updateProfile();
			new FInsertPlace("맛집 등록");
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

	private void deletePlace() {

		int cnt = -1;
		int row = tableM.getSelectedRow();

		if(row != -1) {
			int num = Integer.valueOf(textFields[3].getText());
			cnt = dao.deletePlace(num);

			if(cnt != -1) {
				getJoinTable();
				clearPanel1();
				setTable();
			}
		}else {
			JOptionPane.showMessageDialog(this, "삭제할 레코드를 선택하세요.","오류발생",JOptionPane.WARNING_MESSAGE);
		}
	}//deleteMembers

	private void clearPanel1() {
		for(i=0;i<textFields.length;i++) {
			if(i == 5) {
				code.select(0);
				continue;
			}
			if(i == 7) {
				location.select(0);
				continue;
			}
			textFields[i].setText("");
		}
		//		setProfile("");
	}//clearPanel1

	private void updatePlace() {

		int row = tableM.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "수정할 레코드를 선택하세요","오류발생",JOptionPane.WARNING_MESSAGE);
			return;
		}

		boolean result = checkData();
		if(result) {

			int no = Integer.valueOf(textFields[0].getText());
			String id = textFields[1].getText();
			String name = textFields[2].getText();

			int num = Integer.valueOf(textFields[3].getText());
			String pname = textFields[4].getText();
			String code = this.code.getSelectedItem();
			String date = textFields[6].getText();
			String location = this.location.getSelectedItem();
			String addr = textFields[8].getText();

			String link = textFields[9].getText();
			int likes = Integer.valueOf(textFields[10].getText());

			PlaceBean pb = new PlaceBean();
			pb.setNo(no);
			pb.setId(id);
			pb.setName(name);

			pb.setNum(num);
			pb.setPname(pname);
			pb.setCode(code);

			pb.setDate(date);
			pb.setLocation(location);
			pb.setAddr(addr);

			pb.setLink(link);
			pb.setLikes(likes);

			int cnt = -1;
			try {
				cnt = dao.updatePlace(pb);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "수정 오류 발생.","오류발생",JOptionPane.INFORMATION_MESSAGE);
			}

			if(cnt != -1) {
				JOptionPane.showMessageDialog(this, "수정 완료되었습니다.","수정 성공!",JOptionPane.PLAIN_MESSAGE);
				getJoinTable();
			}
		}
		//업데이트할 때 id가 unique 제약에 걸려서 오류 발생하면 예외처리 해주기.
	}//updatePlace

	private void getJoinTable() {
		lists = dao.getJoinTable(); //전체 테이블 데이터 가져오기
		rowData = new Object[lists.size()][columnNames.length]; //2차원 배열 만들기
		fillData(); //만든 2차원 배열에 테이블 데이터 값 넣기
		//*******순서 유의

		tableM = new JTable(rowData, columnNames); //(내용,제목)
		scrollPane.setViewportView(tableM);//새로 만들어진 테이블을 스크롤페인에 올리기 = 새로고침
		tableM.addMouseListener(new MouseHandler()); //새로만든테이블에도 리스너부착
		setTable();
	}//getJoinTable


	private void getAllPlace(ArrayList<PlaceBean> lists) {
		rowData = new Object[lists.size()][columnNames.length]; 
		fillData(); 

		tableM = new JTable(rowData, columnNames); 
		scrollPane.setViewportView(tableM);
		tableM.addMouseListener(new MouseHandler()); 
		setTable();
	}//getAllPlace

	private boolean checkData() {
		if(textFields[4].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "가게명을 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
			textFields[4].setText("");
			textFields[4].requestFocus();
			return false;
		}

		if(textFields[6].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "등록일을 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
			textFields[6].setText("");
			textFields[6].requestFocus();
			return false;
		}else {
			try { //날짜형식에 맞게 입력됐는지 확인
				Date.valueOf(textFields[6].getText());			
			}catch(IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this, "등록일을 날짜 형식에 맞게 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
				textFields[6].setText("");
				textFields[6].requestFocus();
				return false;
			}
		}

		if(textFields[8].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "주소를 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
			textFields[8].setText("");
			textFields[8].requestFocus();
			return false;
		}

		if(textFields[9].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "링크를 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
			textFields[9].setText("");
			textFields[9].requestFocus();
			return false;
		}

		if(textFields[10].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "좋아요수를 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
			textFields[10].setText("");
			textFields[10].requestFocus();
			return false;
		}else {
			try {
				Integer.valueOf(textFields[10].getText());
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "숫자만 입력하세요.","에러발생",JOptionPane.INFORMATION_MESSAGE);
				textFields[10].setText("");
				textFields[10].requestFocus();
				return false;
			}
		}

		return true;
	}//checkData

}
