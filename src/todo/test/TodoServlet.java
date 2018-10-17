package todo.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.websocket.Session;

import todo.TodoBean.TodoBean;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/TodoServlet")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.removeAttribute("todoList");
		session.setAttribute("todoList", this.getList());
		
		request.getRequestDispatcher("hohoTest/todo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String detail = request.getParameter("detail");
		this.add(detail);
		HttpSession session = request.getSession();
		session.removeAttribute("todoList");
		session.setAttribute("todoList", this.getList());

		request.getRequestDispatcher("hohoTest/todo.jsp").forward(request, response);

	}
	
	private void add(String detail) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			String query = "insert into todo (detail) values(?)";
			pstm = this.getConnection().prepareStatement(query);
			pstm.setString(1, detail);
			int n = pstm.executeUpdate();
			if (n >= 1) {
				System.out.println("success");
			} else {
				System.out.println("fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	private ArrayList<TodoBean> getList() {
		ArrayList<TodoBean> list = null;
		PreparedStatement pstm = null;
		ResultSet set = null;
		Session session = null;
		Connection con = null;
		TodoBean obj = null;

		int _id;
		String detail;
		boolean done;
		try {
			String query = "select * from todo";
			pstm = this.getConnection().prepareStatement(query);
			list = new ArrayList<TodoBean>();
			set = pstm.executeQuery();
			while (set.next()) {
				obj = new TodoBean();
				obj.setId(set.getInt("_id"));
				obj.setDetail(set.getString("detail"));
				obj.setDone(set.getBoolean("done"));
				list.add(obj);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstm != null)
					pstm.close();
				if(set != null)
					set.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return list;
	}

	private Connection getConnection() {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

}
