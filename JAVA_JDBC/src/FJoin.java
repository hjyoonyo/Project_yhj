import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FJoin extends JFrame implements ActionListener{
	JTextField txtId = new JTextField(20);
	JPasswordField txtPw = new JPasswordField(20);
	JTextField txtName = new JTextField(20);
	JTextField txtBirth = new JTextField(20);
	JTextField txtPnum = new JTextField(20);
	JTextField txtNick = new JTextField(20);
	JButton check = new JButton("검사");
	JButton join = new JButton("가입");
	JButton cancle = new JButton("취소");
	JLabel result = null;
	JLabel result2 = null;
	Container contentPane=null;

	FJoin(String title){
		super(title);

		setSize(400,600);
		setLocationRelativeTo(null);
		setResizable(false);

		compose();


		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}//생성자



	private void compose() {
		contentPane = getContentPane();
		contentPane.setLayout(null);

		//라벨
		JLabel lbid = new JLabel("ID :");
		JLabel lbpw = new JLabel("PW :");
		JLabel lbname = new JLabel("이름 :");
		JLabel lbbirth = new JLabel("생년월일 :");
		JLabel lbpnum = new JLabel("전화번호 :");
		JLabel lbnick = new JLabel("닉네임 :");
		
		JLabel lbpwD = new JLabel("4~12자리로 설정하세요.");
		JLabel lbnameD = new JLabel("1~10 글자로 설정하세요.");
		JLabel lbbirthD = new JLabel("1999-9-9의 형태로 입력하세요");
		JLabel lbpnumD = new JLabel("010-0000-0000의 형태로 입력하세요.");
		JLabel lbnickD = new JLabel("1~10글자로 설정하세요.");


		int i = 1;
		int x = 40;
		int y = 60;
		int width = 90;
		int height = 20;
		int fsize = 18;
		lbid.setBounds(x,i*y,width,height);
		lbid.setFont(new Font("Default",Font.PLAIN,fsize));

		lbpw.setBounds(x,++i*y,width,height);
		lbpw.setFont(new Font("Default",Font.PLAIN,fsize));

		lbname.setBounds(x,++i*y,width,height);
		lbname.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		lbbirth.setBounds(x,++i*y,width,height);
		lbbirth.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		lbpnum.setBounds(x,++i*y,width,height);
		lbpnum.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		lbnick.setBounds(x,++i*y,width,height);
		lbnick.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		x=135;
		i=1;
		width = 220;
		height = 25;
		lbpwD.setBounds(x,++i*y+30,width,height);
		lbnameD.setBounds(x,++i*y+30,width,height);
		lbbirthD.setBounds(x,++i*y+30,width,height);
		lbpnumD.setBounds(x,++i*y+30,width,height);
		lbnickD.setBounds(x,++i*y+30,width,height);
		
		contentPane.add(lbid);
		contentPane.add(lbpw);
		contentPane.add(lbname);
		contentPane.add(lbbirth);
		contentPane.add(lbpnum);
		contentPane.add(lbnick);
		contentPane.add(lbpwD);
		contentPane.add(lbnameD);
		contentPane.add(lbbirthD);
		contentPane.add(lbpnumD);
		contentPane.add(lbnickD);
		
		//텍스트필드
		i = 1;
		x = 130;
		width = 200;
		height = 25;
		fsize = 17;
		txtId.setBounds(x,i*y,width,height); //특수문자 입력하면 경고창 뜨게 설정
		txtId.setFont(new Font("Default",Font.PLAIN,fsize));
		txtId.addKeyListener(new KeyHandler());

		txtPw.setBounds(x,++i*y,width,height);
		txtPw.setFont(new Font("Default",Font.PLAIN,fsize));

		txtName.setBounds(x,++i*y,width,height);
		txtName.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		txtBirth.setBounds(x,++i*y,width,height);
		txtBirth.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		txtPnum.setBounds(x,++i*y,width,height);
		txtPnum.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		txtNick.setBounds(x,++i*y,width,height);
		txtNick.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		contentPane.add(txtId);
		contentPane.add(txtPw);
		contentPane.add(txtName);
		contentPane.add(txtBirth);
		contentPane.add(txtPnum);
		contentPane.add(txtNick);

		//중복 확인 버튼
		check.setBounds(x,90,60,25);
		check.setBackground(Color.LIGHT_GRAY);
		check.addActionListener(this);

		result = new JLabel("중복된 ID입니다.");
		result.setBounds(200, 90, 200, 25);
		result.setVisible(false);

		result2 = new JLabel("사용 가능한 ID입니다.");
		result2.setBounds(200, 90, 200, 25);
		result2.setVisible(false);

		contentPane.add(result);
		contentPane.add(result2);
		contentPane.add(check);

		//가입버튼
		join.setBounds(80,450,100,30);
		join.addActionListener(this);
		contentPane.add(join);

		//취소버튼
		cancle.setBounds(200,450,100,30);
		cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Main();
			}
		});
		contentPane.add(cancle);
	}//compose

	public static void main(String[] args) {
		new FJoin("회원가입_테스트");
	}//main



	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		if(obj == check) {
			if(txtId.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "ID를 입력하세요.","에러발생",JOptionPane.INFORMATION_MESSAGE);
				txtId.requestFocus(); //오류발생한 곳에 커서를 깜박거리게 함
				result.setVisible(false);
				result2.setVisible(false);
				return; // 밑으로 내려가지 못하게 함 => 문제가 발생하면 false 리턴
			}else if(txtId.getText().length()>12 || txtId.getText().length()<4) {
				JOptionPane.showMessageDialog(this, "ID는 4~12자리로 입력하세요.","에러발생",JOptionPane.INFORMATION_MESSAGE);
				txtId.setText("");
				txtId.requestFocus();
				result.setVisible(false);
				result2.setVisible(false);
				return;
			}

			MembersBean mb = null;
			MembersDao dao = new MembersDao();
			try {
				String id = txtId.getText();

				mb = dao.getMemberById(id);

				if(id.equals(mb.getId())){
					txtId.setText("");
					txtId.requestFocus();
					result.setVisible(true);
					result2.setVisible(false);
				}
			}catch(NullPointerException ee) {
				result.setVisible(false);
				result2.setVisible(true);
			}
		}//중복검사
		if(obj == join) {
			boolean result = checkData();
			if(result) {
				MembersDao dao = new MembersDao();
				MembersBean mb = new MembersBean(txtId.getText(),String.valueOf(txtPw.getPassword()),txtName.getText(),
						txtBirth.getText(),txtPnum.getText(),txtNick.getText());

				int cnt = dao.insertMembers(mb);
				if(cnt == 1) {
					JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.",txtName.getText()+"님 환영합니다.",JOptionPane.PLAIN_MESSAGE);
					dispose();
					new Main();
				}else {
					JOptionPane.showMessageDialog(this, "오류가 발생했습니다.",txtName.getText()+"회원가입 오류",JOptionPane.PLAIN_MESSAGE);
				}
			}
		}//회원가입

	}//actionPerformed

	private boolean checkData() {
		if(txtId.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "ID를 입력하세요.","에러발생",JOptionPane.INFORMATION_MESSAGE);
			txtId.requestFocus(); //오류발생한 곳에 커서를 깜박거리게 함
			return false; // 밑으로 내려가지 못하게 함 => 문제가 발생하면 false 리턴
		}else if(txtId.getText().length()>12 || txtId.getText().length()<4) {
			JOptionPane.showMessageDialog(this, "ID는 4~12자리로 입력하세요.","에러발생",JOptionPane.INFORMATION_MESSAGE);
			txtId.setText("");
			txtId.requestFocus();
			result.setVisible(false);
			result2.setVisible(false);
			return false;
		}else {
			MembersBean mb = null;
			MembersDao dao = new MembersDao();
			try {
				String id = txtId.getText();

				mb = dao.getMemberById(id);

				if(id.equals(mb.getId())){
					JOptionPane.showMessageDialog(this, "중복된 ID입니다.","에러발생",JOptionPane.INFORMATION_MESSAGE);
					txtId.setText("");
					txtId.requestFocus();
					result.setVisible(false);
					result2.setVisible(false);
					return false;
				}
			}catch(NullPointerException ee) {
				result.setVisible(false);
				result2.setVisible(false);
			}
		}

		if(txtPw.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.","에러발생",JOptionPane.ERROR_MESSAGE);
			txtPw.setText("");
			txtPw.requestFocus();
			return false;
		}else if(String.valueOf(txtPw.getPassword()).length()>12 || String.valueOf(txtPw.getPassword()).length()<4) {
			JOptionPane.showMessageDialog(this, "비밀번호는 4~12자리로 입력하세요.","에러발생",JOptionPane.INFORMATION_MESSAGE);
			txtPw.setText("");
			txtPw.requestFocus();
			return false;
		}

		if(txtName.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "이름을 입력하세요.","에러발생",JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane.YES_NO_CANCEL_OPTION
			txtName.setText("");
			txtName.requestFocus();
			return false;
		}else if(txtName.getText().length() >10) {
			JOptionPane.showMessageDialog(this, "이름은 1~10자리로 입력하세요.","에러발생",JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane.YES_NO_CANCEL_OPTION
			txtName.setText("");
			txtName.requestFocus();
			return false;
		}

		if(txtBirth.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "생년월일을 입력하세요.","에러발생",JOptionPane.QUESTION_MESSAGE);
			txtBirth.setText("");
			txtBirth.requestFocus();
			return false;
		}else {
			try { //날짜형식에 맞게 입력됐는지 확인
				Date.valueOf(txtBirth.getText());			
			}catch(IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this, "생년월일을 날짜 형식에 맞게 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
				txtBirth.setText("");
				txtBirth.requestFocus();
				return false;
			}
		}

		if(txtPnum.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "전화번호를 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
			txtPnum.setText("");
			txtPnum.requestFocus();
			return false;
		}else if(Pattern.matches("^01[0-9]-[0-9]{4}-[0-9]{4}$", txtPnum.getText())) {
			// ^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$
		}else {
			JOptionPane.showMessageDialog(txtPnum, "010-0000-0000 형식으로 입력하세요","에러발생",JOptionPane.INFORMATION_MESSAGE);
			txtPnum.setText("");
			txtPnum.requestFocus();
			return false;
		}

		if(txtNick.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "닉네임을 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
			txtNick.setText("");
			txtNick.requestFocus();
			return false;
		}else if(txtNick.getText().length() >10) {
			JOptionPane.showMessageDialog(this, "닉네임은 1~10자리로 입력하세요.","에러발생",JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane.YES_NO_CANCEL_OPTION
			txtNick.setText("");
			txtNick.requestFocus();
			return false;
		}

		return true;
	}

	class KeyHandler extends KeyAdapter{
		public void keyReleased(KeyEvent e) { // 키가 눌러졌다가 띄어졌을 때
			//System.out.println("keyReleased");

			Object obj = e.getSource();
			if(obj == txtId) {
				if(Pattern.matches("^[0-9a-zA-Z]*$", txtId.getText())) {

				}else {
					JOptionPane.showMessageDialog(txtId, "영문과 숫자만 입력 가능합니다.","에러발생",JOptionPane.INFORMATION_MESSAGE);
					result.setVisible(false);
					result2.setVisible(false);
					txtId.setText("");
					txtId.requestFocus();
				}
			}
		}//keyReleased()
	}//KeyHandler

}
