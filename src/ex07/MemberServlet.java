package ex07;

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

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stud

		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		response.getWriter().print(login(userid, password));
	}

	// 1 = 성공 , 0 = 비번틀림 , -1=아이디 없음
	private int login(String userid, String password) {
		int result = 0;
		// DBCP를 이용해 사용자의 id를 이용해서 검색
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet set = null;
		String query = "select * from member where userid = ?";
		int signal = 0;

		try {
			pstm = this.getConnection().prepareStatement(query);
			pstm.setString(1, userid);
			set = pstm.executeQuery();
			if (set.next()) {
				String dbpassword = set.getString("password");
				if (dbpassword.equals(password))
					signal = 1;
				else
					signal = 0;
			} else {
				signal = -1;
			}

		} catch (Exception e) {

		}finally {
			try {
				if(pstm != null)
					pstm.close();
				if(set != null)
					set.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return signal;
	}

	private Connection getConnection() {
		// TODO Auto-generated method stub

		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			con = (Connection) ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

}