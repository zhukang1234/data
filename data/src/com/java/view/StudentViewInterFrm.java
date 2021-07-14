

package com.java.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import com.java.dao.StudentDao;
import com.java.model.Sinfo;
import com.java.util.DbUtil;
import com.java.util.StringUtil;
//学生资料查看界面
public class StudentViewInterFrm extends JInternalFrame {
	
	/**
	 * 定义变量成员
	 */
	private static final long serialVersionUID = 1L;
	private JTextField SnameTxt;
	private JTextField SnoTxt;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JButton jb_search;
	private JTable studentTable;
	
	DbUtil dbUtil = new DbUtil();
	StudentDao studentDao = new StudentDao();

	//定义学生资料查看方法
	public StudentViewInterFrm() {
		initComponents();
		this.setLocation(200, 50);
		this.fillTable(new Sinfo());
	}

	private void fillTable(Sinfo sinfo) {
		DefaultTableModel dtm = (DefaultTableModel) studentTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;

		try {
			con = dbUtil.getCon();
			ResultSet rs = studentDao.StudentList(con, sinfo);

			while (rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getString("Sno"));
				v.add(rs.getString("Sname"));
				v.add(rs.getString("Ssex"));
				v.add(rs.getString("Smajor"));
				v.add(rs.getString("Stele"));
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
	//初始化表单
	private void initComponents() {

		jScrollPane1 = new JScrollPane();
		studentTable = new JTable();
		jPanel1 = new JPanel();
		jLabel1 = new JLabel();
		SnoTxt = new JTextField();
		jLabel2 = new JLabel();
		SnameTxt = new JTextField();
		jb_search = new JButton();

		setClosable(true);
		setIconifiable(true);
		setTitle("学生资料查看");
		//设置学生信息表单栏
		studentTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "学号", "姓名", "性别", "专业", "电话" }) {
			/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(studentTable);
		//搜索条件Panel
		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("搜索条件"));

		//学生学号Panel
		jLabel1.setText("学生学号");
		//学生姓名Panel
		jLabel2.setText("学生姓名");


		jb_search
				.setIcon(new ImageIcon(this.getClass().getResource(
						"/com/java/view/image/search.png"))); 
		jb_search.setText("查询");//设置“查询”点击按钮
		jb_search.addActionListener(new ActionListener() {//注册监听器
			public void actionPerformed(ActionEvent evt) {
				jb_searchActionPerformed(evt);//对时间对象做出的反应
			}
		});
		//设置GroupLayout流对象
		GroupLayout jPanel1Layout = new GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(78, 78, 78)
										.addComponent(jLabel1)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												SnoTxt,
												GroupLayout.PREFERRED_SIZE,
												104,
												GroupLayout.PREFERRED_SIZE)
										.addGap(69, 69, 69)
										.addComponent(jLabel2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												SnameTxt,
												GroupLayout.PREFERRED_SIZE,
												96,
												GroupLayout.PREFERRED_SIZE)
										.addGap(56, 56, 56).addComponent(
												jb_search).addContainerGap(85,
												Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(25, 25, 25)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(
																SnoTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jb_search)
														.addComponent(
																SnameTxt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel2))
										.addContainerGap(38, Short.MAX_VALUE)));

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
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																jPanel1,
																GroupLayout.DEFAULT_SIZE,
															    GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane1,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																685,
																Short.MAX_VALUE))
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
												148,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(36, Short.MAX_VALUE)));

		pack();
	}
	//定义搜索学生信息方法
	private void jb_searchActionPerformed(java.awt.event.ActionEvent evt) {
		String sno = this.SnoTxt.getText();
		String sname = this.SnameTxt.getText();
		if (StringUtil.isEmpty(sno)) {
			sno = "-1";
		}
		Sinfo sinfo = new Sinfo(Integer.parseInt(sno), sname);
		this.fillTable(sinfo);
	}
	



}