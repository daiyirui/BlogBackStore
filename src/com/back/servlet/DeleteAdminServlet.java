package com.back.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IAdminBiz;
import com.back.biz.impl.AdminBizImpl;
import com.back.filter.PageBean;

@SuppressWarnings("serial")
public class DeleteAdminServlet extends HttpServlet {

	public DeleteAdminServlet() {
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
        if(adminBiz.DeleteAdmins(aid)){
        	PageBean pb=new PageBean();
        	//定义分页参数
        	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
        	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
        	pb=adminBiz.SelectAdminsList(nowpage, pagesize);
	    	session.setAttribute("AdminsList",pb);
	    	response.getWriter().printf("<script>alert('删除后台用户成功!');location.href='show_admins.jsp'</script>");
        }else{
        	response.getWriter().printf("<script>alert('删除后台用户失败!');location.href='show_admins.jsp'</script>");
        }
    }
	public void init() throws ServletException {
		// Put your code here
	}

}
