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
public class MastDeleteWeiboServlet extends HttpServlet {

	public MastDeleteWeiboServlet() {
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
		String[] idc=request.getParameterValues("chkDeptnos");
		HttpSession session=request.getSession();
		IWeiboBiz weiboBiz=new WeiboBizImpl();		
    	//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
    	PageBean pb=new PageBean();    	
    		if(weiboBiz.DeleteMastWeibo(idc)){
    			pb=weiboBiz.SelectByPage(nowpage, pagesize);
    	    	session.setAttribute("WeiboList",pb);
    	    	response.getWriter().printf("<script>alert('批量删除"+idc.length+"微博信息成功!');location.href='show_weibo.jsp'</script>");		
    		}else{
    			response.getWriter().printf("<script>alert('微博批量删除失败!');location.href='show_weibo.jsp'</script>");
    		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
