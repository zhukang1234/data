

package com.java.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import com.java.dao.CourseDao;
import com.java.dao.SelectionDao;
import com.java.model.Course;
import com.java.model.Selection;
import com.java.util.DbUtil;


//课程选择
public class SelectCourseInterFrm extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//私有成员
	private JTable courseTable;
	private JScrollPane jScrollPane1;
	private JButton jb_confirm;
	private JButton jb_underFull;
	private int capacity;
	private int numSelected;
	private int courseId=-1;
	
	//创建数据库连接
	DbUtil dbUtil = new DbUtil();
	CourseDao courseDao = new CourseDao();
	SelectionDao selectionDao = new SelectionDao();


	//构造方法
	public SelectCourseInterFrm() {
		initComponents();
		this.setLocation(200, 50);
		this.fillTable(new Course());

	}
	//填表
	private void fillTable(Course course) {
		DefaultTableModel dtm = (DefaultTableModel) courseTable.getModel();
		dtm.setRowCount(0);
		//建立连接
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
	//放置组件
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		courseTable = new javax.swing.JTable();
		jb_underFull = new javax.swing.JButton();
		jb_confirm = new javax.swing.JButton();
		//窗口设置
		setClosable(true);
		setIconifiable(true);
		setTitle("课程选择");
		//表格式
		courseTable.setModel(new DefaultTableModel(
				new Object[][] {

				}, new String[] { "课程编号", "课程名称", "上课时间", "任课老师", "课程容量",
						"已选人数" }) {
			/**
							 * 
							 */
							private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, true,
					true, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		//给courseTable添加监听器
		courseTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				courseTableMousePressed(evt);
			}
		});
		//将courseTable添加到滚动面板中
		jScrollPane1.setViewportView(courseTable);
		//按钮设置
		jb_underFull
			.setText("只显示未选满课程");
		jb_underFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_underFullActionPerformed(evt);
			}
		});
		//确认选课按钮
		jb_confirm.setText("确认选课");
		jb_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_confirmActionPerformed(evt);
			}
		});

		//创建GroupLayout布局管理器
		GroupLayout layout = new GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		//设置沿水平轴确定组件的位置和大小
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
																		.addComponent(
																				jScrollPane1,
																				GroupLayout.DEFAULT_SIZE,
																				578,
																				Short.MAX_VALUE))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				134,
																				134,
																				134)
																		.addComponent(
																				jb_underFull)
																		.addGap(
																				98,
																				98,
																				98)
																		.addComponent(
																				jb_confirm)))
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
												jScrollPane1,
												GroupLayout.PREFERRED_SIZE,
												319,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED,
												37, Short.MAX_VALUE)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																jb_underFull)
														.addComponent(
																jb_confirm))
										.addContainerGap()));

		pack();//依据放置的组件设定窗口的大小，使窗口恰好容纳所有的组件
	}
	//重写监听器的各方法
	private void jb_underFullActionPerformed(ActionEvent evt) {
		DefaultTableModel dtm = (DefaultTableModel) courseTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		Course course=new Course();
		try {
			con = dbUtil.getCon();
			ResultSet rs = courseDao.UnderFullList(con, course);
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
	private void courseTableMousePressed(MouseEvent evt) {
		int row = courseTable.getSelectedRow();

		courseId = Integer.parseInt((String) courseTable.getValueAt(row, 0));
		capacity = Integer.parseInt((String) courseTable.getValueAt(row, 4));
		numSelected = Integer.parseInt((String) courseTable.getValueAt(row, 5));

	}

	private void jb_confirmActionPerformed(ActionEvent evt) {
		if (courseId==-1) {
			JOptionPane.showMessageDialog(this, "请选择一门课程！");
			return;
		}
		if (capacity == numSelected) {
			JOptionPane.showMessageDialog(this, "该课程已选满,请选择其他课程.");
			return;
		}

		int n = JOptionPane.showConfirmDialog(this, "确定要选择该门课程吗?");
		if (n == 0) {
			Connection con = null;
			int currentSno = LogOnFrm.currentStudent.getSno();
			Selection selection = new Selection(courseId, currentSno);
			try {
				con = dbUtil.getCon();
				int selectionNum = selectionDao.SelectionAdd(con, selection);
				int selectedNum = selectionDao.NumSelectedAdd(con, courseId);
				if (selectionNum == 1 && selectedNum == 1) {
					JOptionPane.showMessageDialog(this, "选课成功!");
					this.fillTable(new Course());
				} else {
					JOptionPane.showMessageDialog(this, "选课失败!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "已选过该门课程!");
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