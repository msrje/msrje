package config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter extends HttpFilter implements Filter {
       
    
    public EncodingFilter() {
        super();
    }

	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		System.out.println("utf-8 인코딩 완료!");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext application=fConfig.getServletContext();
		String root=application.getContextPath();
		System.out.println("init root:"+root);
		application.setAttribute("root", root);
	}

}