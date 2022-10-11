package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BoardService;
import service.impl.BoardServiceProc;


@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static BoardService service=new BoardServiceProc();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().print("<h1>board list page</h1>");
		String uri=request.getRequestURI();
		System.out.println(uri);

		String root=request.getContextPath();
		String path=null;
		if(uri.equals(root+"/board/list")) {
			path=service.selectList(request, response);
		}else if(uri.equals(root+"/board/write")) {
			path="/WEB-INF/views/board/write.jsp";
		}else if(uri.equals(root+"/board/proc")) {
			service.save(request, response);
		}else if(uri.equals(root+"/board/detail")) {
			path=service.detail(request, response);
		}else if(uri.equals(root+"/board/update")) {
			path=service.update(request, response);
		}else if(uri.equals(root+"/board/delete")) {
			path=service.delete(request, response);
		}
		//End if()
		if(path!=null)
			request.getRequestDispatcher(path).forward(request, response);
	}//End doGet()
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}