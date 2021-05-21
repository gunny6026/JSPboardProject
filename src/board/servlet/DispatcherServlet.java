package board.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.command.BCommand;
import board.command.BDeleteCommand;
import board.command.BModifyCommand;
import board.command.ContentCommand;
import board.command.JoinCommand;
import board.command.ListCommand;
import board.command.WriteCommand;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DispatcherServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		System.out.println("doget 호출");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
		System.out.println("dopost 호출");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		
		
	}
	
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doHandle 응답");
		
		request.setCharacterEncoding("utf-8");
		
		String viewpage = null; //어떤 페이지를 보여줄건지 확인하는 값
		BCommand bcommand = null; //dao에 작업을 전달하는??
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
			
		Connection conn = null;		
		Statement stmt = null;
		ResultSet rs = null;		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
		}catch (Exception e) {
		}
		
		if(com.equals("/list.do")) {
			bcommand = new ListCommand();
			bcommand.execute(request, response);
			viewpage = "board/BoardList.jsp";
			
		}else if(com.equals("/write.do")) {
			bcommand = new WriteCommand();
			bcommand.execute(request, response);
			viewpage = "list.do";
		}else if(com.equals("/content_view.do")) {
			bcommand = new ContentCommand();
			bcommand.execute(request, response);
			viewpage ="content_view.jsp";
		}else if(com.equals("/modify.do")) {
			bcommand = new BModifyCommand();
			bcommand.execute(request, response);
			viewpage ="content_view.do";
		}else if(com.equals("/delete.do")) {
			bcommand = new BDeleteCommand();
			bcommand.execute(request, response);
			viewpage = "list.do";
		}else if(com.equals("/authentication.do")){
			bcommand = new JoinCommand();
			bcommand.execute(request, response);
			viewpage = "delete.do";
		}else if(com.equals("/user/join.do")) {
			
		}else if(com.equals("/user/login.do")) {
			System.out.println("호출");
			
		}		
		RequestDispatcher dis = request.getRequestDispatcher(viewpage);
		dis.forward(request, response);
	
	}

}