
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.java.dao.CourseDao;
import com.java.model.Course;
import com.java.util.DbUtil;
import com.java.util.StringUtil;


//课程添加内部窗体
public class CourseAddInterFrm extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//私有成员
	private JTextField capacityTxt;
	private JTextField courseNameTxt;
	private JTextField courseTeacherTxt;
	private JTextField courseTimeTxt;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JButton jb_add;
	private JButton jb_reset;
	//连接数据库
	DbUtil dbUtil=new DbUtil();
	CourseDao coursedao=new CourseDao();

	//构造方法
	public CourseAddInterFrm() {
		initComponents();
		this.setLocation(200, 50);//设置位置
	}

	//放置组件方法
	private void initComponents() {

		//创建组件
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		courseTimeTxt = new JTextField();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		courseNameTxt = new JTextField();
		courseTeacherTxt = new JTextField();
		capacityTxt = new JTextField();
		jb_add = new JButton();
		jb_reset = new JButton();

		setClosable(true);//设置为可关闭的内部窗口
		setIconifiable(true);//设置成可以缩小变成一个图标
		setTitle("课程添加");//设置内部窗体的标题

		//标签设置文本
		jLabel1.setText("课程名称");
		jLabel2.setText("上课时间");
		jLabel3.setText("任课老师");
		jLabel4.setText("课程容量");
		
		//添加按钮放置图片
		jb_add.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/add.png"))); 
		//添加按钮设置文本
		jb_add.setText("添加");
		//为添加按钮注册监听器
		jb_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_addActionPerformed(evt);
			}
		});
		//重置按钮放置图片
		jb_reset.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/reset.png"))); 
		//重置按钮设置文本
		jb_reset.setText("重置");
		//为重置按钮注册监听器
		jb_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_resetActionPerformed(evt);
			}
		});
		//创建GroupLayout布局管理器
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		//设置沿水平轴确定组件的位置和大小
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
										.addGap(41, 41, 41)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addGroup(layout.createSequentialGroup()
																		.addComponent(jLabel1)
																		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(courseNameTxt,GroupLayout.PREFERRED_SIZE,144,GroupLayout.PREFERRED_SIZE)
																		.addGap(60,60,60)
																		.addComponent(jLabel2)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(courseTimeTxt,GroupLayout.PREFERRED_SIZE,144,GroupLayout.PREFERRED_SIZE))
														.addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
																						.addGroup(layout.createSequentialGroup().addComponent(jLabel3)
																										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(courseTeacherTxt,GroupLayout.PREFERRED_SIZE,144,GroupLayout.PREFERRED_SIZE))
																						.addComponent(jb_add))
																		.addGap(60,60,60)
																		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																						.addGroup(layout.createSequentialGroup()
																								.addComponent(jLabel4)
																										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(capacityTxt,GroupLayout.PREFERRED_SIZE,144,GroupLayout.PREFERRED_SIZE))
																						.addComponent(jb_reset))))
										.addContainerGap(44, Short.MAX_VALUE)));
		//设置沿垂直轴确定组件的位置和大小
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(46, 46, 46)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(jLabel2)
														.addComponent(courseTimeTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
														.addComponent(courseNameTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(jLabel4)
														.addComponent(courseTeacherTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
														.addComponent(capacityTxt,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,55, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(jb_reset)
														.addComponent(jb_add))
										.addGap(20, 20, 20)));

		pack();//依据放置的组件设定窗口的大小，使窗口恰好容纳所有的组件
	}
	//添加按钮触发事件
	private void jb_addActionPerformed(ActionEvent evt) {
		//获取文本框中的值
		String courseName=this.courseNameTxt.getText();
		String courseTime=this.courseTimeTxt.getText();
		String courseTeacher=this.courseTeacherTxt.getText();
		String  capacity=this.capacityTxt.getText();
		//关于文本框中输入为空的各种情况的判断
		if(StringUtil.isEmpty(courseName)){
			JOptionPane.showMessageDialog(this, "课程名称不能为空!");
			return;
		}
		if(StringUtil.isEmpty(courseTime)){
			JOptionPane.showMessageDialog(this, "上课时间不能为空!");
			return;
		}
		if(StringUtil.isEmpty(courseTeacher)){
			JOptionPane.showMessageDialog(this, "任课老师不能为空!");
			return;
		}
		if(StringUtil.isEmpty(capacity)){
			JOptionPane.showMessageDialog(this, "课程容量不能为空!");
			return;
		}
		//创建一个连接
		Course course=new Course(courseName,courseTime,courseTeacher,Integer.parseInt(capacity));
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int n=coursedao.courseAdd(con, course);
			if(n==1){
				JOptionPane.showMessageDialog(this, "课程添加成功!");
				this.resetValue();
			}else{
				JOptionPane.showMessageDialog(this, "课程添加失败!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "课程添加失败!");
		}
		
	}
	//重置按钮触发事件
	private void jb_resetActionPerformed(ActionEvent evt) {
		this.resetValue();
	}
	//重置清空文本框中的内容
	private void resetValue(){
		this.courseNameTxt.setText("");
	    this.courseTeacherTxt.setText("");
	    this.courseTimeTxt.setText("");
	    this.capacityTxt.setText("");
	    }
}