package fun.login;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction extends HttpServlet{
	LoginDao dao=new LoginDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String destination = (String) session.getAttribute("destination");
		ServletContext context = req.getServletContext();
		String path = context.getContextPath();
		System.out.println("in LoginAciton doget");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		Boolean flag = dao.queryPassWord(username, password);
		String rePath=((!(null==destination))||("".equals(destination)))?destination:path+"/index.jsp";
		if(flag){
			System.out.println("path+rePath:"+path+rePath);
			resp.sendRedirect(path+rePath);
		}else{
			resp.sendRedirect(path+"/error.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in LoginAciton dopost");

		super.doPost(req, resp);
	}
}
