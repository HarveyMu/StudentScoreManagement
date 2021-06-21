package model;

import java.io.Serializable;
/**
 * 
 * @author Harvey
 *
 */
public class CourseRanking implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *  学号
	 */
	private String sNo;
	/**
	 *  系名
	 */
	private String dName;
	/**
	 *  班名
	 */
	private String cLname;
	/**
	 *  学生姓名
	 */
	private String sName;
	/**
	 *  学生性别
	 */
	private String sSex;
	/**
	 *  学生年龄
	 */
	private int sage;
	/**
	 *  成绩
	 */
	private Double grade;

	public String getDname() {
		return dName;
	}

	public void setDname(String dname) {
		dName = dname;
	}

	public String getClname() {
		return cLname;
	}

	public void setClname(String clname) {
		cLname = clname;
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

	public void setSage(int sage) {
		this.sage = sage;
	}

	public String getSno() {
		return sNo;
	}

	public void setSno(String sno) {
		sNo = sno;
	}

	public String getSname() {
		return sName;
	}

	public void setSname(String sname) {
		sName = sname;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
