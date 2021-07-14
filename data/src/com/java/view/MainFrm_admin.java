
package com.java.view;

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


public class MainFrm_admin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//私有成员
	private JMenu jMenu1;
	private JMenu jMenu2;
	private JMenu jMenu3;
	private JMenu jMenu4;
	private JMenuBar jMenuBar1;
	private JMenuItem jMenuItem1;
	private JMenuItem jMenuItem2;
	private JMenuItem jmiCourseAdd;
	private JMenuItem jmiCourseManage;
	private JMenuItem jmiCourseView;
	private JMenuItem jmiExit;
	private JMenuItem jmiStudentPassword;
	private JMenuItem jmiStudentView;
	private JDesktopPane table;
	
	//构造方法
	public MainFrm_admin() {
		initComponents();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);//设置为窗口全屏
	}
	//放置组件
	private void initComponents() {
		//创建组件
		table = new JDesktopPane();
		jMenuBar1 = new JMenuBar();
		jMenu1 = new JMenu();
		jMenu3 = new JMenu();
		jmiCourseView = new JMenuItem();
		jmiCourseAdd = new JMenuItem();
		jmiCourseManage = new JMenuItem();
		jMenu4 = new JMenu();
		jmiStudentView = new JMenuItem();
		jmiStudentPassword = new JMenuItem();
		jmiExit = new JMenuItem();
		jMenu2 = new JMenu();
		jMenuItem1 = new JMenuItem();
		jMenuItem2 = new JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);//设置默认关闭方式
		setTitle("学生信息管理系统_管理员端");//设置标题
		//为菜单1添加图片
		jMenu1.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/base.png")));
		//为菜单1添加文字  基本操作
		jMenu1.setText("基本操作");
		jMenu3.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/course.png")));
		jMenu3.setText("选课相关");
		jmiCourseView.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/course.png")));
		jmiCourseView.setText("查看选课情况");
		//为菜单项“查看选课情况”注册监听器
		jmiCourseView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jmiCourseViewActionPerformed(evt);
			}
		});
		//将菜单项“查看选课情况”添加到菜单3“选课相关”中
		jMenu3.add(jmiCourseView);
		jmiCourseAdd.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/course.png"))); 
		jmiCourseAdd.setText("增加课程条目");
		//为菜单项“增加课程条目”注册监听器
		jmiCourseAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jmiCourseAddActionPerformed(evt);
			}
		});
		//将菜单项“增加课程条目”添加到菜单3“选课相关”中
		jMenu3.add(jmiCourseAdd);
		jmiCourseManage.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/course.png"))); 
		jmiCourseManage.setText("修改课程信息");
		//为菜单项“修改课程信息”注册监听器
		jmiCourseManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jmiCourseManageActionPerformed(evt);
			}
		});
		jMenu3.add(jmiCourseManage);
		jMenu1.add(jMenu3);
		
		jMenu4.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/student.png"))); 
		jMenu4.setText("学生相关");
		jmiStudentView.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/student.png")));
		jmiStudentView.setText("查看学生资料");
		//为菜单项“查看学生资料”注册监听器
		jmiStudentView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jmiStudentViewActionPerformed(evt);
			}
		});
		jMenu4.add(jmiStudentView);
		jmiStudentPassword.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/password.png")));
		jmiStudentPassword.setText("管理学生密码");
		//为菜单项“管理学生密码”注册监听器
		jmiStudentPassword.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jmiStudentPasswordActionPerformed(evt);
					}
				});
		jMenu4.add(jmiStudentPassword);
		jMenu1.add(jMenu4);
		jmiExit.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/exit.png")));
		jmiExit.setText("退出系统");
		//为菜单项“退出系统”注册监听器
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
		jMenuItem1.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/help.png"))); 
		jMenuItem1.setText("操作指南");
		jMenu2.add(jMenuItem1);

		jMenuItem2.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/phone.png")));
		jMenuItem2.setText("联系技术人员");
		jMenu2.add(jMenuItem2);

		jMenuBar1.add(jMenu2);
		setJMenuBar(jMenuBar1);

		//创建GroupLayout布局管理器
		GroupLayout layout = new GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		//设置沿水平轴确定组件的位置和大小
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(table,
				GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE));
		//设置沿垂直轴确定组件的位置和大小
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(table,
				GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE));

		pack();//依据放置的组件设定窗口的大小，使窗口恰好容纳所有的组件
	}
	//菜单项“管理学生密码”触发事件
	private void jmiStudentPasswordActionPerformed(ActionEvent evt) {
		StudentPasswordInterFrm studentPasswordInterFrm = new StudentPasswordInterFrm();
		studentPasswordInterFrm.setVisible(true);
		this.table.add(studentPasswordInterFrm);
	}
	//菜单项“查看学生资料”触发事件
	private void jmiStudentViewActionPerformed(ActionEvent evt) {
		StudentViewInterFrm studentViewInterFrm = new StudentViewInterFrm();
		studentViewInterFrm.setVisible(true);
		this.table.add(studentViewInterFrm);
	}
	//菜单项“查看选课情况”触发事件
	private void jmiCourseViewActionPerformed(ActionEvent evt) {
		CourseViewInterFrm courseViewInterFrm = new CourseViewInterFrm();
		courseViewInterFrm.setVisible(true);
		this.table.add(courseViewInterFrm);
	}
	//菜单项“修改课程信息”触发事件
	private void jmiCourseManageActionPerformed(ActionEvent evt) {
		CourseManageInterFrm courseManageInterFrm = new CourseManageInterFrm();
		courseManageInterFrm.setVisible(true);
		this.table.add(courseManageInterFrm);
	}
	//菜单项“增加课程条目”触发事件
	private void jmiCourseAddActionPerformed(ActionEvent evt) {
		CourseAddInterFrm courseAddInterFrm = new CourseAddInterFrm();
		courseAddInterFrm.setVisible(true);
		this.table.add(courseAddInterFrm);
	}
	//菜单项“退出系统”触发事件
	private void jmiExitActionPerformed(ActionEvent evt) {
		int result = JOptionPane.showConfirmDialog(this, "是否退出系统");
		if (result == 0) {
			this.dispose();
		}
	}

	//主函数
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrm_admin().setVisible(true);
			}
		});
	}



}