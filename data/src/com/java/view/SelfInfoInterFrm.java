
package com.java.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java.dao.StudentDao;
import com.java.model.Sinfo;
import com.java.util.DbUtil;

//学籍信息查看界面
public class SelfInfoInterFrm extends JInternalFrame {
	
	/**
	 * 定义成员变量；
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable infoTable;//设置主表单
	private JScrollPane jScrollPane1;//设置Panel
	
	DbUtil dbUtil = new DbUtil();
	StudentDao studentDao = new StudentDao();
	//定义查看方法
	public SelfInfoInterFrm() {
		initComponents();
		this.setLocation(200, 50);
		this.fillTable(new Sinfo());
	}
	//定义表单
	private void fillTable(Sinfo sinfo) {
		DefaultTableModel dtm = (DefaultTableModel)infoTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		int currentSno = LogOnFrm.currentStudent.getSno();
		sinfo = new Sinfo(currentSno);
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

	@SuppressWarnings("serial")
	//初始化表单
	private void initComponents() {
		//设置Panel
		jScrollPane1 = new JScrollPane();
		infoTable = new JTable();

		setClosable(true);
		setIconifiable(true);
		setTitle("学籍信息查看");//设置标题

		infoTable.setModel(new DefaultTableModel(
				new Object[][] {
				//表单中的成员
				}, new String[] { "学号", "姓名", "性别", "专业", "电话" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(infoTable);

		GroupLayout layout = new GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(
						jScrollPane1, GroupLayout.DEFAULT_SIZE,
						529, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(
						jScrollPane1, GroupLayout.PREFERRED_SIZE,
						51,GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		pack();
	}


}