package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Secretary;
import bean.Student;
import bean.Teacher;
import dao.SecretaryDao;
import dao.StudentDao;
import dao.TeacherDao;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charest=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		if (type.equals("student")) {
			StudentDao studentDao = new StudentDao();
			if (studentDao.Login(username, password)) {
				request.setCharacterEncoding("utf-8");
				int id = new StudentDao().Login(username);
				Student stu = new StudentDao().Find(id);
				request.setAttribute("id", stu.getId());
				request.setAttribute("name", stu.getName());
				rd = request.getRequestDispatcher("/student.jsp");
			}
		}else if (type.equals("teacher")) {
			TeacherDao teacherDao = new TeacherDao();
			if (teacherDao.Login(username, password)) {
				request.setCharacterEncoding("utf-8");
				int id = new TeacherDao().Login(username);
				Teacher tea = new TeacherDao().Find(id);
				request.setAttribute("id", tea.getId());
				request.setAttribute("name", tea.getName());
				rd = request.getRequestDispatcher("/teacher.jsp");
			}
		}else {
			SecretaryDao secretaryDao = new SecretaryDao();
			if (secretaryDao.Login(username, password)) {
				request.setCharacterEncoding("utf-8");
				int id = new SecretaryDao().Login(username);
				Secretary sec = new SecretaryDao().Find(id);
				request.setAttribute("id", sec.getId());
				request.setAttribute("name", sec.getName());
				rd = request.getRequestDispatcher("/secretary.jsp");
			}
		}
		rd.forward(request, response);
	}

}
