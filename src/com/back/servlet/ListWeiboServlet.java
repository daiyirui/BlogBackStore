package com.back.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IWeiboBiz;
import com.back.biz.impl.WeiboBizImpl;
import com.back.filter.PageBean;

@SuppressWarnings("serial")
public class ListWeiboServlet extends HttpServlet {

	public ListWeiboServlet() {
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
		IWeiboBiz weiboBiz=new WeiboBizImpl();
		//显示微博信息		
		HttpSession session=request.getSession();
		PageBean pb=new PageBean();
		//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
    	pb=weiboBiz.SelectByPage(nowpage, pagesize);
    	session.setAttribute("WeiboList",pb);
		response.sendRedirect("show_weibo.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
