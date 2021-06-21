package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.*;

import model.User;
import utils.DbUtils;
/**
 * 
 * @author Harvey
 *
 */
public class UserDao {
	/**
	 *  判断用户在数据库中是否存在，存在返回true，不存在返回false
	 * @param username
	 * @return
	 */
	public boolean userIsExist(String username) {
		Connection conn = DbUtils.getConnection();
		String sql = "select * from user where username = ?";
		try {
			/**
			 *  获取PreparedStatement对象
			 */
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);
			/**
			 *  给用户对象属性赋值
			 */
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				/**
				 *  数据库中存在此用户
				 */
				return true;
			}
			/**
			 *  释放资源
			 */
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(conn);
		}
		return false;
	}

	/**
	 *  用户登录，登录成功返回一个含值User对象,如果登录失败返回一个User空对象
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password) {
		Connection conn = DbUtils.getConnection();
		User user = null;
		String sql = "select * from user where username = ? and password = ?;";

		try {
			/**
			 *  获取PreparedStatement对象
			 */
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			/**
			 *  对sql参数进行动态赋值
			 */
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			/**
			 * 查询结果集
			 *  判断数据库中是否存在该用户
			 */
			if (rs.next()) {
				user = new User();
				/**
				 *  实例化一个user对象
				 *  给用户对象赋值
				 */ 
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setLevel(rs.getString("level"));
			}
			/**
			 *  释放资源
			 */
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(conn);
		}
		return user;
	}

	/**
	 *  用户注册，注册成功返回一个含值User对象，如果注册失败返回一个User空对象
	 * @param username
	 * @param password
	 * @param level
	 * @return
	 */
	public User register(String username, String password, String level) {
		Connection conn = DbUtils.getConnection();
		User user = null;

		try {
			/**
			 *  判断数据库中是否存在该用户
			 */
			if (!userIsExist(username)) {
				/**
				 *  不存在该用户，可以注册
				 */
				user = new User();
				/**
				 *  实例化一个user对象
				 *  给用户对象赋值
				 */
				user.setUsername(username);
				user.setPassword(password);
				user.setLevel(level);
				/**
				 *  将用户对象写入数据库
				 */
				Statement stmt = (Statement) conn.createStatement();
				stmt.executeUpdate("insert into user values('" + username + "','" + password + "','" + level + "');");
				stmt.close();
				/**
				 *  释放资源
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(conn);
		}
		return user;
	}

	/**
	 *  获取用户的权限级别，若存在则返回级别(管理员，普通用户),若不存在返回空
	 * @param username
	 * @return
	 */
	public String level(String username) {
		Connection conn = DbUtils.getConnection();
		String sql = "select level from user where username = ?;";
		String level = null;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				/**
				 *  存在该用户
				 */
				level = rs.getString("level");
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
		return level;
	}

	/**
	 *  获取数据库中所有用户的信息，用ArrayList返回
	 * @return
	 */
	public ArrayList<User> queryAllUser() {
		Connection conn = DbUtils.getConnection();
		String sql = "select * from user order by username;";
		ArrayList<User> results = new ArrayList<User>();

		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User temp = new User();
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
				temp.setLevel(rs.getString("level"));
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
	 *  插入用户信息，返回一个int值表示状态,1：成功，0失败
	 * @param username
	 * @param password
	 * @param level
	 * @return
	 */
	public int insertUser(String username, String password, String level) {
		Connection conn = DbUtils.getConnection();
		String sql = "insert into user values(?,?,?);";

		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, level);
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
	 *  删除用户信息，返回一个int值表示状态,1：成功，0失败
	 * @param username
	 * @return
	 */
	public int deleteUser(String username) {
		Connection conn = DbUtils.getConnection();
		String sql = "delete from user where username = ?;";

		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);
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
	 *  修改用户信息，返回一个int值表示状态,1：成功，0失败
	 * @param username
	 * @param after_username
	 * @param after_password
	 * @param after_level
	 * @return
	 */
	public int alterUser(String username, String afterUsername, String afterPassword, String afterLevel) {
		Connection conn = DbUtils.getConnection();
		String sql = "update user set username = ?,password = ?,level = ? where username = ?;";

		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, afterUsername);
			ps.setString(2, afterPassword);
			ps.setString(3, afterLevel);
			ps.setString(4, username);
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
