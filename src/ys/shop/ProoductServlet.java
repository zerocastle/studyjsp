package ys.shop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class ProoductServlet
 */
@WebServlet("/product")
public class ProoductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProoductServlet() {
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
//		RequestDispatcher dis = request.getRequestDispatcher("ProductEx/produc.jsp");
//		dis.forward(request, response);
		HttpSession session = request.getSession();

		session.setAttribute("productList", getList());
		request.getRequestDispatcher("productEx/product.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void add() {
		// TODO Auto-generated method stub

	}

	private ArrayList<Product> getList() {
		// TODO Auto-generated method stub
		ArrayList<Product> list = null;
		PreparedStatement pstm = null;
		ResultSet set = null;
		Product product = null;

		try {
			String query = "select * from product";
			pstm = this.getConnection().prepareStatement(query);
			list = new ArrayList<Product>();
			set = pstm.executeQuery();

			while (set.next()) {
				product = new Product();
				product.setId(set.getInt("id"));
				product.setName(set.getString("name"));
				product.setInfo(set.getString("info"));
				product.setPrice(set.getInt("price"));
				list.add(product);
			}

		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (set != null)
					set.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return list;
	}

	private Connection getConnection() {
		Connection con = null;

		try {
			Context initCtx = (Context) new InitialContext().lookup("java:comp/env");
			DataSource ds = (DataSource) initCtx.lookup("jdbc/TestDB");
			try {
				con = ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}

}
