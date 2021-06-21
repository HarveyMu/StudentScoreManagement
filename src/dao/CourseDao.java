package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.*;

import model.Course;
import model.CourseAvg;
import model.CourseFailRate;
import model.CourseRanking;
import utils.DbUtils;
/**
 * 
 * @author Harvey
 *
 */
public class CourseDao {
	/**
	 *  获取所有课程的信息，用ArrayList返回
	 * @return
	 */
	public ArrayList<Course> queryAllCourse() {
		Connection conn = DbUtils.getConnection();
		String sql = "select * from course order by cno;";
		ArrayList<Course> results = new ArrayList<Course>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course temp = new Course();
				temp.setCno(rs.getString("Cno"));
				temp.setCname(rs.getString("Cname"));
				temp.setCteacher(rs.getString("Cteacher"));
				temp.setCcredit(rs.getInt("Ccredit"));
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
	 *  插入课程信息，返回一个int值表示状态,1：成功，0失败
	 * @param Cno
	 * @param Cname
	 * @param Cteacher
	 * @param Ccredit
	 * @return
	 */
	public int insertCourse(String cNo, String cName, String cTeacher, double cCredit) {
		Connection conn = DbUtils.getConnection();
		String sql = "insert into course values(?,?,?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, cNo);
			ps.setString(2, cName);
			ps.setString(3, cTeacher);
			ps.setDouble(4, cCredit);
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
	 *  删除课程信息，返回一个int值表示状态,1：成功，0失败
	 * @param Cno
	 * @return
	 */
	public int deleteCourse(String cNo) {
		Connection conn = DbUtils.getConnection();
		String sql = "delete from course where Cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, cNo);
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
	 *  修改课程信息，返回一个int值表示状态,1：成功，0失败
	 * @param cno
	 * @param after_cno
	 * @param after_cname
	 * @param after_cteacher
	 * @param after_ccredit
	 * @return
	 */
	public int alterCourse(String cno, String afterCno, String afterCname, String afterCteacher,
			double afterCcredit) {
		Connection conn = DbUtils.getConnection();
		String sql = "update course set cno = ?,cname = ?,cteacher = ?,ccredit = ? where cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, afterCno);
			ps.setString(2, afterCname);
			ps.setString(3, afterCteacher);
			ps.setDouble(4, afterCcredit);
			ps.setString(5, cno);
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
	 *  查询课程平均分信息，返回一个ArrayLst集合
	 * @return
	 */
	public ArrayList<CourseAvg> courseAvg() {
		Connection conn = DbUtils.getConnection();
		String sql = "select sc.cno cno,cname,avg(grade) avg from course,sc where course.cno = sc.cno group by cno order by cno;";
		ResultSet result = null;
		ArrayList<CourseAvg> courseAvg = new ArrayList<CourseAvg>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			result = ps.executeQuery();
			while (result.next()) {
				CourseAvg temp = new CourseAvg();
				temp.setCno(result.getString("Cno"));
				temp.setCname(result.getString("Cname"));
				temp.setAvg(result.getDouble("avg"));
				courseAvg.add(temp);
			}
			ps.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(conn);
		}
		return courseAvg;
	}

	/**
	 *  查询课程不及格率，返回一个ArrayList集合
	 * @return
	 */
	public ArrayList<CourseFailRate> failRate() {
		Connection conn = DbUtils.getConnection();
		String sql = "select cno,(select cname from course where cno = x.cno) cname,cast(100.0*(select count(sno) from sc where grade < 60 and cno = x.cno)/(select count(sno) from sc where cno = x.cno) as decimal(18,2)) rate from sc x group by cno order by cno;";
		ArrayList<CourseFailRate> failRate = new ArrayList<CourseFailRate>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CourseFailRate temp = new CourseFailRate();
				temp.setCno(rs.getString("cno"));
				temp.setCname(rs.getString("cname"));
				temp.setFailRate(rs.getDouble("rate"));
				failRate.add(temp);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(conn);
		}
		return failRate;
	}

	/** 查询课程排名情况,返回一个ArrayList集合
	 * @param cno
	 * @return
	 */
	public ArrayList<CourseRanking> courseRanking(String cno) {
		Connection conn = DbUtils.getConnection();
		String sql = "select student.Sno Sno,Dname,Clname,Sname,Ssex,Sage,Grade from department,class,student,sc where student.sno = sc.sno and class.Clno = student.Clno and department.Dno = class.Dno and cno = '"
				+ cno + "' order by grade desc;";
		ArrayList<CourseRanking> courseRanking = new ArrayList<CourseRanking>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CourseRanking temp = new CourseRanking();
				temp.setSno(rs.getString("Sno"));
				temp.setDname(rs.getString("Dname"));
				temp.setClname(rs.getString("Clname"));
				temp.setSname(rs.getString("Sname"));
				temp.setSsex(rs.getString("Ssex"));
				temp.setSage(rs.getInt("Sage"));
				temp.setGrade(rs.getDouble("Grade"));
				courseRanking.add(temp);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(conn);
		}
		return courseRanking;
	}
}
