package model;

import java.io.Serializable;
/**
 * 
 * @author Harvey
 *
 */
public class Sc implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *  学号
	 */
	private String sNo;
	/**
	 *  姓名
	 */
	private String sName;
	/**
	 *  性别
	 */
	private String sSex;
	/**
	 *  年龄
	 */
	private int sage;
	/**
	 *  课程号
	 */
	private String cNo;
	/**
	 *  课程名称
	 */
	private String cName;
	/**
	 *  成绩
	 */
	private double grade;

	public String getCname() {
		return cName;
	}

	public void setCname(String cname) {
		cName = cname;
	}

	public String getSname() {
		return sName;
	}

	public void setSname(String sname) {
		sName = sname;
	}

	public String getSsex() {
		return sSex;
	}

	public void setSsex(String ssex) {
		sSex = ssex;
	}

	public int getSage() {
		return sage;
	}

	public void setSage(int i) {
		sage = i;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getSno() {
		return sNo;
	}

	public void setSno(String sno) {
		sNo = sno;
	}

	public String getCno() {
		return cNo;
	}

	public void setCno(String cno) {
		cNo = cno;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
