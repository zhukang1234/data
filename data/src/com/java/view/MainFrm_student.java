package com.java.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

//学生登录端口界面
public class MainFrm_student extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//私有成员变量
	private JMenu jMenu1;
	private JMenu jMenu2;
	private JMenu jMenu3;
	private JMenu jMenu4;
	private JMenuBar jMenuBar1;
	private JMenuItem jMenuItem6;
	private JMenuItem jMenuItem7;
	private JMenuItem jmiExit;
	private JMenuItem jmiPasswordModify;
	private JMenuItem jmiSelectCourse;
	private JMenuItem jmiSelectedView;
	private JMenuItem jmiSelfInfo;
	private JDesktopPane table;

	//构造方法
	public MainFrm_student() {
		initComponents();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	//添加组件方法
	private void initComponents() {
		//创建组件
		table = new JDesktopPane();
		jMenuBar1 = new JMenuBar();
		jMenu1 = new JMenu();
		jMenu3 = new JMenu();
		jmiSelectCourse = new JMenuItem();
		jmiSelectedView = new JMenuItem();
		jMenu4 = new JMenu();
		jmiPasswordModify = new JMenuItem();
		jmiSelfInfo = new JMenuItem();
		jmiExit = new JMenuItem();
		jMenu2 = new JMenu();
		jMenuItem6 = new JMenuItem();
		jMenuItem7 = new JMenuItem();
		
		//设置默认关闭方式
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//设置窗口标题
		setTitle("学生信息管理系统主界面_学生端");
		//为菜单1添加图片
		jMenu1.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/base.png")));
		//为菜单1添加文字  基本操作
		jMenu1.setText("基本操作");

		jMenu3.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/course.png")));
		jMenu3.setText("选课");
		jmiSelectCourse.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/course.png"))); 
		jmiSelectCourse.setText("选择未选课程");
		//为菜单项“选择未选课程”注册监听器
		jmiSelectCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jmiSelectCourseActionPerformed(evt);
			}
		});
		//将菜单项添加到菜单中
		jMenu3.add(jmiSelectCourse);

		jmiSelectedView.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/course.png"))); 
		jmiSelectedView.setText("查看已选课程");
		jmiSelectedView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jmiSelectedViewActionPerformed(evt);
			}
		});
		
		jMenu3.add(jmiSelectedView);
		jMenu1.add(jMenu3);
		jMenu4.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/userName.png")));
		jMenu4.setText("个人信息");
		jmiPasswordModify.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/password.png"))); 
		jmiPasswordModify.setText("修改密码");
		jmiPasswordModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jmiPasswordModifyActionPerformed(evt);
					}
				});
		jMenu4.add(jmiPasswordModify);
		jmiSelfInfo.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/student.png")));
		jmiSelfInfo.setText("学籍信息");
		jmiSelfInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jmiSelfInfoActionPerformed(evt);
			}
		});
		jMenu4.add(jmiSelfInfo);
		jMenu1.add(jMenu4);
		jmiExit.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/exit.png"))); 
		jmiExit.setText("退出系统");
		jmiExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jmiExitActionPerformed(evt);
			}
		});
		jMenu1.add(jmiExit);
		jMenuBar1.add(jMenu1);
		jMenu2.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/help.png"))); 
		jMenu2.setText("帮助");
		jMenuItem6
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/help.png")));
		jMenuItem6.setText("操作指南");
		jMenu2.add(jMenuItem6);

		jMenuItem7.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/phone.png")));
		jMenuItem7.setText("联系管理员");
		jMenu2.add(jMenuItem7);
		jMenuBar1.add(jMenu2);
		setJMenuBar(jMenuBar1);
		//创建GroupLayout布局管理器
		GroupLayout layout = new GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		//设置沿水平轴确定组件的位置和大小
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(table,
				GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE));
		//设置沿垂直轴确定组件的位置和大小
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(table,
				GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE));

		pack();//依据放置的组件设定窗口的大小，使窗口恰好容纳所有的组件
	}
	//菜单项“学籍信息”触发事件
	private void jmiSelfInfoActionPerformed(ActionEvent evt) {
		SelfInfoInterFrm selfInfoInterFrm = new SelfInfoInterFrm();
		selfInfoInterFrm.setVisible(true);
		this.table.add(selfInfoInterFrm);
	}
	//菜单项“修改密码”触发事件
	private void jmiPasswordModifyActionPerformed(ActionEvent evt) {
		PasswordModifyInterFrm passwordModifyInterFrm = new PasswordModifyInterFrm();
		passwordModifyInterFrm.setVisible(true);
		this.table.add(passwordModifyInterFrm);
	}
	//菜单项“查看已选课程”触发事件
	private void jmiSelectedViewActionPerformed(ActionEvent evt) {
		SelectedViewInterFrm selectedViewInterFrm = new SelectedViewInterFrm();
		selectedViewInterFrm.setVisible(true);
		this.table.add(selectedViewInterFrm);
	}
	//菜单项“选择未选课程”触发事件
	private void jmiSelectCourseActionPerformed(ActionEvent evt) {
		SelectCourseInterFrm selectCourseInterFrm = new SelectCourseInterFrm();
		selectCourseInterFrm.setVisible(true);
		this.table.add(selectCourseInterFrm);
	}
	//菜单项“退出系统”触发事件
	private void jmiExitActionPerformed(ActionEvent evt) {
		int result = JOptionPane.showConfirmDialog(this, "是否退出系统?");
		if (result == 0) {
			this.dispose();
		}
	}
	//主函数
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrm_student().setVisible(true);
			}
		});
	}
	


}