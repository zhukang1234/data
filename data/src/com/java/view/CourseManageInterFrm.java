
package com.java.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import com.java.dao.CourseDao;
import com.java.model.Course;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

//课程管理窗口
public class CourseManageInterFrm extends JInternalFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//私有成员
	private JTextField capacityTxt;
	private JTextField courseIdTxt;
	private JTextField courseNameTxt;
	private JTable courseTable;
	private JTextField courseTeacherTxt;
	private JTextField courseTimeTxt;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	private JButton jb_delete;
	private JButton jb_modify;
	private JButton jb_search;
	private JTextField numSelectedTxt;
	private JTextField s_courseNameTxt;
	private JTextField s_courseTeacherTxt;
	private JTextField s_courseTimeTxt;
	private int NumSelected;
	

	//数据库连接
	DbUtil dbUtil = new DbUtil();
	CourseDao courseDao = new CourseDao();
	

	//构造方法
	public CourseManageInterFrm() {
		//调用放置组件方法
		initComponents();
		this.setLocation(200, 50);
		this.fillTable(new Course());

	}
	//重置时间清空方法
	private void resetValue() {
		this.courseIdTxt.setText("");
		this.courseNameTxt.setText("");
		this.courseTeacherTxt.setText("");
		this.courseTimeTxt.setText("");
		this.capacityTxt.setText("");
		this.numSelectedTxt.setText("");
	}
	//填表
	private void fillTable(Course course) {
		DefaultTableModel dtm = (DefaultTableModel) courseTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = courseDao.courseList(con, course);
			while (rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getString("courseId"));
				v.add(rs.getString("courseName"));
				v.add(rs.getString("courseTime"));
				v.add(rs.getString("courseTeacher"));
				v.add(rs.getString("capacity"));
				v.add(rs.getString("numSelected"));
				dtm.addRow(v);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//放置组件方法
	private void initComponents() {
		//创建组件
		jPanel1 = new JPanel();
		jLabel1 = new JLabel();
		s_courseNameTxt = new JTextField();
		jLabel2 = new JLabel();
		s_courseTimeTxt = new JTextField();
		jLabel3 = new JLabel();
		s_courseTeacherTxt = new JTextField();
		jb_search = new JButton();
		jScrollPane1 = new JScrollPane();
		courseTable = new JTable();
		jPanel2 = new JPanel();
		courseIdTxt = new JTextField();
		jLabel4 = new JLabel();
		courseNameTxt = new JTextField();
		jLabel5 = new JLabel();
		courseTimeTxt = new JTextField();
		jLabel6 = new JLabel();
		courseTeacherTxt = new JTextField();
		jLabel7 = new JLabel();
		capacityTxt = new JTextField();
		jLabel8 = new JLabel();
		numSelectedTxt = new JTextField();
		jLabel9 = new JLabel();
		jb_modify = new JButton();
		jb_delete = new JButton();

		//设置窗口属性
		setClosable(true);
		setIconifiable(true);
		setTitle("课程信息修改");
		//设置jPane1面板
		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("搜索条件"));

		jLabel1.setText("课程名称");

		jLabel2.setText("任课老师");

		jLabel3.setText("上课时间");
		//给按钮jb_search添加图片和文字及监听器
		jb_search
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/search.png"))); 
		jb_search.setText("查询");
		jb_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_searchActionPerformed(evt);
			}
		});
		//创建GroupLayout布局管理器
		GroupLayout jPanel1Layout = new GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		//设置沿水平轴确定组件的位置和大小
		jPanel1Layout.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(24, 24, 24)
										.addComponent(jLabel1)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												s_courseNameTxt,
												GroupLayout.PREFERRED_SIZE,
												158,
												GroupLayout.PREFERRED_SIZE)
										.addGap(26, 26, 26)
										.addComponent(jLabel3)
										.addGap(18, 18, 18)
										.addComponent(
												s_courseTimeTxt,
												GroupLayout.PREFERRED_SIZE,
												149,
												GroupLayout.PREFERRED_SIZE)
										.addGap(46, 46, 46)
										.addComponent(jLabel2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												s_courseTeacherTxt,
												GroupLayout.PREFERRED_SIZE,
												110,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												50, Short.MAX_VALUE)
										.addComponent(jb_search).addGap(94, 94,
												94)));
		//设置沿垂直轴确定组件的位置和大小
		jPanel1Layout.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																s_courseNameTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel1)
														.addComponent(jb_search)
														.addComponent(jLabel3)
														.addComponent(
																s_courseTimeTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel2)
														.addComponent(
																s_courseTeacherTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		//设置表
		courseTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "课程编号", "课程名称", "上课时间", "任课老师", "课程容量",
						"已选人数" }) {
			/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		//给课程表添加监听器
		courseTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				courseTableMousePressed(evt);
			}
		});
		//将表添加到滚动面板中
		jScrollPane1.setViewportView(courseTable);
		 //设置jPanel2
	    jPanel2.setBorder(javax.swing.BorderFactory
			    .createTitledBorder("表单操作"));
	    //设置文本框的可编辑性
		courseIdTxt.setEditable(false);

		jLabel4.setText("课程编号");

		jLabel5.setText("课程名称");

		jLabel6.setText("上课时间");

		jLabel7.setText("任课老师");

		jLabel8.setText("课程容量");

		numSelectedTxt.setEditable(false);

		jLabel9.setText("已选人数");

		//创建GroupLayout布局管理器
		javax.swing.GroupLayout jPanel2Layout = new GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGap(34, 34, 34)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel7)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				courseTeacherTxt,
																				GroupLayout.PREFERRED_SIZE,
																				158,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				45,
																				45,
																				45)
																		.addComponent(
																				jLabel8)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				capacityTxt,
																				GroupLayout.PREFERRED_SIZE,
																				158,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				46,
																				46,
																				46)
																		.addComponent(
																				jLabel9)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				numSelectedTxt,
																				GroupLayout.PREFERRED_SIZE,
																				158,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel4)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				courseIdTxt,
																				GroupLayout.PREFERRED_SIZE,
																				158,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				45,
																				45,
																				45)
																		.addComponent(
																				jLabel5)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				courseNameTxt,
																				GroupLayout.PREFERRED_SIZE,
																				158,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(
																				46,
																				46,
																				46)
																		.addComponent(
																				jLabel6)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				courseTimeTxt,
																				GroupLayout.PREFERRED_SIZE,
																				158,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(149, Short.MAX_VALUE)));
		//设置沿垂直轴确定组件的位置和大小
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGap(19, 19, 19)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																courseIdTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel4)
														.addComponent(
																courseTimeTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel6)
														.addComponent(
																courseNameTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5))
										.addGap(32, 32, 32)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																courseTeacherTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel7)
														.addComponent(
																numSelectedTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel9)
														.addComponent(
																capacityTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel8))
										.addContainerGap(34, Short.MAX_VALUE)));

		//设置修改和删除按钮
		jb_modify.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/modify.png"))); 
		jb_modify.setText("修改");
		jb_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_modifyActionPerformed(evt);
			}
		});

		jb_delete
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/delete.png"))); 
		jb_delete.setText("删除");
		jb_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_deleteActionPerformed(evt);
			}
		});
		//创建GroupLayout布局管理器
		GroupLayout layout = new GroupLayout(
				getContentPane());
		//设置沿水平轴确定组件的位置和大小
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jPanel2,
																								GroupLayout.Alignment.TRAILING,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jScrollPane1,
																								GroupLayout.DEFAULT_SIZE,
																								920,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanel1,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				330,
																				330,
																				330)
																		.addComponent(
																				jb_modify)
																		.addGap(
																				78,
																				78,
																				78)
																		.addComponent(
																				jb_delete)))
										.addContainerGap()));
		//设置沿垂直轴确定组件的位置和大小
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
							GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jPanel1,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jScrollPane1,
												GroupLayout.PREFERRED_SIZE,
												165,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(
												jPanel2,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(27, 27, 27)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(jb_delete)
														.addComponent(jb_modify))
										.addContainerGap(36, Short.MAX_VALUE)));

		pack();//依据放置的组件设定窗口的大小，使窗口恰好容纳所有的组件
	}

	//重写事件监听器的方法
	private void jb_modifyActionPerformed(ActionEvent evt) {
		String courseId = this.courseIdTxt.getText();
		if (StringUtil.isEmpty(courseId)) {
			JOptionPane.showMessageDialog(this, "请选择要修改的记录!");
			return;
		}
		String courseName = this.courseNameTxt.getText();
		String courseTime = this.courseTimeTxt.getText();
		String courseTeacher = this.courseTeacherTxt.getText();
		String capacity = this.capacityTxt.getText();
		if (StringUtil.isEmpty(courseName)) {
			JOptionPane.showMessageDialog(this, "课程名称不能为空!");
			return;
		}
		if (StringUtil.isEmpty(courseTime)) {
			JOptionPane.showMessageDialog(this, "上课时间不能为空!");
			return;
		}
		if (StringUtil.isEmpty(courseTeacher)) {
			JOptionPane.showMessageDialog(this, "任课老师不能为空!");
			return;
		}
		if (StringUtil.isEmpty(capacity)) {
			JOptionPane.showMessageDialog(this, "课程容量不能为空!");
			return;
		}
		if (Integer.parseInt(capacity) < NumSelected) {
			JOptionPane.showMessageDialog(this, "课程容量不能小于已选课人数!");
			return;
		}
		Course course = new Course(Integer.parseInt(courseId), courseName,
				courseTime, courseTeacher, Integer.parseInt(capacity));
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int modifyNum = courseDao.courseModify(con, course);
			if (modifyNum == 1) {
				JOptionPane.showMessageDialog(this, "修改成功!");
				this.resetValue();
				this.fillTable(new Course());
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

	private void courseTableMousePressed(MouseEvent evt) {
		//获取选中的行
		int row = courseTable.getSelectedRow();
		this.courseIdTxt.setText((String) courseTable.getValueAt(row, 0));
		this.courseNameTxt.setText((String) courseTable.getValueAt(row, 1));
		this.courseTimeTxt.setText((String) courseTable.getValueAt(row, 2));
		this.courseTeacherTxt.setText((String) courseTable.getValueAt(row, 3));
		this.capacityTxt.setText((String) courseTable.getValueAt(row, 4));
		this.numSelectedTxt.setText((String) courseTable.getValueAt(row, 5));
		NumSelected = Integer.parseInt((String) courseTable.getValueAt(row, 5));
	}

	private void jb_searchActionPerformed(ActionEvent evt) {
		String s_courseName = this.s_courseNameTxt.getText();
		String s_courseTime = this.s_courseTimeTxt.getText();
		String s_courseTeacher = this.s_courseTeacherTxt.getText();
		Course course = new Course(s_courseName, s_courseTime, s_courseTeacher);
		this.fillTable(course);

	}

	private void jb_deleteActionPerformed(ActionEvent evt) {
		String courseId = this.courseIdTxt.getText();
		if (StringUtil.isEmpty(courseId)) {
			JOptionPane.showMessageDialog(this, "请选择要删除的记录!");
			return;
		}
		if (NumSelected > 0) {
			JOptionPane.showMessageDialog(this, "本课程已有人选,不能删除!");
			return;
		}
		int n = JOptionPane.showConfirmDialog(this, "确定要删除这条记录吗?");
		if (n == 0) {
			Connection con = null;

			try {
				con = dbUtil.getCon();
				int deleteNum = courseDao.courseDelete(con, courseId);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(this, "删除成功!");
					this.resetValue();
					this.fillTable(new Course());
				} else {
					JOptionPane.showMessageDialog(this, "删除失败!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "删除失败!");
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}