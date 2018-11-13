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
	
	//명령어와 명령어 처리 클래스를 쌍으로 저장
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
		
		//initParams에서 propertyConfig의 값을 읽어옴
		String props = config.getInitParameter("propertyConfig");
		String realFolder = "/property"; // properties 파일이 저장된 폴더
		// 웹 어플리케이션 루투경로
		ServletContext context = config.getServletContext();
		//realFolder를 웹 애플리케이션 시스템상의 절대 경롤로 변경
		String realPath = context.getRealPath(realFolder) + "\\"+props;
		
		//명령어와 처리 클래스의 매핑 정보를 저장할 Properties 객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		
		try {
			//command.properties 파일의 내용을 읽어옴
			f = new FileInputStream(realPath);
			//command.properties의 내용을 Properties객체 pr에 저장
			pr.load(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//command.properties 파일의 내용을 읽어옴
			if(f != null) try {
				f.close();
			}catch(IOException ex) {
				
			}
			// Set 객체의 iterator() 메소드를 사용해 iterator 객체를 얻어냄
			Iterator<?> keyIter = pr.keySet().iterator();
			//iterator 객체에 저장된 명령어와 처리 클래스를 commandMap에 저장
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
		reqeusetPro(request,response); //웹 브라우저 요청 처리 메소드 호출
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
		view = com.requestPro(request, response); // 없는 주소값에서
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
