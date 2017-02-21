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
public class DeletePermissionServlet extends HttpServlet {

	public DeletePermissionServlet() {
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
		int pid=Integer.parseInt(request.getParameter("pid"));		
		HttpSession session=request.getSession();
    	//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
    	PageBean pb=new PageBean();
    	IPermissionBiz perBiz=new PermissionBizImpl();
    	if(perBiz.DeletePermissions(pid)){
    		pb=perBiz.SelectByPage(nowpage, pagesize);
        	session.setAttribute("permissionPageBean",pb);	
        	response.getWriter().printf("<script>alert('删除权限成功!');location.href='permissionlist.jsp'</script>");
    	}else{
    		response.getWriter().printf("<script>alert('删除权限失败!');location.href='permissionlist.jsp'</script>");
    	}
    	
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
