package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {

	String selectList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	String detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	String update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}