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

@SuppressWarnings("serial")
public class MastDeleteUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] uidc=request.getParameterValues("chkOrdersnos");
		IUserDao userDao = new UserDaoImpl();
		for(int i = 0;i<uidc.length;i++) {
			userDao.DeleteUser(Integer.parseInt(uidc[i]));
		}
		List<Users> UsersList = userDao.FindAllUsers();
	    request.setAttribute("UsersList",UsersList);
		request.getRequestDispatcher("./show_users.jsp").forward(request, response);
			
	}
}
