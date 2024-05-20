import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener{

	JTextField txtId = new JTextField(15);
	JPasswordField txtPw = new JPasswordField(15);
	JButton login = null;
	JButton join = null;
	
	

	public Main() {
		super("토마토 플레이스 Login");

		compose();

		setSize(400,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}//생성자


	private void compose() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//로고 삽입
		ImageIcon tomato = new ImageIcon("images/tomato.png"); //이미지 불러오기
		Image tomatoImg = tomato.getImage();
		Image updateLogo = tomatoImg.getScaledInstance(270, 270, Image.SCALE_SMOOTH); //불러온 이미지 크기 조절
		ImageIcon logo = new ImageIcon(updateLogo); //사이즈 조절된 이미지로 아이콘 생성

		JLabel imgLabel = new JLabel();

		imgLabel.setIcon(logo);
		imgLabel.setBounds(100, 50, 200, 200);
		imgLabel.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(imgLabel);

		//아이디, 비번 jlabel
		JLabel lbid = new JLabel("ID");
		JLabel lbpw = new JLabel("PW");

		lbid.setBounds(100,300,30,20);
		lbid.setFont(new Font("Arial",Font.PLAIN,16));
		lbpw.setBounds(100,350,30,20);
		lbpw.setFont(new Font("Arial",Font.PLAIN,16));
		contentPane.add(lbid);
		contentPane.add(lbpw);

		//텍스트필드
		txtId.setBounds(150,300,150,30);
		txtId.setFont(new Font("Arial",Font.PLAIN,16));
		txtPw.setBounds(150,350,150,30);
		txtPw.setFont(new Font("Arial",Font.PLAIN,16));
		txtPw.setEchoChar('*');
		contentPane.add(txtId);
		contentPane.add(txtPw);

		//로그인 버튼
		login = new JButton("로그인");
		login.setBounds(100,430,90,30);
		login.setFont(new Font("맑은 고딕",Font.PLAIN,14));
		login.setBackground(Color.GRAY);
		login.setForeground(Color.white);
		login.addActionListener(this);

		//회원가입 버튼
		join = new JButton("회원가입");
		join.setBounds(200,430,100,30);
		join.setFont(new Font("맑은 고딕",Font.PLAIN,14));
		join.setBackground(Color.GRAY);
		join.setForeground(Color.white);
		join.addActionListener(this);

		contentPane.add(login);
		contentPane.add(join);

	}//compose()


	public static void main(String[] args) {

		new Main();

	}//main()


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj == login) {
			boolean result = check();
			
			if(result) {
				login();
			}
			//2.관리자화면 띄우기
			//3.회원화면 띄우기
		}

		if(obj == join) {
			setVisible(false);
			new FJoin("토마토 플레이스 - 회원가입");
			//3.회원가입 화면 띄우기
		}
	}//actionPerformed


	private boolean check() {
		String id = txtId.getText();
		String pw = String.valueOf(txtPw.getPassword());
		
		if(id.equals("")) {
			System.out.println("아이디를 입력해주세요.");
			JOptionPane.showMessageDialog(this, "아이디를 입력해주세요.", "로그인 오류", JOptionPane.WARNING_MESSAGE);
			txtId.setText("");
			txtId.requestFocus();
			return false;
		}
		if(pw.equals("")) {
			System.out.println("아이디를 입력하세요.");
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.", "로그인 오류", JOptionPane.WARNING_MESSAGE);
			txtId.setText("");
			txtPw.setText("");
			txtId.requestFocus();
			return false;
		}
		return true;
	}


	private void login() {
		//0.아이디와 비번이 테이블에 저장된 것과 일치하는지 대조하기.
		//아이디를 검색해서 결과가 나오면 로그인, 아니면 입력한 아이디가 존재하지 않습니다.
		MembersBean mb = null;
		MembersDao dao = new MembersDao();
		try {
			String id = txtId.getText();
			String pw = String.valueOf(txtPw.getPassword());
			System.out.println("텍스트필드: "+id+" "+pw); //테스트코드
			
			mb = dao.getMemberById(id);
			System.out.println("mb : "+mb.getId()+" "+mb.getPw());//tc

			if(id.equals("yhj2024")){
				if(pw.equals(mb.getPw())) {
					//System.out.println("관리자님 환영합니다.");
					new FManager1();
					dispose();
					//dispose()
					//관리자프레임 생성
					
				}else {
					//System.out.println("비밀번호가 일치하지 않습니다.");
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "로그인 오류", JOptionPane.WARNING_MESSAGE);
					txtId.setText("");
					txtPw.setText("");
					txtId.requestFocus();
				}
			}else if(id.equals(mb.getId())){
				if(pw.equals(mb.getPw())) {
					System.out.println(mb.getName()+"님 환영합니다.");
					new FMember(id);
					dispose();
					//회원정보 가지고 회원프레임 생성
				}else {
					System.out.println("비밀번호가 일치하지 않습니다.");
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "로그인 오류", JOptionPane.WARNING_MESSAGE);
					txtId.setText("");
					txtPw.setText("");
					txtId.requestFocus();
				}
			}
		}catch(NullPointerException e) {
			//System.out.println("입력한 아이디가 존재하지 않습니다.");
			JOptionPane.showMessageDialog(this, "입력한 아이디가 존재하지 않습니다.", "로그인 오류", JOptionPane.WARNING_MESSAGE);
			txtId.setText("");
			txtPw.setText("");
			txtId.requestFocus();
		}

	}//login
	
	
}
