package fun.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy....");
	}

	@Override
	public void doFilter(ServletRequest reqs, ServletResponse respo,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("doFilter....");
		HttpServletRequest req =(HttpServletRequest)reqs;
		HttpServletResponse resp =(HttpServletResponse)respo;
		ServletContext context = req.getServletContext();
		HttpSession session = req.getSession();
		String username=(String)session.getAttribute("username");
		System.out.println("Filter username:"+username);
		if(!"".equals(username)&&!(null==username)){
			chain.doFilter(req, resp);
		}else{
			resp.sendRedirect(context.getContextPath()+"/login.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Filter init....");
	}

}
