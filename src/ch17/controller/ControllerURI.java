package ch17.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerURI
 */
@WebServlet(
		urlPatterns = { "*.do" }, 
		initParams = { 
				@WebInitParam(name = "propertyConfig", value = "commandURI.properties")
		})
public class ControllerURI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����
	private Map<String, Object> commandMap = new HashMap<String, Object>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerURI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		//initParams���� propertyConfig�� ���� �о��
		String props = config.getInitParameter("propertyConfig");
		String realFolder = "/property"; // properties ������ ����� ����
		// �� ���ø����̼� �������
		ServletContext context = config.getServletContext();
		//realFolder�� �� ���ø����̼� �ý��ۻ��� ���� ��ѷ� ����
		String realPath = context.getRealPath(realFolder) + "\\"+props;
		
		//��ɾ�� ó�� Ŭ������ ���� ������ ������ Properties ��ü ����
		Properties pr = new Properties();
		FileInputStream f = null;
		
		try {
			//command.properties ������ ������ �о��
			f = new FileInputStream(realPath);
			//command.properties�� ������ Properties��ü pr�� ����
			pr.load(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//command.properties ������ ������ �о��
			if(f != null) try {
				f.close();
			}catch(IOException ex) {
				
			}
			// Set ��ü�� iterator() �޼ҵ带 ����� iterator ��ü�� ��
			Iterator<?> keyIter = pr.keySet().iterator();
			//iterator ��ü�� ����� ��ɾ�� ó�� Ŭ������ commandMap�� ����
			while(keyIter.hasNext()) {
				String command = (String)keyIter.next();
				String className = pr.getProperty(command);
				
				try {
					Class<?> commandClass = Class.forName(className);
					Object commandInstance = commandClass.newInstance();
					commandMap.put(command, commandInstance);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					throw new ServletException(e);
				}catch (InstantiationException e) {
					// TODO Auto-generated catch block
					throw new ServletException(e);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		reqeusetPro(request,response); //�� ������ ��û ó�� �޼ҵ� ȣ��
	}

	private void reqeusetPro(HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException {
		// TODO Auto-generated method stub
		String view = null;
		CommandProcess com = null;
		try {
		String command = request.getRequestURI();
		if(command.indexOf(request.getContextPath()) == 0)
			command = command.substring(request.getContextPath().length());
		com = (CommandProcess)commandMap.get(command);
		if(com == null) {view="/ch17/process.jsp";}
		else {view = com.requestPro(request, response);}
		view = com.requestPro(request, response); // ���� �ּҰ�����
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		RequestDispatcher dispathcher = request.getRequestDispatcher(view);
		dispathcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
