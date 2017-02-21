package com.back.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IHotBlogBiz;
import com.back.biz.impl.HotBlogBizImpl;
import com.back.filter.PageBean;
import com.back.po.Bloghot;

@SuppressWarnings("serial")
public class ListBlogHotServlet extends HttpServlet {

	public ListBlogHotServlet() {
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
		PageBean pb=new PageBean();
		IHotBlogBiz hotBiz=new HotBlogBizImpl();
		pb=hotBiz.SelectByPage();
		//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	List<Bloghot> l=pb.getData();
    	for (Bloghot bloghot : l) {
			System.out.println("ss"+bloghot.getBtitle());
		}
    	session.setAttribute("HotBlogList",pb);
		session.setAttribute("totalpageh", pb.getData().size());
		session.setAttribute("currentpageh",pagesize);
		response.sendRedirect("show_hotblog.jsp");		
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
