package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDao;
import dao.CourseDao;
import dao.DepartmentDao;
import dao.ScDao;
import dao.StudentDao;
import dao.UserDao;
import model.Class;
import model.Course;
import model.CourseAvg;
import model.CourseFailRate;
import model.CourseRanking;
import model.Department;
import model.Sc;
import model.Student;
import model.User;
/**
 * 
 * @author Harvey
 *
 */
public class AdminDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String action;
	/**
	 *  存储操作描述
	 *  接收请求
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		action = request.getParameter("action");
		/**
		 *  判断所执行操作
		 */
		switch (action) {
		/**
		 *  用户操作
		 */
		case "query_all_user":
			queryAllUser(request, response);
			break;
		case "insert_user":
			insertUser(request, response);
			break;
		case "delete_user":
			deleteUser(request, response);
			break;
		case "alter_user":
			alterUser(request, response);
			break;
		/**
		 *  院系操作
		 */
		case "query_all_department":
			queryAllDepartment(request, response);
			break;
		case "insert_department":
			alterDepartment(request, response);
			break;
		case "delete_department":
			alterDepartment(request, response);
			break;
		case "alter_department":
			alterDepartment(request, response);
			break;
		/**
		 *  班级操作
		 */
		case "query_all_class":
			queryAllClass(request, response);
			break;
		case "insert_class":
			insertClass(request, response);
			break;
		case "delete_class":
			deleteClass(request, response);
			break;
		case "alter_class":
			alterClass(request, response);
			break;
		/**
		 *  学生操作
		 */
		case "query_all_student":
			queryAllStudent(request, response);
			break;
		case "insert_student":
			insertStudent(request, response);
			break;
		case "delete_student":
			deleteStudent(request, response);
			break;
		case "alter_student":
			alterStudent(request, response);
			break;
		/**
		 *  课程操作
		 */
		case "course_avg":
			courseAvg(request, response);	break;
		case "fail_rate":
			failRate(request, response);break;
		case "course_ranking":
			courseRanking(request, response);break;		
		case "query_all_course":
			queryAllCourse(request, response);	break;
		case "insert_course":
			insertCourse(request, response);	break;
		case "delete_course":
			deleteCourse(request, response);break;		
		case "alter_course":
			alterCourse(request, response);break;	
		/**
		 *  成绩操作
		 */
		case "query_all_sc":
			queryAllSc(request, response);	break;
		case "insert_sc":
			insertSc(request, response);break;
		case "delete_sc":
			deleteSc(request, response);			break;
		case "alter_sc":
			alterSc(request, response);break;
		default:break;	
		}
	}

	/*-------------------------------- 用户 -----------------------------------*/
	/**
	 *  查询所有用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		UserDao userDao = new UserDao();

		ArrayList<User> results = userDao.queryAllUser();
		PrintWriter out = response.getWriter();
		/**
		 *  输出结果
		 */
		if (results != null) {
			out.write("<div class='all'>");
			out.write("<div><span>用户名</span><span>密码</span><span>权限级别</span></div>");
			for (User i : results) {
				out.write("<div>");
				out.write("<span>" + i.getUsername() + "</span>");
				out.write("<span>" + i.getPassword() + "</span>");
				out.write("<span>" + i.getLevel() + "</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}

		out.flush();
		out.close();
	}

	/**
	 *  插入用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String level = request.getParameter("level");

		int flag = new UserDao().insertUser(username, password, level);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "用户插入成功！";
		} else {
			info = "错误：用户插入失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  删除用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");

		int flag = new UserDao().deleteUser(username);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "成功删除名为" + username + "用户！";
		} else {
			info = "错误：删除用户失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  修改用户
	 */
	protected void alterUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String afterUsername = request.getParameter("after_username");
		String afterPassword = request.getParameter("after_password");
		String afterLevel = request.getParameter("after_level");

		int flag = new UserDao().alterUser(username, afterUsername, afterPassword, afterLevel);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "名为" + username + "用户信息修改成功！";
		} else {
			info = "错误：修改用户失败!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/*-------------------------------- 院系-----------------------------------*/
	/**
	 *  查询所有院系
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryAllDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		ArrayList<Department> results = new DepartmentDao().queryAllDepartment();
		PrintWriter out = response.getWriter();
		/**
		 *  输出结果
		 */
		if (results != null) {
			out.write("<div class='all'>");
			out.write("<div><span>系编号</span><span>系名</span></div>");
			for (Department i : results) {
				out.write("<div>");
				out.write("<span>" + i.getDno() + "</span>");
				out.write("<span>" + i.getDname() + "</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	/**
	 *  插入院系
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void insertDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String dno = request.getParameter("dno");
		String dname = request.getParameter("dname");
		int flag = new DepartmentDao().insertDepartment(dno, dname);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "院系" + dname + "插入成功！";
		} else {
			info = "错误：院系插入失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  删除院系
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String dno = request.getParameter("dno");
		int flag = new DepartmentDao().deleteDepartment(dno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "成功删除" + dno + "号院系！";
		} else {
			info = "错误：删除院系失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  修改院系
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void alterDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String dno = request.getParameter("dno");
		String afterDno = request.getParameter("after_dno");
		String afterDname = request.getParameter("after_dname");
		int flag = new DepartmentDao().alterDepartment(dno, afterDno, afterDname);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = dno + "号系修改成功！";
		} else {
			info = "错误：修改院系失败!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/*-------------------------------- 班级-----------------------------------*/
	/**
	 *  查询所有班级
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryAllClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<Class> results = new ClassDao().queryAllClass();
		PrintWriter out = response.getWriter();
		// 输出结果
		if (results != null) {
			out.write("<div class='all'>");
			out.write("<div><span>班级编号</span><span>班级名</span><span>所属院系</span></div>");
			for (Class i : results) {
				out.write("<div>");
				out.write("<span>" + i.getClno() + "</span>");
				out.write("<span>" + i.getClname() + "</span>");
				out.write("<span>" + i.getDno() + "</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	/**
	 *  插入班级
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void insertClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String clno = request.getParameter("clno");
		String clname = request.getParameter("clname");
		String dno = request.getParameter("dno");
		int flag = new ClassDao().insertClass(clno, clname, dno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "班级" + clname + "插入成功！";
		} else {
			info = "错误：班级插入失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  删除班级
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String clno = request.getParameter("clno");
		int flag = new ClassDao().deleteClass(clno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "成功删除" + clno + "班级！";
		} else {
			info = "错误：删除班级失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  修改班级
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void alterClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String clno = request.getParameter("clno");
		String afterClno = request.getParameter("after_clno");
		String afterClname = request.getParameter("after_clname");
		String afterDno = request.getParameter("after_dno");
		int flag = new ClassDao().alterClass(clno, afterClno, afterClname, afterDno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "班级" + clno + "修改成功！";
		} else {
			info = "错误：修改班级失败!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/*-------------------------------- 学生-----------------------------------*/
	/**
	 *  查询所有学生
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryAllStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<Student> results = new StudentDao().queryAllStudent();
		PrintWriter out = response.getWriter();
		/**
		 *  输出结果
		 */
		if (results != null) {
			out.write("<div class='all'>");
			out.write("<div><span>学号</span><span>姓名</span><span>性别</span><span>年龄</span><span>所在班级编号</span></div>");
			for (Student i : results) {
				out.write("<div>");
				out.write("<span>" + i.getSno() + "</span>");
				out.write("<span>" + i.getSname() + "</span>");
				out.write("<span>" + i.getSsex() + "</span>");
				out.write("<span>" + i.getSage() + "</span>");
				out.write("<span>" + i.getClno() + "</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	/**
	 *  插入学生
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void insertStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");
		String ssex = request.getParameter("ssex");
		int sage = Integer.parseInt(request.getParameter("sage"));
		String clno = request.getParameter("clno");
		int flag = new StudentDao().insertStudent(sno, sname, ssex, sage, clno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "学生" + sname + "插入成功！";
		} else {
			info = "错误：学生插入失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  删除学生
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		int flag = new StudentDao().deleteStudent(sno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "成功删除" + sno + "号学生！";
		} else {
			info = "错误：删除学生失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  修改学生信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void alterStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		String afterSno = request.getParameter("after_sno");
		String afterSname = request.getParameter("after_sname");
		String afterSsex = request.getParameter("after_ssex");
		int afterSage = Integer.parseInt(request.getParameter("after_sage"));
		String afterClno = request.getParameter("after_clno");
		int flag = new StudentDao().alterClass(sno, afterSno, afterSname, afterSsex, afterSage, afterClno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "学生" + sno + "信息修改成功！";
		} else {
			info = "错误：修改学生信息失败!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/*-------------------------------- 课程 -----------------------------------*/
	/**
	 *  查询课程平均分
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void courseAvg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		ArrayList<CourseAvg> results = new CourseDao().courseAvg();
		PrintWriter out = response.getWriter();
		if (results != null) {
			// 输出结果
			if (results != null) {
				out.write("<div class='all'>");
				out.write("<div><span>课程号</span><span>课程名称</span><span>平均分</span></div>");
				for (CourseAvg i : results) {
					out.write("<div>");
					out.write("<span>" + i.getCno() + "</span>");
					out.write("<span>" + i.getCname() + "</span>");
					out.write("<span>" + i.getAvg() + "</span>");
					out.write("</div>");
				}
				out.write("</div>");
			}
		}
		out.flush();
		out.close();
	}

	/**
	 *  查询课程不及格率
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void failRate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<CourseFailRate> results = new CourseDao().failRate();
		PrintWriter out = response.getWriter();
		/**
		 *  输出结果
		 */
		if (results != null) {
			out.write("<div class='all'>");
			out.write("<div><span>课程号</span><span>课程名称</span><span>不及格率</span></div>");
			for (CourseFailRate i : results) {
				out.write("<div>");
				out.write("<span>" + i.getCno() + "</span>");
				out.write("<span>" + i.getCname() + "</span>");
				out.write("<span>" + i.getFailRate() + "%</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	/**
	 *  查询课程排名
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void courseRanking(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String cNo = request.getParameter("cno");
		ArrayList<CourseRanking> results = new CourseDao().courseRanking(cNo);
		PrintWriter out = response.getWriter();
		/**
		 *  输出结果
		 */
		if (results != null) {
			out.write("<div class='all'>");
			out.write(
					"<div><span>学号</span><span>所在系</span><span>班级名称</span><span>姓名</span><span>性别</span><span>年龄</span><span>成绩</span><span>排名</span></div>");
			int no = 1;
			for (CourseRanking i : results) {
				out.write("<div>");
				out.write("<span>" + i.getSno() + "</span>");
				out.write("<span>" + i.getDname() + "</span>");
				out.write("<span>" + i.getClname() + "</span>");
				out.write("<span>" + i.getSname() + "</span>");
				out.write("<span>" + i.getSsex() + "</span>");
				out.write("<span>" + i.getSage() + "</span>");
				out.write("<span>" + i.getGrade() + "</span>");
				out.write("<span>" + (no++) + "</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	/**
	 *  查询所有课程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryAllCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<Course> results = new CourseDao().queryAllCourse();
		PrintWriter out = response.getWriter();
		if (results != null) {
			/**
			 *  输出结果
			 */
			if (results != null) {
				out.write("<div class='all'>");
				out.write("<div><span>课程号</span><span>课程名称</span><span>执教老师</span><span>学分</span></div>");
				for (Course i : results) {
					out.write("<div>");
					out.write("<span>" + i.getCno() + "</span>");
					out.write("<span>" + i.getCname() + "</span>");
					out.write("<span>" + i.getCteacher() + "</span>");
					out.write("<span>" + i.getCcredit() + "</span>");
					out.write("</div>");
				}
				out.write("</div>");
			}
		}
		out.flush();
		out.close();
	}

	/**
	 *  插入课程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void insertCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String cNo = request.getParameter("cno");
		String cName = request.getParameter("cname");
		String cTeacher = request.getParameter("cteacher");
		int cCredit = Integer.parseInt(request.getParameter("ccredit"));
		int flag = new CourseDao().insertCourse(cNo, cName, cTeacher, cCredit);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "课程" + cName + "插入成功！";
		} else {
			info = "错误：课程插入失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  删除课程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String cno = request.getParameter("cno");
		int flag = new CourseDao().deleteCourse(cno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "成功删除" + cno + "课程！";
		} else {
			info = "错误：删除课程失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  修改课程信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void alterCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String cno = request.getParameter("cno");
		String afterCno = request.getParameter("after_cno");
		String afterCname = request.getParameter("after_cname");
		String afterCteacher = request.getParameter("after_cteacher");
		double afterCcredit = Double.parseDouble(request.getParameter("after_ccredit"));
		int flag = new CourseDao().alterCourse(cno, afterCno, afterCname, afterCteacher, afterCcredit);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = cno + "号课程修改成功！";
		} else {
			info = "错误：修改课程信息失败!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/*-------------------------------- 成绩-----------------------------------*/
	/**
	 *  查询所有成绩表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryAllSc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<Sc> results = new ScDao().queryAllSc();
		PrintWriter out = response.getWriter();
		/**
		 *  输出结果
		 */
		if (results != null) {
			out.write("<div id='all_sc' class='all'>");
			out.write(
					"<div><span>学号</span><span>姓名</span><span>性别</span><span>年龄</span><span>课程号</span><span>课程名称</span><span>分数</span></div>");
			for (Sc i : results) {
				out.write("<div>");
				out.write("<span>" + i.getSno() + "</span>");
				out.write("<span>" + i.getSname() + "</span>");
				out.write("<span>" + i.getSsex() + "</span>");
				out.write("<span>" + i.getSage() + "</span>");
				out.write("<span>" + i.getCno() + "</span>");
				out.write("<span>" + i.getCname() + "</span>");
				out.write("<span>" + i.getGrade() + "</span>");
				out.write("</div>");
			}
			out.write("</div>");
		}
		out.flush();
		out.close();
	}

	/**
	 *  插入一条成绩记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void insertSc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		String cno = request.getParameter("cno");
		double grade = Double.parseDouble(request.getParameter("grade"));
		int flag = new ScDao().insertSc(sno, cno, grade);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = sno + "号学生" + cno + "号课程成绩" + grade + "插入成功！";
		} else {
			info = "错误：成绩信息插入失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  删除成绩记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteSc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		String cno = request.getParameter("cno");
		int flag = new ScDao().deleteSc(sno, cno);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = "成功删除" + sno + "号学生" + cno + "号课程成绩！";
		} else {
			info = "错误：删除成绩信息失败！";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

	/**
	 *  修改成绩信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void alterSc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String sno = request.getParameter("sno");
		String cno = request.getParameter("cno");
		double afterGrade = Double.parseDouble(request.getParameter("afterGrade"));
		int flag = new ScDao().alterSc(sno, cno, afterGrade);
		String info = null;
		PrintWriter out = response.getWriter();
		if (flag == 1) {
			info = sno + "号学生" + cno + "号课程成绩修改成功！";
		} else {
			info = "错误：修改学生成绩失败!";
		}
		out.write("<div class='error'>");
		out.write("<div>" + info + "</div>");
		out.write("</div>");
		out.flush();
		out.close();
	}

}
