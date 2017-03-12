package com.back.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.back.dao.IUserDao;
import com.back.dao.impl.UserDaoImpl;
import com.back.po.Users;

public class UserServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if("deleteUser".equals(action)) {
			deleteUser(request,response);
		}
	}
	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		IUserDao userDao = new UserDaoImpl();
		
		userDao.DeleteUser(Integer.parseInt(uid));
		List<Users> UsersList = userDao.FindAllUsers();
	    request.setAttribute("UsersList",UsersList);
		request.getRequestDispatcher("./show_users.jsp").forward(request, response);
	}
}
