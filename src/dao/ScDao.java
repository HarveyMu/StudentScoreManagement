package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.*;

import model.Sc;
import utils.DbUtils;
/**
 * 
 * @author Harvey
 *
 */
public class ScDao {

	/**
	 *  获取所有成绩记录的信息，用ArrayList返回
	 * @return
	 */
	public ArrayList<Sc> queryAllSc() {
		Connection conn = DbUtils.getConnection();
		String sql = "select student.sno sno,sname,ssex,sage,course.cno,cname,grade from sc,student,course where sc.sno = student.sno and course.cno = sc.cno order by sno;";
		ArrayList<Sc> results = new ArrayList<Sc>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sc temp = new Sc();
				temp.setSno(rs.getString("sno"));
				temp.setSname(rs.getString("sname"));
				temp.setSsex(rs.getString("ssex"));
				temp.setSage(rs.getInt("sage"));
				temp.setCno(rs.getString("cno"));
				temp.setCname(rs.getString("cname"));
				temp.setGrade(rs.getDouble("grade"));
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
	 *  插入成绩信息，返回一个int值表示状态,1：成功，0失败
	 * @param sNo
	 * @param cNo
	 * @param grade
	 * @return
	 */
	public int insertSc(String sNo, String cNo, double grade) {
		Connection conn = DbUtils.getConnection();
		String sql = "insert into sc values(?,?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, sNo);
			ps.setString(2, cNo);
			ps.setDouble(3, grade);
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
	 *  删除成绩记录，返回一个int值表示状态,1：成功，0失败
	 * @param sNo
	 * @param cNo
	 * @return
	 */
	public int deleteSc(String sNo, String cNo) {
		Connection conn = DbUtils.getConnection();
		String sql = "delete from sc where sno = ? and cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, sNo);
			ps.setString(2, cNo);
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
	 *  修改成绩信息，返回一个int值表示状态,1：成功，0失败
	 * @param sNo
	 * @param cNo
	 * @param afterGrade
	 * @return
	 */
	public int alterSc(String sNo, String cNo, double afterGrade) {
		Connection conn = DbUtils.getConnection();
		String sql = "update sc set grade = ? where sno = ? and cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setDouble(1, afterGrade);
			ps.setString(2, sNo);
			ps.setString(3, cNo);
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
