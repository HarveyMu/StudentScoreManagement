package dao;

import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Department;
import utils.DbUtils;
/**
 * 
 * @author Harvey
 *
 */
public class DepartmentDao {
	/**
	 *  查询所有的系信息，查询返回一个含值的ArrayList,当为空值的说明表中无数据元组
	 * @return
	 */
	public ArrayList<Department> queryAllDepartment() {
		Connection conn = DbUtils.getConnection();
		String sql = "select * from department order by dno;";
		ArrayList<Department> results = new ArrayList<Department>();

		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Department temp = new Department();
				temp.setDno(rs.getString("Dno"));
				temp.setDname(rs.getString("Dname"));
				results.add(temp);
			}
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
	 *  插入院系信息，返回一个int值表示状态,1：成功，0失败
	 * @param dno
	 * @param dname
	 * @return
	 */
	public int insertDepartment(String dno, String dname) {
		Connection conn = DbUtils.getConnection();
		String sql = "insert into department values(?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, dno);
			ps.setString(2, dname);
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
	 *  删除院系信息，返回一个int值表示状态,1：成功，0失败
	 * @param dno
	 * @return
	 */
	public int deleteDepartment(String dno) {
		Connection conn = DbUtils.getConnection();
		String sql = "delete from department where dno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, dno);
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
	 *  修改院系信息，返回一个int值表示状态,1：成功，0失败
	 * @param dno
	 * @param after_dno
	 * @param after_dname
	 * @return
	 */
	public int alterDepartment(String dno, String afterDno, String afterDname) {
		Connection conn = DbUtils.getConnection();
		String sql = "update department set dno = ?,dname = ? where dno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, afterDno);
			ps.setString(2, afterDname);
			ps.setString(3, dno);
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
