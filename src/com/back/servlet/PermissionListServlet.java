package com.back.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IPermissionBiz;
import com.back.biz.impl.PermissionBizImpl;
import com.back.filter.PageBean;

@SuppressWarnings("serial")
public class PermissionListServlet extends HttpServlet {

	public PermissionListServlet() {
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
		HttpSession session=request.getSession();
    	//�����ҳ����
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
    	PageBean pb=new PageBean();
    	IPermissionBiz perBiz=new PermissionBizImpl();
    	pb=perBiz.SelectByPage(nowpage, pagesize);
    	session.setAttribute("permissionPageBean",pb);
    	response.sendRedirect("permissionlist.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
