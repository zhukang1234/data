
package com.java.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.BorderFactory;
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

import com.java.dao.StudentDao;
import com.java.model.Student;
import com.java.util.DbUtil;
import com.java.util.StringUtil;

public class StudentPasswordInterFrm extends JInternalFrame {
	
	/**
	 * 学生密码管理界面
	 */
	//定义成员变量
	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JScrollPane jScrollPane1;
	private JButton jb_modify;
	private JButton jb_search;
	private JTable passwordTable;
	private JTextField passwordTxt;
	private JTextField s_snoTxt;
	private JTextField snoTxt;
	
	DbUtil dbUtil = new DbUtil();
	StudentDao studentDao = new StudentDao();
	//定义学生密码查看方法；
	public StudentPasswordInterFrm() {
		initComponents();
		this.setLocation(200, 50);
		this.fillTable(new Student());
	}
	//设置表单
	private void fillTable(Student student) {
		DefaultTableModel dtm = (DefaultTableModel) passwordTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;

		try {
			con = dbUtil.getCon();
			ResultSet rs = studentDao.PasswordList(con, student);

			while (rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getString("Sno"));
				v.add(rs.getString("Spassword"));

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
	//初始化表单和布局管理
	private void initComponents() {

		jScrollPane1 = new JScrollPane();
		passwordTable = new JTable();
		jPanel1 = new JPanel();
		jLabel1 = new JLabel();
		s_snoTxt = new JTextField();
		jb_search = new JButton();
		jPanel2 = new JPanel();
		snoTxt = new JTextField();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		passwordTxt = new JTextField();
		jb_modify = new JButton();

		setClosable(true);
		setIconifiable(true);
		setTitle("学生密码管理");

		passwordTable.setModel(new DefaultTableModel(
				new Object[][] {

				}, new String[] { "学生学号", "密码" }) {
			/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		passwordTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				passwordTableMousePressed(evt);
			}
		});
		jScrollPane1.setViewportView(passwordTable);

		jPanel1.setBorder(BorderFactory
				.createTitledBorder("搜索条件"));

		jLabel1.setText("学生学号");

		jb_search
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/search.png"))); 
		jb_search.setText("查询");
		jb_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_searchActionPerformed(evt);
			}
		});
		//使用RroupLayout流布局管理
		GroupLayout jPanel1Layout = new GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addContainerGap(42,
						Short.MAX_VALUE).addComponent(jLabel1).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(s_snoTxt,
								GroupLayout.PREFERRED_SIZE, 103,
								GroupLayout.PREFERRED_SIZE).addGap(
								42, 42, 42).addComponent(jb_search).addGap(32,
								32, 32)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
															    GroupLayout.Alignment.BASELINE)
														.addComponent(
																s_snoTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jb_search)
														.addComponent(jLabel1))
										.addContainerGap(18, Short.MAX_VALUE)));

		jPanel2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("表单操作"));


		snoTxt.setEditable(false);

		jLabel2.setText("学生学号");

		jLabel3.setText("密码");
		//布局
		GroupLayout jPanel2Layout = new GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												snoTxt,
												GroupLayout.PREFERRED_SIZE,
												93,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												43, Short.MAX_VALUE)
										.addComponent(jLabel3)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												passwordTxt,
												GroupLayout.PREFERRED_SIZE,
												122,
												GroupLayout.PREFERRED_SIZE)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																snoTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3)
														.addComponent(
																passwordTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(18, Short.MAX_VALUE)));

		jb_modify
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/modify.png"))); 
		jb_modify.setText("修改");
		jb_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jb_modifyActionPerformed(evt);
			}
		});

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
																		.addGroup(
																				layout
																						.createParallelGroup(
																								GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jPanel2,
																								GroupLayout.Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																							    GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPanel1,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jScrollPane1,
																								GroupLayout.DEFAULT_SIZE,
																								364,
																								Short.MAX_VALUE)))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				147,
																				147,
																				147)
																		.addComponent(
																				jb_modify)))
										.addContainerGap()));
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
												169,
											    GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel2,
												GroupLayout.PREFERRED_SIZE,
											    GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(
												jb_modify).addContainerGap(29,
												Short.MAX_VALUE)));

		pack();
	}

	private void jb_modifyActionPerformed(ActionEvent evt) {
		String sno = this.snoTxt.getText();
		if (StringUtil.isEmpty(sno)) {
			JOptionPane.showMessageDialog(this, "请选择要修改的记录!");
			return;
		}
		String password = this.passwordTxt.getText();
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(this, "密码不能为空!");
			return;
		}
		Student student = new Student(Integer.parseInt(sno), password);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int modifyNum = studentDao.PasswordModify(con, student);
			if (modifyNum == 1) {
				JOptionPane.showMessageDialog(this, "修改成功!");
				this.resetValue();
				this.fillTable(new Student());
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

	private void resetValue() {
		this.snoTxt.setText("");
		this.passwordTxt.setText("");
	}

	private void passwordTableMousePressed(MouseEvent evt) {
		int row = passwordTable.getSelectedRow();
		this.snoTxt.setText((String) passwordTable.getValueAt(row, 0));
		this.passwordTxt.setText((String) passwordTable.getValueAt(row, 1));
	}

	private void jb_searchActionPerformed(ActionEvent evt) {
		String s_sno = this.s_snoTxt.getText();
		if (StringUtil.isEmpty(s_sno)) {
			s_sno = "-1";
		}
		Student student = new Student(Integer.parseInt(s_sno));

		this.fillTable(student);
	}

	


}