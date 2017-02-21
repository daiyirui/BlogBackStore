package com.back.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IAdminBiz;
import com.back.biz.impl.AdminBizImpl;
import com.back.po.Admins;

@SuppressWarnings("serial")
public class AdminLoginServlet extends HttpServlet {

	public AdminLoginServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("gbk");
		String aname=request.getParameter("aname");
		String apwd=request.getParameter("apwd");
		HttpSession session=request.getSession();
		IAdminBiz adminBiz=new AdminBizImpl();
		Admins admin=new Admins();
		admin=adminBiz.LoginBackStrone(aname, apwd);
		if(admin!=null){
			session.setAttribute("admins",admin);
			response.sendRedirect("index.jsp");
		}else{
			response.getWriter().printf("<script>alert('”√ªßªÚ√‹¬Î¥ÌŒÛ!');location.href='addadmins.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
