package model;

import java.io.Serializable;
/**
 * 
 * @author Harvey
 *
 */
public class CourseAvg implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 课程号
	 */
	private String cNo;
	/**
	 * 课程名称
	 */
	private String cName;
	/**
	 * 平均分
	 */
	private double avg;
	
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
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
}
