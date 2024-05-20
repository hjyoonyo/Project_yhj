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


public class FMember extends JFrame implements ActionListener{
	private int i;
	private String id;

	private PlaceDao dao = new PlaceDao();
	private MembersDao mdao;
	private MembersBean mb;
	private ArrayList<PlaceBean> lists = null;
	private ArrayList<PlaceBean> plists = null;
	private String[] labelname = {"ID","비밀번호","이름","생년월일","전화번호","별명","가입일자","등록수"};
	private Object[] columnNames = {"NUM","가게명","분류","등록일","지역명","주소","링크","좋아요수"};
	private Object[][] rowData;
	private JTable tableM =null;
	private JTable tableP =null;

	private JScrollPane scrollPane = null;
	private JScrollPane pscrollPane = new JScrollPane();
	private Container contentPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;	
	private JButton searchB = null;
	private JButton logout = null;
	private JLabel searchL = null;
	private JLabel profileLabel = new JLabel();
	private JTextField searchT = new JTextField(15);
	private JTextField[] mtextFields = new JTextField[labelname.length];
	private JTextField[] ptextFields = new JTextField[columnNames.length];
	private JButton[] btn = new JButton[4];
	private Choice code = new Choice();
	private Choice location = new Choice();

	FMember(){

	}

	FMember(String id){
		super(id + "님 환영합니다.");
		this.id = id;

		setSize(1300,780);
		setLocationRelativeTo(null);
		setResizable(false);

		//맛집테이블
		lists = dao.getAllPlace();
		rowData = new Object[lists.size()][columnNames.length];
		fillData();

		tableM = new JTable(rowData, columnNames);
		scrollPane = new JScrollPane(tableM);

		mdao = new MembersDao();
		mb = mdao.getMemberById(id);

		compose();
		setTable();

		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setTable() {

		tableM.setAutoCreateRowSorter(true);
		TableRowSorter sorter = new TableRowSorter(tableM.getModel());
		tableM.setRowSorter(sorter); //table 오름차순 내림차순 정렬하는 메서드

		tableM.addMouseListener(new MouseHandler());

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
			rowData[i][j++] = plists.get(i).getLikes();
			j = 0;
		}
	}//fillPData

	private void compose() {
		contentPane = getContentPane();
		contentPane.setLayout(null);

		//패널1
		panel1 = new JPanel();
		panel1.setSize(600,700);
		panel1.setLocation(20, 20);
		panel1.setBorder(new LineBorder(Color.black,1)); //패널 테두리 설정
		panel1.setBorder(new LineBorder(Color.black,1)); //패널 테두리 설정
		panel1.setLayout(null);
		contentPane.add(panel1);

		scrollPane.setBounds(0, 30, 600, 350);
		scrollPane.setBorder(new LineBorder(Color.black,1));
		panel1.add(scrollPane);

		//검색 라벨
		searchL = new JLabel("맛집 검색"); //줄바꿈이 적용되지 않아서 html 문법 활용.
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

		//패널3
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(20, 400, 560, 280);
		panel3.setBackground(Color.white);
		panel3.setBorder(new LineBorder(Color.black,1));
		panel1.add(panel3);

		//맛집 정보 라벨
		JLabel[] plabel = new JLabel[columnNames.length];

		for(i=0;i<columnNames.length;i++) {
			plabel[i] = new JLabel(columnNames[i].toString());

			if(i<4) {
				plabel[i].setBounds(20, 40+50*i, 50, 20);
			}else {
				plabel[i].setBounds(20+270, 40+50*(i-4), 60, 20);
			}

			plabel[i].setFont(new Font("맑은 고딕",Font.PLAIN,13));
			plabel[i].setOpaque(true);        // JLabel 색상변경하기 위해서는 setOpaque를 true로 해줘야한다
			plabel[i].setBackground(Color.white);
			panel3.add(plabel[i]);
		}

		//클릭한 행의 맛집 정보 텍스트필드
		for(i=0;i<columnNames.length;i++) {
			if(i == 2 || i == 5) {
				continue;
			}
			ptextFields[i] = new JTextField(15);
			if(i<4) {
				ptextFields[i].setBounds(80, 40+50*i, 180, 20);
			}else {
				ptextFields[i].setBounds(80+280, 40+50*(i-4), 180, 20);
			}
			panel3.add(ptextFields[i]);
		}
		ptextFields[0].setEnabled(false);

		//초이스
		code = new Choice();
		code.setBounds(80, 40+50*2, 180, 20);
		code.setFont(new Font("Default",Font.PLAIN,12));
		code.add("1. 한식");
		code.add("2. 중식");
		code.add("3. 일식");
		code.add("4. 양식");
		code.add("5. 기타");
		code.add("6. 카페");
		panel3.add(code);

		location = new Choice();
		location.setBounds(80+280, 40+50, 180, 20);
		location.setFont(new Font("맑은 고딕",Font.PLAIN,12));
		location.add("동교동");
		location.add("서교동");
		location.add("연남동");
		panel3.add(location);

		//패널2
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setSize(600,700);
		panel2.setLocation(640, 20);
		panel2.setBorder(new LineBorder(Color.black,1));
		contentPane.add(panel2);

		//수정, 탈퇴, 이미지업로드, 이미지삭제 버튼
		String[] btnNames = {"수정","탈퇴","이미지 업로드","이미지 삭제"};

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

		//패널4
		panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(20, 50, 560, 630);
		panel4.setBackground(Color.white);
		panel4.setBorder(new LineBorder(Color.black,1));
		panel2.add(panel4);

		//회원 정보 라벨
		JLabel[] label = new JLabel[labelname.length];

		for(i=0;i<labelname.length;i++) {
			label[i] = new JLabel(labelname[i]);
			setLabel(label[i], 25+40*i);
		}

		//회원 정보 텍스트필드
		for(i=0;i<labelname.length;i++) {
			mtextFields[i] = new JTextField(15);
			mtextFields[i].setBounds(100, 28+40*i, 150, 20);
			panel4.add(mtextFields[i]);
		}
		mtextFields[0].setEnabled(false);
		mtextFields[6].setEnabled(false);
		mtextFields[7].setEnabled(false);

		mtextFields[0].setText(id);
		mtextFields[1].setText(mb.getPw());
		mtextFields[2].setText(mb.getName());
		mtextFields[3].setText(mb.getBirth());
		mtextFields[4].setText(mb.getPnum());
		mtextFields[5].setText(mb.getNickname());
		mtextFields[6].setText(mb.getJoinDate());
		mtextFields[7].setText(String.valueOf(mb.getPcount()));

		//프로필 이미지
		String path = mb.getProfile();
		setProfile(path);

		//회원이 등록한 맛집테이블
		//		pscrollPane.setBounds(20, 420, 560, 350);
		//		pscrollPane.setBorder(new LineBorder(Color.black,1));
		//		panel2.add(pscrollPane);
		//		pscrollPane.setVisible(false);

		//로그아웃 버튼
		logout = new JButton("<HTML>로<br>그<br>아<br>웃<HTML>");
		logout.setMargin(new Insets(0,0,0,0));
		logout.setBounds(1240,640,20,80);
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
		panel4.add(profileLabel);
	}//setProfile

	private void setLabel(JLabel label, int y) {
		label.setBounds(20,y,80,20);
		label.setFont(new Font("맑은 고딕",Font.PLAIN,14));
		label.setOpaque(true);        // JLabel 색상변경하기 위해서는 setOpaque를 true로 해줘야한다
		label.setBackground(Color.white);
		panel4.add(label);
	}//setLabel

	public static void main(String[] args) {
		new FMember("sook2");
	}

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

//		if(obj == btn[0]) { //수정
//			updateMembers();
//		}
//		if(obj == btn[1]) { //탈퇴
//			deleteMembers();
//		}
//		if(obj == btn[2]) { //이미지 업로드
//			updateProfile();
//		}
//
//		if(obj == btn[2]) { //이미지 삭제
//			deleteProfile();
//		}

		if(obj == logout) { //로그아웃
			new Main();
			dispose();
		}
	}

	private void deleteProfile() {
		MembersDao dao = new MembersDao();

		int no = Integer.valueOf(mtextFields[0].getText());
		int result = dao.updateProfile("", no);

		if(result != -1) {
			setProfile("");
		}

	}

	private void updateProfile() {
		MembersDao dao = new MembersDao();

		JFileChooser fileChooser = new JFileChooser("C:\\Java_yhj\\1차프로젝트_윤효정\\images");
		fileChooser.setDialogTitle("이미지 불러오기");
		fileChooser.setFileFilter(new FileNameExtensionFilter("PNG File","png"));
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.showOpenDialog(this);

		File file = fileChooser.getSelectedFile();
		if(file != null) {
			String path = file.toString();
			int no = Integer.valueOf(mtextFields[0].getText());
			int result = dao.updateProfile(path, no);

			if(result != -1) {
				setProfile(path);
			}
		}
	}

	private void deleteMembers() {
		int cnt=-1;
		MembersDao dao = new MembersDao();
		int no = mb.getNo();
		cnt = dao.deleteMembers(no);
		
		if(cnt != -1) {
			JOptionPane.showMessageDialog(this, "탈퇴되었습니다.","탈퇴 성공",JOptionPane.PLAIN_MESSAGE);
			new Main();
			dispose();
		}
		
		
	}//deleteMembers

	private void updateMembers() {

		boolean result = checkData();
		if(result) {

			String id = mtextFields[0].getText();
			String pw = mtextFields[1].getText();
			String name = mtextFields[2].getText();
			String birth = mtextFields[3].getText();
			String pnum = mtextFields[4].getText();
			String nickname = mtextFields[5].getText();
			String joinDate = mtextFields[6].getText();
			int pcount = Integer.valueOf(mtextFields[7].getText());

			MembersBean mb = new MembersBean();
			mb.setNo(this.mb.getNo());
			mb.setId(id);
			mb.setPw(pw);
			mb.setName(name);
			mb.setBirth(birth);
			mb.setPnum(pnum);
			mb.setNickname(nickname);
			mb.setRating(this.mb.getRating());
			mb.setJoinDate(joinDate);
			mb.setPcount(pcount);

			MembersDao dao = new MembersDao();
			int cnt = -1;
			try {
				cnt = dao.updateMembers(mb);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "중복된 ID입니다.","오류발생",JOptionPane.INFORMATION_MESSAGE);
				mtextFields[1].setText("");
				mtextFields[1].requestFocus();
			}

			if(cnt != -1) {
				JOptionPane.showMessageDialog(this, "수정 완료되었습니다.","수정 성공!",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}//updateMembers

	private boolean checkData() {

		if(mtextFields[1].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.","오류발생",JOptionPane.ERROR_MESSAGE);
			mtextFields[1].setText("");
			mtextFields[1].requestFocus();
			return false;
		}else if(mtextFields[1].getText().length()>12 || mtextFields[2].getText().length()<4) {
			JOptionPane.showMessageDialog(this, "비밀번호는 4~12자리로 입력하세요.","오류발생",JOptionPane.INFORMATION_MESSAGE);
			mtextFields[1].setText("");
			mtextFields[1].requestFocus();
			return false;
		}

		if(mtextFields[2].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "이름을 입력하세요.","오류발생",JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane.YES_NO_CANCEL_OPTION
			mtextFields[2].setText("");
			mtextFields[2].requestFocus();
			return false;
		}else if(mtextFields[2].getText().length() >10) {
			JOptionPane.showMessageDialog(this, "이름은 1~10자리로 입력하세요.","오류발생",JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane.YES_NO_CANCEL_OPTION
			mtextFields[2].setText("");
			mtextFields[2].requestFocus();
			return false;
		}

		if(mtextFields[3].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "생년월일을 입력하세요.","오류발생",JOptionPane.QUESTION_MESSAGE);
			mtextFields[3].setText("");
			mtextFields[3].requestFocus();
			return false;
		}else {
			try { //날짜형식에 맞게 입력됐는지 확인
				Date.valueOf(mtextFields[3].getText());			
			}catch(IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this, "생년월일을 날짜 형식에 맞게 입력하세요.","오류발생",JOptionPane.WARNING_MESSAGE);
				mtextFields[3].setText("");
				mtextFields[3].requestFocus();
				return false;
			}
		}

		if(mtextFields[4].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "전화번호를 입력하세요.","오류발생",JOptionPane.WARNING_MESSAGE);
			mtextFields[4].setText("");
			mtextFields[4].requestFocus();
			return false;
		}if(Pattern.matches("^01[0-9]-[0-9]{4}-[0-9]{4}$", mtextFields[5].getText())) {
			// ^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$
		}else {
			JOptionPane.showMessageDialog(mtextFields[5], "010-0000-0000 형식으로 입력하세요","오류발생",JOptionPane.INFORMATION_MESSAGE);
			mtextFields[4].setText("");
			mtextFields[4].requestFocus();
			return false;
		}

		if(mtextFields[5].getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "닉네임을 입력하세요.","오류발생",JOptionPane.WARNING_MESSAGE);
			mtextFields[5].setText("");
			mtextFields[5].requestFocus();
			return false;
		}else if(mtextFields[5].getText().length() >10) {
			JOptionPane.showMessageDialog(this, "닉네임은 1~10자리로 입력하세요.","오류발생",JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane.YES_NO_CANCEL_OPTION
			mtextFields[5].setText("");
			mtextFields[5].requestFocus();
			return false;
		}

		return true;
	}

	private void getAllPlace(ArrayList<PlaceBean> lists) {
		rowData = new Object[lists.size()][columnNames.length]; 
		fillData(); 

		tableM = new JTable(rowData, columnNames); 
		scrollPane.setViewportView(tableM);
		tableM.addMouseListener(new MouseHandler()); 
		setTable();
	}//getAllPlace

	private void clearPanel1() {
		for(i=0;i<ptextFields.length;i++) {
			if(i == 2) {
				code.select(0);
				continue;
			}
			if(i == 5) {
				location.select(0);
				continue;
			}
			ptextFields[i].setText("");
		}
	}//clearPanel1

	public class MouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tableM.getSelectedRow();

			if(row != -1) {
				//맛집 정보 띄우기
				for(i=0;i<tableM.getColumnCount();i++) {
					if(i == 2) {
						code.select(tableM.getValueAt(row, i).toString());
						continue;
					}
					if(i == 5) {
						location.select(tableM.getValueAt(row, i).toString());
						continue;
					}
					ptextFields[i].setText(tableM.getValueAt(row, i).toString());
				}
			}
		}
	}//MouseHandler
















}
