package com.back.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.dao.IAdminDao;
import com.back.dao.impl.AdminDaoImpl;
import com.back.po.Admins;

@SuppressWarnings("serial")
public class AdminLoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String aname=request.getParameter("aname");
		String apwd=request.getParameter("apwd");
		HttpSession session=request.getSession();
		IAdminDao admindao=new AdminDaoImpl();
		Admins admin=new Admins();
		admin=admindao.LoginBackStrone(aname, apwd);
		if(admin!=null){
			session.setAttribute("admins",admin);
			response.sendRedirect("index.jsp");
		}else{
			response.getWriter().printf("<script>alert('�û����������!');location.href='addadmins.jsp'</script>");
		}
	}

}
