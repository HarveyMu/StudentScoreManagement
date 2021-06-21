package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.*;

import model.Student;
import utils.DbUtils;
/**
 * 
 * @author Harvey
 *
 */
public class StudentDao {
	/**
	 *  获取所有学生的信息，用ArrayList返回
	 * @return
	 */
	public ArrayList<Student> queryAllStudent() {
		Connection conn = DbUtils.getConnection();
		String sql = "select * from student order by sno;";
		ArrayList<Student> results = new ArrayList<Student>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student temp = new Student();
				temp.setSno(rs.getString("sno"));
				temp.setSname(rs.getString("sname"));
				temp.setSsex(rs.getString("ssex"));
				temp.setSage(rs.getInt("sage"));
				temp.setClno(rs.getString("clno"));
				results.add(temp);
			}
			/**
			 *  关闭资源
			 */
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(conn);
		}
		return results;
	}

	/**
	 *  插入学生信息，返回一个int值表示状态,1：成功，0失败
	 * @param Sno
	 * @param Sname
	 * @param Ssex
	 * @param Sage
	 * @param Clno
	 * @return
	 */
	public int insertStudent(String sNo, String sName, String sSex, int sage, String cLno) {
		Connection conn = DbUtils.getConnection();
		String sql = "insert into student values(?,?,?,?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, sNo);
			ps.setString(2, sName);
			ps.setString(3, sSex);
			ps.setInt(4, sage);
			ps.setString(5, cLno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(conn);
		}
		return flag;
	}

	/**
	 *  删除学生信息，返回一个int值表示状态,1：成功，0失败
	 * @param sno
	 * @return
	 */
	public int deleteStudent(String sno) {
		Connection conn = DbUtils.getConnection();
		String sql = "delete from student where sno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, sno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(conn);
		}
		return flag;
	}

	/**
	 *  修改学生信息，返回一个int值表示状态,1：成功，0失败
	 * @param sno
	 * @param after_sno
	 * @param after_sname
	 * @param after_ssex
	 * @param after_sage
	 * @param after_clno
	 * @return
	 */
	public int alterClass(String sno, String afterSno, String afterSname, String afterSsex, int afterSage,
			String afterClno) {
		Connection conn = DbUtils.getConnection();
		String sql = "update student set sno = ?,sname = ?,ssex = ?,sage = ?,clno = ? where sno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, afterSno);
			ps.setString(2, afterSname);
			ps.setString(3, afterSsex);
			ps.setInt(4, afterSage);
			ps.setString(5, afterClno);
			ps.setString(6, sno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(conn);
		}
		return flag;
	}

}
