package com.back.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.dao.IAdminDao;
import com.back.dao.impl.AdminDaoImpl;
import com.back.po.Admins;

public class AdminServlet extends HttpServlet {
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
		//实现换一换功能
		 String action = request.getParameter("action");
		 if("login".equals(action)) {
			 login(request,response);
			//个人基本资料的修改
		 }else if("listAdmins".equals(action)) {
			 listAdmins(request,response);
			 //跳转到个人信息修改页面
		 }else if("modifyInfoPre".equals(action)){
			 modifyInfoPre(request,response);
			 //修改管理员信息
		 }else if("modifyInfo".equals(action)) {
			 modifyInfo(request,response);
			 //增加管理员
		 }else if("addAdmin".equals(action)) {
			 addAdmin(request,response);
			 //跳转增加管理员界面
		 }else if("addAdminPre".equals(action)){
			 addAdminPre(request,response);
			 //删除管理员操作
		 }else if("deleteAdmin".equals(action)) {
			 deleteAdmin(request,response);
		 }
		 
	}
	
	/**
	 * 删除管理员操作
	 * @param request
	 * @param response
	 */
	private void deleteAdmin(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	/**
	 * 跳转增加管理员界面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addAdminPre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addadmins.jsp").forward(request, response);
	}
	/**
	 * 增加管理员
	 * @param request
	 * @param response
	 */
	private void addAdmin(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	/**
	 * 修改管理员自己的信息
	 * @param request
	 * @param response
	 */
	private void modifyInfo(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	/**
	 * 跳转到修改管理员信息界面
	 * @param request
	 * @param response
	 */
	private void modifyInfoPre(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
	private void listAdmins(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IAdminDao admindao = new AdminDaoImpl();
		List<Admins> AdminsList = admindao.AdminsList();
		System.out.println(AdminsList);
		request.setAttribute("AdminsList",AdminsList);
		request.getRequestDispatcher("./show_admins.jsp").forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aname=request.getParameter("aname");
		String apwd=request.getParameter("apwd");
		IAdminDao admindao=new AdminDaoImpl();
		Admins admin=admindao.LoginBackStrone(aname, apwd);
	
		if(admin != null) {
			HttpSession session=request.getSession();
			session.setAttribute("admins",admin);
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("./login.jsp").forward(request, response);
		}
	}
}
