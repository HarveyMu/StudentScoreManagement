package model;

import java.io.Serializable;
/**
 * 
 * @author Harvey
 *
 */
public class Class implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *  班级编号
	 */
	private String cLno;
	/**
	 *  班级名称
	 */
	private String cLname;
	/**
	 *  所属院系
	 */
	private String dNo;

	public String getClno() {
		return cLno;
	}

	public void setClno(String clno) {
		cLno = clno;
	}

	public String getClname() {
		return cLname;
	}

	public void setClname(String clname) {
		cLname = clname;
	}

	public String getDno() {
		return dNo;
	}

	public void setDno(String dno) {
		dNo = dno;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
