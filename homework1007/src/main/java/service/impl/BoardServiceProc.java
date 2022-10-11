package service.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.board2.Board2DTO;
import mybatis.MybatisConfig;
import service.BoardService;

public class BoardServiceProc implements BoardService {
	private SqlSessionFactory sqlSessionFactory=MybatisConfig.getInstance();
	@Override
	public String selectList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		
		List<Board2DTO> result=sqlSession.selectList("BoardMapper.findAll");
		System.out.println("result 개수 : "+result.size());
		sqlSession.close();
		
		request.setAttribute("list", result);
		
		return "/WEB-INF/views/board/list.jsp";
		
	}

	@Override
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("글쓰기 처리!!!!");
		
		Board2DTO dto=new Board2DTO();
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setCreater(request.getParameter("creater"));
		
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		sqlSession.insert("BoardMapper.save", dto);
		sqlSession.close();
		response.sendRedirect("list");
		
	}

	@Override
	public String detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies=request.getCookies();
		boolean flag=true;
		for(Cookie cookie:cookies) {
			String n=cookie.getName();
			String v=cookie.getValue();
			System.out.println(n +" : "+ v);
			if(n.equals("myCookie")) {
				flag=false;
			}
		}
		
		String ip=request.getRemoteHost();
		System.out.println("ip : "+ip);
		
		
		long bno=Long.parseLong(request.getParameter("bno"));
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//조회결과가 단일행 결과인경우
		Board2DTO result=sqlSession.selectOne("BoardMapper.findById", bno);
		String referer=request.getHeader("Referer");
		System.out.println("Referer(detail이전페이지) : "+referer);
		if(!referer.contains("detail") && flag ) {
			sqlSession.update("BoardMapper.readCountPP", bno);
			sqlSession.commit();
			
			Cookie cookie=new Cookie("myCookie", "쿠키에요");
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(60);
			response.setCharacterEncoding("utf-8");
			response.addCookie(cookie);
		}
		sqlSession.close();
		
		request.setAttribute("detail", result);
		
		return "/WEB-INF/views/board/detail.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long bno=Long.parseLong(request.getParameter("bno"));
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		
		Board2DTO dto=new Board2DTO();
		dto.setBno(bno);
		dto.setSubject(subject);
		dto.setContent(content);
		
		SqlSession sqlSession=sqlSessionFactory.openSession(true);
		sqlSession.update("BoardMapper.update", dto);
	
		sqlSession.close();

		response.sendRedirect("detail?bno="+bno);
		return null;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long bno=Long.parseLong(request.getParameter("bno"));
		SqlSession sqlSession=sqlSessionFactory.openSession(true);
		sqlSession.delete("BoardMapper.deleteById", bno);
		sqlSession.close();
		
		response.sendRedirect("list");
		return null;
	}

}