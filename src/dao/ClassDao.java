package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.*;
import utils.DbUtils;
import model.Class;
/**
 * 
 * @author Harvey
 *
 */
public class ClassDao {
	/**
	 *  获取所有班级的信息，用ArrayList返回
	 * @return
	 */
	public ArrayList<Class> queryAllClass() {
		Connection conn = DbUtils.getConnection();
		String sql = "select * from class order by clno;";
		ArrayList<Class> results = new ArrayList<Class>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Class temp = new Class();
				temp.setClno(rs.getString("clno"));
				temp.setClname(rs.getString("clname"));
				temp.setDno(rs.getString("dno"));
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
	 *  插入班级信息，返回一个int值表示状态,1：成功，0失败
	 * @param clno
	 * @param clname
	 * @param dno
	 * @return
	 */
	public int insertClass(String clno, String clname, String dno) {
		Connection conn = DbUtils.getConnection();
		String sql = "insert into class values(?,?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, clno);
			ps.setString(2, clname);
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

	/**
	 *  删除班级信息，返回一个int值表示状态,1：成功，0失败
	 * @param clno
	 * @return
	 */
	public int deleteClass(String clno) {
		Connection conn = DbUtils.getConnection();
		String sql = "delete from class where clno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, clno);
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
	 *  修改班级信息，返回一个int值表示状态,1：成功，0失败
	 * @param clno
	 * @param after_clno
	 * @param after_clname
	 * @param after_dno
	 * @return
	 */
	public int alterClass(String clno, String afterClno, String afterClname, String afterDno) {
		Connection conn = DbUtils.getConnection();
		String sql = "update class set clno = ?,clname = ?,dno = ? where clno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, afterClno);
			ps.setString(2, afterClname);
			ps.setString(3, afterDno);
			ps.setString(4, clno);
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
