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

public class AdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        this.doPost(request, response);
 	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//实现换一换功能
		 String action = request.getParameter("action");
		 if("login".equals(action)) {
			 
			 login(request,response);
			//个人基本资料的修改
		 }else if("modifyAdminInfo".equals(action)) {
			 modifyUserInfo(request,response);
			 //跳转到个人信息修改页面
		 }
	}
	
	private void modifyUserInfo(HttpServletRequest request,HttpServletResponse response) {
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aname=request.getParameter("aname");
		String apwd=request.getParameter("apwd");
		IAdminDao admindao=new AdminDaoImpl();
		Admins admin=new Admins();
		admin=admindao.LoginBackStrone(aname, apwd);
		if(admin != null) {
			HttpSession session=request.getSession();
			session.setAttribute("admins",admin);
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("./login.jsp").forward(request, response);
		}
	}
}
