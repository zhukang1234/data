
package com.java.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.java.dao.StudentDao;
import com.java.model.Student;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

//密码修改
public class PasswordModifyInterFrm extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//私有成员
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton jb_modify;
	private JButton jb_reset;
	private JPasswordField newPasswordTxt;
	private JPasswordField oldPasswordTxt;
	private JPasswordField passwordConfirmTxt;
	//创建连接
	DbUtil dbUtil = new DbUtil();
	StudentDao studentDao = new StudentDao();

	//构造方法
	public PasswordModifyInterFrm() {
		initComponents();
		this.setLocation(200, 50);
	}
	//放置组件
	private void initComponents() {

		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jb_modify = new JButton();
		jb_reset = new JButton();
		oldPasswordTxt = new JPasswordField();
		newPasswordTxt = new JPasswordField();
		passwordConfirmTxt = new JPasswordField();
		//设置窗口性质
		setClosable(true);//设置为可关闭的
		setIconifiable(true);//设置为可缩小成一个图标
		setTitle("密码修改");//设置标题

		jLabel1.setText("原密码:");

		jLabel2.setText("新密码:");

		jLabel3.setText("再输入一遍新密码:");
		//给jb_modify按钮添加图片和文本及监听器
		jb_modify.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/modify.png")));
		jb_modify.setText("修改");
		jb_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_modifyActionPerformed(evt);
			}
		});
		//给jb_reset按钮添加图片和文本及监听器
		jb_reset.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/reset.png")));
		jb_reset.setText("重置");
		jb_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_resetActionPerformed(evt);
			}
		});
		//创建GroupLayout布局管理器
		GroupLayout layout = new GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		//设置沿水平轴确定组件的位置和大小
		layout.setHorizontalGroup(layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(88, 88, 88)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(jLabel3)
														.addComponent(jLabel2)
														.addComponent(jLabel1)
														.addComponent(jb_modify))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
														.addComponent(
																newPasswordTxt,
																GroupLayout.PREFERRED_SIZE,
																152,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																passwordConfirmTxt,
																GroupLayout.PREFERRED_SIZE,
																152,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																oldPasswordTxt,
																GroupLayout.PREFERRED_SIZE,
																152,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jb_reset))
										.addContainerGap(105, Short.MAX_VALUE)));
		//设置沿垂直轴确定组件的位置和大小
		layout.setVerticalGroup(layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(28, 28, 28)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																oldPasswordTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(28, 28, 28)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																newPasswordTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																passwordConfirmTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
															    GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												41, Short.MAX_VALUE)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(jb_modify)
														.addComponent(jb_reset))
										.addGap(25, 25, 25)));

		pack();//依据放置的组件设定窗口的大小，使窗口恰好容纳所有的组件
	}
	//重写监听器的方法
	private void jb_modifyActionPerformed(ActionEvent evt) {
		String oldPassword = new String(this.oldPasswordTxt.getPassword());
		String newPassword = new String(this.newPasswordTxt.getPassword());
		String passwordConfirm = new String(this.passwordConfirmTxt
				.getPassword());
		if (StringUtil.isEmpty(oldPassword)) {
			JOptionPane.showMessageDialog(this, "原密码不能为空!");
			return;
		}
		if (StringUtil.isEmpty(newPassword)) {
			JOptionPane.showMessageDialog(this, "新密码不能为空!");
			return;
		}
		if (StringUtil.isEmpty(passwordConfirm)) {
			JOptionPane.showMessageDialog(this, "请再输入一遍新密码!");
			return;
		}
		String rightOldPassword = LogOnFrm.currentStudent.getSpassword();
		if (!oldPassword.equals(rightOldPassword)) {
			JOptionPane.showMessageDialog(this, "旧密码错误,请重新输入!");
			return;
		}
		if (!newPassword.equals(passwordConfirm)) {
			JOptionPane.showMessageDialog(this, "新密码不一致,请重新输入!");
			return;
		}
		Connection con = null;
		int currentSno = LogOnFrm.currentStudent.getSno();
		Student student = new Student(currentSno, newPassword);

		try {
			con = dbUtil.getCon();
			int modifyNum = studentDao.PasswordModify(con, student);
			if (modifyNum == 1) {
				JOptionPane.showMessageDialog(this, "修改成功!");
				this.resetValue();

			} else {
				JOptionPane.showMessageDialog(this, "修改失败!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "修改失败!");
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	 //重置方法
	private void resetValue() {
		this.oldPasswordTxt.setText("");
		this.newPasswordTxt.setText("");
		this.passwordConfirmTxt.setText("");
	}
	//点击重置按钮调用的方法
	private void jb_resetActionPerformed(ActionEvent evt) {
		this.oldPasswordTxt.setText("");
		this.newPasswordTxt.setText("");
		this.passwordConfirmTxt.setText("");
	}





}