import java.awt.Choice;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FInsertPlace extends JFrame implements ActionListener, ItemListener{
	JTextField txtPname = new JTextField(20);
	Choice choiceCode = new Choice();
	Choice choiceLocation = new Choice();
	JTextField txtAddr = new JTextField(20);
	JTextField txtLink = new JTextField(20);
	JTextField txtNo= new JTextField(20);
	//	JButton check = new JButton("검사");
	JButton insert = new JButton("등록");
	JButton cancle = new JButton("취소");
	JLabel result = null;
	JLabel result2 = null;
	Container contentPane=null;

	FInsertPlace(String title){
		super(title);

		setSize(400,600);
		setLocationRelativeTo(null);
		setResizable(false);

		compose();


		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}//생성자



	private void compose() {
		contentPane = getContentPane();
		contentPane.setLayout(null);
		//		this.num = num;
		//		this.pname = pname;
		//		this.code = code;
		//		this.date = date;
		//		this.location = location;
		//		this.addr = addr;
		//		this.link = link;
		//		this.no = no;
		//		this.likes = likes;
		//라벨
		JLabel lbpname = new JLabel("가게명 :");
		JLabel lbcode = new JLabel("분류 :");
		JLabel lblocation = new JLabel("지역명 :");
		JLabel lbaddr = new JLabel("주소 :");
		JLabel lblink = new JLabel("링크 :");
		JLabel lbno = new JLabel("NO :");

		//		JLabel lbpwD = new JLabel("4~12자리로 설정하세요.");
		//		JLabel lbnameD = new JLabel("1~10 글자로 설정하세요.");
		//		JLabel lbbirthD = new JLabel("1999-9-9의 형태로 입력하세요");
		//		JLabel lbpnumD = new JLabel("010-0000-0000의 형태로 입력하세요.");
		//		JLabel lbnickD = new JLabel("1~10글자로 설정하세요.");


		int i = 1;
		int x = 40;
		int y = 60;
		int width = 90;
		int height = 20;
		int fsize = 18;
		lbpname.setBounds(x,i*y,width,height);
		lbpname.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		lbcode.setBounds(x,++i*y,width,height);
		lbcode.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		lblocation.setBounds(x,++i*y,width,height);
		lblocation.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		lbaddr.setBounds(x,++i*y,width,height);
		lbaddr.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		lblink.setBounds(x,++i*y,width,height);
		lblink.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		lbno.setBounds(x,++i*y,width,height);
		lbno.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		//		x=135;
		//		i=1;
		//		width = 220;
		//		height = 25;
		//		lbpwD.setBounds(x,++i*y+30,width,height);
		//		lbnameD.setBounds(x,++i*y+30,width,height);
		//		lbbirthD.setBounds(x,++i*y+30,width,height);
		//		lbpnumD.setBounds(x,++i*y+30,width,height);
		//		lbnickD.setBounds(x,++i*y+30,width,height);

		contentPane.add(lbpname);
		contentPane.add(lbcode);
		contentPane.add(lblocation);
		contentPane.add(lbaddr);
		contentPane.add(lblink);
		contentPane.add(lbno);
		//		contentPane.add(lbpwD);
		//		contentPane.add(lbnameD);
		//		contentPane.add(lbbirthD);
		//		contentPane.add(lbpnumD);
		//		contentPane.add(lbnickD);

		//텍스트필드
		i = 1;
		x = 130;
		width = 200;
		height = 25;
		fsize = 17;
		txtPname.setBounds(x,i*y,width,height); //특수문자 입력하면 경고창 뜨게 설정
		txtPname.setFont(new Font("Default",Font.PLAIN,fsize));
		txtPname.addKeyListener(new KeyHandler());

		choiceCode.setBounds(x,++i*y,width,height);
		choiceCode.setFont(new Font("Default",Font.PLAIN,fsize));
		choiceCode.add("1. 한식");
		choiceCode.add("2. 중식");
		choiceCode.add("3. 일식");
		choiceCode.add("4. 양식");
		choiceCode.add("5. 기타");
		choiceCode.add("6. 카페");
		choiceCode.addItemListener(this);

		choiceLocation.setBounds(x,++i*y,width,height);
		choiceLocation.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));
		choiceLocation.add("동교동");
		choiceLocation.add("서교동");
		choiceLocation.add("연남동");
		choiceLocation.addItemListener(this);

		txtAddr.setBounds(x,++i*y,width,height);
		txtAddr.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		txtLink.setBounds(x,++i*y,width,height);
		txtLink.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));

		txtNo.setBounds(x,++i*y,width,height);
		txtNo.setFont(new Font("맑은 고딕",Font.PLAIN,fsize));


		contentPane.add(txtPname);
		contentPane.add(choiceCode);
		contentPane.add(choiceLocation);
		contentPane.add(txtAddr);
		contentPane.add(txtLink);
		contentPane.add(txtNo);

		//중복 확인 버튼
		//		check.setBounds(x,90,60,25);
		//		check.setBackground(Color.LIGHT_GRAY);
		//		check.addActionListener(this);
		//
		//		result = new JLabel("중복된 ID입니다.");
		//		result.setBounds(200, 90, 200, 25);
		//		result.setVisible(false);
		//
		//		result2 = new JLabel("사용 가능한 ID입니다.");
		//		result2.setBounds(200, 90, 200, 25);
		//		result2.setVisible(false);
		//
		//		contentPane.add(result);
		//		contentPane.add(result2);
		//		contentPane.add(check);

		//등록버튼
		insert.setBounds(80,450,100,30);
		insert.addActionListener(this);
		contentPane.add(insert);

		//취소버튼
		cancle.setBounds(200,450,100,30);
		cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(cancle);
	}//compose

	public static void main(String[] args) {
		new FInsertPlace("맛집등록_테스트");
	}//main



	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		//		if(obj == check) {
		//			if(txtId.getText().length() == 0) {
		//				JOptionPane.showMessageDialog(this, "ID를 입력하세요.","에러발생",JOptionPane.INFORMATION_MESSAGE);
		//				txtId.requestFocus(); //오류발생한 곳에 커서를 깜박거리게 함
		//				result.setVisible(false);
		//				result2.setVisible(false);
		//				return; // 밑으로 내려가지 못하게 함 => 문제가 발생하면 false 리턴
		//			}else if(txtId.getText().length()>12 || txtId.getText().length()<4) {
		//				JOptionPane.showMessageDialog(this, "ID는 4~12자리로 입력하세요.","에러발생",JOptionPane.INFORMATION_MESSAGE);
		//				txtId.setText("");
		//				txtId.requestFocus();
		//				result.setVisible(false);
		//				result2.setVisible(false);
		//				return;
		//			}
		//
		//			MembersBean mb = null;
		//			MembersDao dao = new MembersDao();
		//			try {
		//				String id = txtId.getText();
		//
		//				mb = dao.getMemberById(id);
		//
		//				if(id.equals(mb.getId())){
		//					txtId.setText("");
		//					txtId.requestFocus();
		//					result.setVisible(true);
		//					result2.setVisible(false);
		//				}
		//			}catch(NullPointerException ee) {
		//				result.setVisible(false);
		//				result2.setVisible(true);
		//			}
		//		}//중복검사

		//		JTextField txtPname = new JTextField(20);
		//		JTextField txtCode = new JPasswordField(20);
		//		JTextField txtLocation = new JTextField(20);
		//		JTextField txtAddr = new JTextField(20);
		//		JTextField txtLink = new JTextField(20);
		if(obj == insert) {
			boolean result = checkData();
			if(result) {
				PlaceDao dao = new PlaceDao();

				PlaceBean mb = new PlaceBean();
				mb.setPname(txtPname.getText());
				mb.setCode(choiceCode.getSelectedItem());
				mb.setLocation(choiceLocation.getSelectedItem());
				mb.setAddr(txtAddr.getText());
				mb.setLink(txtLink.getText());
				mb.setNo(Integer.valueOf(txtNo.getText()));

				int cnt = dao.insertPlace(mb);
				if(cnt == 1) {
					JOptionPane.showMessageDialog(this, "등록이 완료되었습니다.","등록 성공.",JOptionPane.PLAIN_MESSAGE);
					dispose();
				}else {
					JOptionPane.showMessageDialog(this, "오류가 발생했습니다.","회원가입 오류",JOptionPane.PLAIN_MESSAGE);
				}
			}
		}//회원가입

	}//actionPerformed

	private boolean checkData() {
		if(txtPname.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "가게명을 입력하세요.","에러발생",JOptionPane.INFORMATION_MESSAGE);
			txtPname.requestFocus(); //오류발생한 곳에 커서를 깜박거리게 함
			return false; // 밑으로 내려가지 못하게 함 => 문제가 발생하면 false 리턴
		}

		//		if(choiceCode.getSelectedItem().length() == 0) {
		//			JOptionPane.showMessageDialog(this, "분류를 입력하세요.","에러발생",JOptionPane.ERROR_MESSAGE);
		//			choiceCode.setText("");
		//			choiceCode.requestFocus();
		//			return false;
		//		}

		//		if(choiceLocation.getSelectedItem().length() == 0) {
		//			JOptionPane.showMessageDialog(this, "지역명을 입력하세요.","에러발생",JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane.YES_NO_CANCEL_OPTION
		//			choiceLocation.removeAll();
		//			choiceLocation.requestFocus();
		//			return false;
		//		}

		if(txtAddr.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "주소를 입력하세요.","에러발생",JOptionPane.QUESTION_MESSAGE);
			txtAddr.setText("");
			txtAddr.requestFocus();
			return false;
		}

		if(txtLink.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "링크를 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
			txtLink.setText("");
			txtLink.requestFocus();
			return false;
		}

		if(txtNo.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "NO를 입력하세요.","에러발생",JOptionPane.WARNING_MESSAGE);
			txtNo.setText("");
			txtNo.requestFocus();
			return false;
		}else {
			try {
				Integer.valueOf(txtNo.getText());
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "숫자만 입력하세요.","에러발생",JOptionPane.INFORMATION_MESSAGE);
				txtNo.setText("");
				txtNo.requestFocus();
				return false;
			}
			MembersDao dao = new MembersDao();
			ArrayList<MembersBean> lists = new ArrayList<MembersBean>();
			try {
				int no = Integer.valueOf(txtNo.getText());

				lists = dao.getAllMembers();

				for(MembersBean mb : lists) {
					if(no == mb.getNo()){
						return true;
					}
				}

				JOptionPane.showMessageDialog(this, "없는 NO입니다.","에러발생",JOptionPane.INFORMATION_MESSAGE);
				txtNo.setText("");
				txtNo.requestFocus();

				return false;

			}catch(NullPointerException ee) {
				JOptionPane.showMessageDialog(this, "숫자만 입력하세요2.","에러발생",JOptionPane.INFORMATION_MESSAGE);
				txtNo.setText("");
				txtNo.requestFocus();
				return false;
			}
		}
	}

	class KeyHandler extends KeyAdapter{
		public void keyReleased(KeyEvent e) { // 키가 눌러졌다가 띄어졌을 때
			//System.out.println("keyReleased");

			//			Object obj = e.getSource();
			//			if(obj == txtPname) {
			//				if(Pattern.matches("^[0-9a-zA-Z]*$", txtPname.getText())) {
			//
			//				}else {
			//					JOptionPane.showMessageDialog(txtPname, "영문과 숫자만 입력 가능합니다.","에러발생",JOptionPane.INFORMATION_MESSAGE);
			//					result.setVisible(false);
			//					result2.setVisible(false);
			//					txtPname.setText("");
			//					txtPname.requestFocus();
			//				}
			//			}
		}//keyReleased()
	}//KeyHandler

	@Override
	public void itemStateChanged(ItemEvent e) {

	}

}
