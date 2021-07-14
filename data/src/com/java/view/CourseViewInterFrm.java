
package com.java.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.java.dao.CourseDao;
import com.java.model.Course;
import com.java.util.DbUtil;
//课程查看
public class CourseViewInterFrm extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//私有成员变量
	private JTable courseTable;
	private JScrollPane jScrollPane1;
	//数据库连接
	DbUtil dbUtil = new DbUtil();
	CourseDao courseDao = new CourseDao();

	//构造方法
	public CourseViewInterFrm() {
		initComponents();
		this.setLocation(200, 50);
		this.fillTable(new Course());
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
		}catch (Exception e) {
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
	//添加组件方法
	private void initComponents() {
		//创建组件
		jScrollPane1 = new JScrollPane();
        courseTable = new JTable();
        //设置窗口属性
        setClosable(true);
        setIconifiable(true);
        setTitle("课程查看");
        //设置表
        courseTable.setModel(new DefaultTableModel(
        		new Object [][] {
        				
        		},
        		new String [] {
        				"课程编号", "课程名称", "上课时间", "任课老师", "课程容量", "已选人数"
        				}
        		) {
        	/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean [] {
        			false, false, false, false, false, false
        			};
        	public boolean isCellEditable(int rowIndex, int columnIndex) {
        		return canEdit [columnIndex];
        		}
        	});
        //将表添加到滚动面板中
        jScrollPane1.setViewportView(courseTable);
        //创建GroupLayout布局管理器
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        //设置沿水平轴确定组件的位置和大小
        layout.setHorizontalGroup(
        		layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        				.addContainerGap()
        				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE).addContainerGap())
        				);
        //设置沿垂直轴确定组件的位置和大小
        layout.setVerticalGroup(
        		layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        				.addContainerGap()
        				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
        				.addContainerGap())
        				);

        pack();//依据放置的组件设定窗口的大小，使窗口恰好容纳所有的组件
}


}