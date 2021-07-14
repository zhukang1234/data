package com.java.model;

//计数model类
public class Selection {
	//私有成员
	int selectId;
    int courseId=-1;
    int Sno;
    //默认构造方法
    public Selection() {
    	super();
    	// TODO Auto-generated constructor stub
    	}
    public Selection(int courseId, int sno) {
    	super();
    	this.courseId = courseId;
    	Sno = sno;
    	}
    public int getSelectId() {
    	return selectId;
    	}
    public void setSelectId(int selectId) {
    	this.selectId = selectId;
    	}
    public int getCourseId() {
    	return courseId;
    	}
    public void setCourseId(int courseId) {
    	this.courseId = courseId;
    	}
    public int getSno() {
    	return Sno;
    	}
    public void setSno(int sno) {
    	Sno = sno;
    	}
    }
