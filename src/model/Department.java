package model;

import java.io.Serializable;
/**
 * 
 * @author Harvey
 *
 */
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *  系编号
	 */
	private String dNo;
	/**
	 *  系名
	 */
	private String dName;

	public String getDno() {
		return dNo;
	}

	public void setDno(String dno) {
		dNo = dno;
	}

	public String getDname() {
		return dName;
	}

	public void setDname(String dname) {
		dName = dname;
	}

}
