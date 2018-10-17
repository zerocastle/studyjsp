package ch09;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainAction.DeleteDo;
import mainAction.InsertDo;
import mainAction.UpdateDo;

/**
 * Servlet implementation class Insert
 */
@WebServlet("*.do")
public class FrontControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontControl() {
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
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);

	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length());
		String page = "";

		System.out.println("들오온 명령어 : " + command);

		if (command.equals("/ch09/insert.do")) {
			InsertDo obj = new InsertDo();
			page = "insertView.jsp";
			int signal = obj.insertDo(request.getParameter("userid"), request.getParameter("username"),
					request.getParameter("password"));
			if (signal >= 0) {
				System.out.println("insert success");
			} else
				System.out.println("insert fail");
			RequestDispatcher dis = request.getRequestDispatcher(page);
			dis.forward(request, response);

		} else if (command.equals("/ch09/select.do")) {
			page = "selectView.jsp";
//			SelectDo obj = new SelectDo();
			response.sendRedirect("selectView.jsp");
		} else if (command.equals("/ch09/update.do")) {
			String userid = request.getParameter("userid");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int signal = 0;
			UpdateDo obj = new UpdateDo();
			signal = obj.updateMember(userid, username, password);
			if (signal >= 1) {
				System.out.println("updatesuccess");
				page = "updateView.jsp";
				response.sendRedirect(page);
			}else {
				System.out.println("update fail...");
				response.sendRedirect("updateForm.jsp");
			}

		} else if(command.equals("/ch09/delete.do")) {
			DeleteDo obj = new DeleteDo();
			int signal = 0;
			page = "deleteView.jsp";
			signal = obj.deleteMember(request.getParameter("userid") , request.getParameter("password"));
			
			if(signal >= 1) {
				System.out.println("delete success...");
				response.sendRedirect("deleteView.jsp");
			}else {
				System.out.println(page);
				response.sendRedirect("deleteForm.jsp");
			}
		}

	}

}
