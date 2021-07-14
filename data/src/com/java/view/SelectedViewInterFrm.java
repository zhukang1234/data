

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
import javax.swing.table.DefaultTableModel;

import com.java.dao.SelectionDao;
import com.java.model.Course;
import com.java.model.Selection;
import com.java.util.DbUtil;

//学生选课界面
public class SelectedViewInterFrm extends JInternalFrame {
	
	/**
	 * 定义变量成员
	 */
	private static final long serialVersionUID = 1L;
	private JTable courseTable;
	private JScrollPane jScrollPane1;
	private JButton jb_selectionCancel;
	
	DbUtil dbUtil = new DbUtil();
	SelectionDao selectionDao = new SelectionDao();
	private int courseId=-1;
	//定义课程选择方法
	public SelectedViewInterFrm() {
		initComponents();
		this.setLocation(200, 50);
		this.fillTable(new Course());
	}
	//课单选择界面->总Table
	private void fillTable(Course course) {
		DefaultTableModel dtm = (DefaultTableModel) courseTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		int currentSno = LogOnFrm.currentStudent.getSno();
		try {
			con = dbUtil.getCon();
			ResultSet rs = selectionDao.SelectedList(con, currentSno);
			while (rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getString("courseId"));
				v.add(rs.getString("courseName"));
				v.add(rs.getString("courseTime"));
				v.add(rs.getString("courseTeacher"));
				dtm.addRow(v);

			}
		//对于异常的处理
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//打印提示信息；
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//初始化表单；
	private void initComponents() {

		jScrollPane1 = new JScrollPane();
		courseTable = new JTable();
		jb_selectionCancel = new JButton();

		setClosable(true);
		setIconifiable(true);
		setTitle("已选课程查看");

		courseTable.setModel(new DefaultTableModel(
				new Object[][] {

				}, new String[] { "课程编号", "课程名称", "上课时间", "任课老师" }) {
			/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		courseTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				courseTableMousePressed(evt);
			}
		});
		jScrollPane1.setViewportView(courseTable);

		jb_selectionCancel.setText("退选");
		jb_selectionCancel
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jb_selectionCancelActionPerformed(evt);
					}
				});
		//布局
		GroupLayout layout = new GroupLayout(
				getContentPane());
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
																		.addComponent(
																				jScrollPane1,
																				GroupLayout.DEFAULT_SIZE,
																				602,
																				Short.MAX_VALUE))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				272,
																				272,
																				272)
																		.addComponent(
																				jb_selectionCancel)))
										.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(
						jScrollPane1, GroupLayout.PREFERRED_SIZE,
						205, GroupLayout.PREFERRED_SIZE).addGap(18,
						18, 18).addComponent(jb_selectionCancel)
						.addContainerGap(14, Short.MAX_VALUE)));

		pack();
	}
	//鼠标点击事件方法；
	private void courseTableMousePressed(MouseEvent evt) {
		int row = courseTable.getSelectedRow();
		courseId = Integer.parseInt((String) courseTable.getValueAt(row, 0));
	}
	//点击事件发生后的提示信息
	private void jb_selectionCancelActionPerformed(
			
			java.awt.event.ActionEvent evt) {
		if (courseId==-1) {
			JOptionPane.showMessageDialog(this, "请选择一门课程！");
			return;
		}
		int n = JOptionPane.showConfirmDialog(this, "确定要退选该门课程吗?");
		if (n == 0) {
			Connection con = null;
			int currentSno = LogOnFrm.currentStudent.getSno();
			Selection selection = new Selection(courseId, currentSno);
			try {
				con = dbUtil.getCon();
				int selectionNum = selectionDao.SelectionCancel(con, selection);
				int selectedNum = selectionDao.NumSelectedMinus(con, courseId);
				if (selectionNum == 1 && selectedNum == 1) {
					JOptionPane.showMessageDialog(this, "退选成功!");
					this.fillTable(new Course());
				} else {
					JOptionPane.showMessageDialog(this, "退选失败!");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "退选失败!");
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