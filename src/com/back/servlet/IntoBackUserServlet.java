package com.back.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IPermissionBiz;
import com.back.biz.impl.PermissionBizImpl;
import com.back.po.Permissions;

@SuppressWarnings("serial")
public class IntoBackUserServlet extends HttpServlet {

	public IntoBackUserServlet() {
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
		IPermissionBiz perBiz=new PermissionBizImpl();
		HttpSession session=request.getSession();
		List<Permissions> lstPer=new ArrayList<Permissions>();
		lstPer=perBiz.SelectAll();
		session.setAttribute("PermissionAll",lstPer);
		response.sendRedirect("addadmins.jsp");
		
	}
	public void init() throws ServletException {
		// Put your code here
	}
}
