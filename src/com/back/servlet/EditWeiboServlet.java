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
public class EditWeiboServlet extends HttpServlet {

	public EditWeiboServlet() {
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
		int wid=Integer.parseInt(request.getParameter("wid"));
		IWeiboBiz weiboBiz=new WeiboBizImpl();
		HttpSession session=request.getSession();
    	//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
    	PageBean pb=new PageBean();
    	if(request.getParameter("did")!=null){
    		//delete method
    		if(weiboBiz.DeleteWeibo(wid)){
    			pb=weiboBiz.SelectByPage(nowpage, pagesize);
    	    	session.setAttribute("WeiboList",pb);
    	    	response.getWriter().printf("<script>alert('微博删除成功!');location.href='show_weibo.jsp'</script>");		
    		}else{
    			response.getWriter().printf("<script>alert('微博删除失败!');location.href='show_weibo.jsp'</script>");
    		}
    	}else{
    		if(request.getParameter("eid").equals("2")){
    			//stop method
    			if(weiboBiz.StopWeibo(wid)){
    				pb=weiboBiz.SelectByPage(nowpage, pagesize);
        	    	session.setAttribute("WeiboList",pb);
        	    	response.getWriter().printf("<script>alert('微博禁用成功!');location.href='show_weibo.jsp'</script>");
    			}else{
    				response.getWriter().printf("<script>alert('微博禁用失败!');location.href='show_weibo.jsp'</script>");
    			}
    		}else{
    			//life method
    			if(weiboBiz.LifeWeibo(wid)){
    				pb=weiboBiz.SelectByPage(nowpage, pagesize);
        	    	session.setAttribute("WeiboList",pb);
        	    	response.getWriter().printf("<script>alert('微博反禁用成功!');location.href='show_weibo.jsp'</script>");
    			}else{
    				response.getWriter().printf("<script>alert('微博反禁用失败!');location.href='show_weibo.jsp'</script>");
    			}
    		}
    	}
//    	
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
