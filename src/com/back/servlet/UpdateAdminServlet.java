package com.back.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IAdminBiz;
import com.back.biz.IPermissionBiz;
import com.back.biz.impl.AdminBizImpl;
import com.back.biz.impl.PermissionBizImpl;
import com.back.po.Admins;
import com.back.po.Permissions;

@SuppressWarnings("serial")
public class UpdateAdminServlet extends HttpServlet {

	public UpdateAdminServlet() {
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
		int aid=Integer.parseInt(request.getParameter("aid"));
		HttpSession session=request.getSession();
		IAdminBiz adminBiz=new AdminBizImpl();
		Admins admin=new Admins();
		//1.根据aid获取admins对象信息
		admin=adminBiz.SelectByaid(aid);
		session.setAttribute("AdminDetail", admin);
		//2.获取全部权限信息结合，原因要对此后台用户的权限信息修改比对
		IPermissionBiz perBiz=new PermissionBizImpl();
		List<Permissions> lstPer=perBiz.SelectAll();
		session.setAttribute("PermissionAll",lstPer);
		response.sendRedirect("udpateadmins.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
