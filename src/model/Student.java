package model;

import java.io.Serializable;
/**
 * 
 * @author Harvey
 *
 */
public class Student implements Serializable {

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
	 *  所在班级
	 */
	private String cLno;

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

	public String getClno() {
		return cLno;
	}

	public void setClno(String clno) {
		this.cLno = clno;
	}

}
