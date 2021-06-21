package model;

import java.io.Serializable;
/**
 * 
 * @author Harvey
 *
 */
public class Course implements Serializable {

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
	 *  执教老师
	 */
	private String cTeacher;
	/**
	 *  学分
	 */
	private int cCredit;

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

	public String getCteacher() {
		return cTeacher;
	}

	public void setCteacher(String cteacher) {
		cTeacher = cteacher;
	}

	public int getCcredit() {
		return cCredit;
	}

	public void setCcredit(int ccredit) {
		cCredit = ccredit;
	}

}
