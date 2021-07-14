
package com.java.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.FontUIResource;

import com.java.dao.LogOnDao;
import com.java.model.Admin;
import com.java.model.Student;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

//登录界面
public class LogOnFrm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//私有成员变量
	private ButtonGroup buttonGroup1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton jb_logOn;
	private JButton jb_reset;
	private JRadioButton jrb_admin;
	private JRadioButton jrb_student;
	private JPasswordField passwordTxt;
	private JFormattedTextField userNameTxt;
	
	DbUtil dbUtil = new DbUtil();
	LogOnDao logOnDao = new LogOnDao();
	public static Student currentStudent ;

	//构造方法
	public LogOnFrm() {
		//改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		@SuppressWarnings("rawtypes")
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, font);
			}
		}
		//调用放置组件方法
		initComponents();
		//设置在windows系统中默认居于屏幕正中
		this.setLocationRelativeTo(null);
		this.jrb_student.setSelected(true);
	}
	//添加组件方法
	private void initComponents() {

		//创建组件
		buttonGroup1 = new ButtonGroup();
		jLabel1 = new JLabel();//“学生信息管理系统 ”大字 面板
		jLabel2 = new JLabel();//账户
		userNameTxt = new JFormattedTextField();//用户账号输入框
		jLabel3 = new JLabel();//密码
		passwordTxt = new JPasswordField();//用户密码输入框
		jrb_student = new JRadioButton();//学生复选按钮
		jrb_admin = new JRadioButton();//管理员复选按钮
		jb_logOn = new JButton();//登录按钮
		jb_reset = new JButton();//重置按钮
		
		//设置在windows系统中默认居于屏幕正中
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("用户登录");
		setResizable(false);
		//设置字体
		jLabel1.setFont(new Font("隶书", 1, 24));
		//jLabel1设置图片
		jLabel1
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/sys.png")));
		//jLabel1设置文字
		jLabel1.setText("学生信息管理系统");
		jLabel2
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/userName.png")));
		jLabel2.setText("账户");

		jLabel3
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/password.png"))); 
		jLabel3.setText("密码");
		//buttonGroup1添加学生按钮
		buttonGroup1.add(jrb_student);
		jrb_student.setText("学生");
		jrb_student
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/student.png")));
		//buttonGroup1添加管理员按钮
		buttonGroup1.add(jrb_admin);
		jrb_admin.setText("管理员");
		jrb_admin
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/admin.png")));

		jb_logOn
				.setIcon(new  ImageIcon(this.getClass().getResource(
						"/com/java/view/image/login.png"))); 
		jb_logOn.setText("登录 ");
		//用内部类的方式为登录按钮注册监听器
		jb_logOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_logOnActionPerformed(evt);
			}
			
		});

		jb_reset
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/reset.png"))); 
		jb_reset.setText("重置 ");
		//用内部类的方式为重置按钮注册监听器
		jb_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_resetActionPerformed(evt);
			}
		});
		//创建GroupLayout布局管理器
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		//设置沿水平轴确定组件的位置和大小
		layout.setHorizontalGroup(layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(106, 106, 106)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
														.addComponent(jLabel1)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addGroup(
																								GroupLayout.Alignment.TRAILING,
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel3)
																										.addGap(
																												33,
																												33,
																												33))
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel2)
																										.addGap(
																												33,
																												33,
																												33)))
																		.addGap(
																				6,
																				6,
																				6)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								GroupLayout.Alignment.TRAILING,
																								false)
																						.addComponent(
																								passwordTxt,
																								0,
																								0,
																								Short.MAX_VALUE)
																						.addComponent(
																								userNameTxt,
																								GroupLayout.DEFAULT_SIZE,
																								135,
																								Short.MAX_VALUE)
																						.addGroup(
																								GroupLayout.Alignment.LEADING,
																								layout
																										.createSequentialGroup()
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addComponent(
																																jb_logOn)
																														.addComponent(
																																jrb_student))
																										.addGroup(
																												layout
																														.createParallelGroup(
																																GroupLayout.Alignment.LEADING,
																																false)
																														.addGroup(
																																layout
																																		.createSequentialGroup()
																																		.addGap(
																																				29,
																																				29,
																																				29)
																																		.addComponent(
																																				jb_reset))
																														.addGroup(
																																layout
																																		.createSequentialGroup()
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																		.addComponent(
																																				jrb_admin,
																																				GroupLayout.DEFAULT_SIZE,
																																				GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)))))))
										.addContainerGap(143, Short.MAX_VALUE)));
		//设置沿垂直轴确定组件的位置和大小
		layout.setVerticalGroup(layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
							    GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap(77, Short.MAX_VALUE)
										.addComponent(jLabel1)
										.addGap(39, 39, 39)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																userNameTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																passwordTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																jrb_student)
														.addComponent(jrb_admin))
										.addGap(32, 32, 32)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(jb_reset)
														.addComponent(jb_logOn))
										.addGap(58, 58, 58)));

		pack();//依据放置的组件设定窗口的大小，使窗口恰好容纳所有的组件
	}
	//登录行为方法
	private void jb_logOnActionPerformed(ActionEvent evt) {
		//获取文本框中的信息
		String userName = userNameTxt.getText();
		String password = new String(passwordTxt.getPassword());
		//关于文本框中输入为空的各种情况的判断
		if (StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(this, "账号不能为空!");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(this, "密码不能为空!");
			return;
		}
		//创建一个连接
		Connection con=null;
		if (this.jrb_student.isSelected()) {
			Student student = new Student(Integer.parseInt(userName), password);
			try {
				con=dbUtil.getCon();
				currentStudent = logOnDao.login(con,
						student);
				if (currentStudent != null) {
					this.dispose();
					new MainFrm_student().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "用户名或密码错误!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "登录失败!");
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (this.jrb_admin.isSelected()) {
			Admin admin = new Admin(Integer.parseInt(userName), password);
			try {
				con=dbUtil.getCon();
				Admin currentAdmin = logOnDao.login(con, admin);
				if (currentAdmin != null) {
					this.dispose();
					new MainFrm_admin().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "用户名或密码错误!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "登录失败!");
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	//重置行为方法
	private void jb_resetActionPerformed(ActionEvent evt) {
		this.resetValue();
	}
	//重置的清空文本框方法
	private void resetValue() {
		this.userNameTxt.setText("");
		this.passwordTxt.setText("");
		this.jrb_student.setSelected(true);//默认复选框选择学生
	}
	//主函数
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LogOnFrm().setVisible(true);
			}
		});
	}



}