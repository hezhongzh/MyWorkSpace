package fun.detail;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DetailAction extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path=req.getContextPath();
		System.out.println("contextPath:" + path);
		ServletContext context=getServletContext();
		HttpSession session=req.getSession();
		String username=(String)session.getAttribute("username");
		if("".equals(username)||null==username){
			session.setAttribute("destination", "/detail.jsp");
			resp.sendRedirect(path+"/login.jsp");
		}else{
			session.removeAttribute("destination");
			resp.sendRedirect(path+"/detail.jsp");
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return super.getServletContext();
	}
	
}
