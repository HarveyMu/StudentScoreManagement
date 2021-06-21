package model;

import java.io.Serializable;
/**
 * 
 * @author Harvey
 *
 */
public class CourseFailRate implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *  课程号
	 */
	private String cNo;
	/**
	 *  课程名称
	 */
	private String cName;
	/**
	 *  不及格率
	 */
	private double failRate;

	public String getCno() {
		return cNo;
	}

	public void setCno(String cno) {
		cNo = cno;
	}

	public String getCname() {
		return cName;
	}

	public void setCname(String cname) {
		cName = cname;
	}

	public double getFailRate() {
		return getFailRate();
	}

	public void setFailRate(double failRate) {
		this.failRate = failRate;
	}

}
