package com.back.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IUserBiz;
import com.back.biz.impl.UserBizImpl;
import com.back.filter.PageBean;

@SuppressWarnings("serial")
public class EditUserServlet extends HttpServlet {

	public EditUserServlet() {
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
		IUserBiz useBiz=new UserBizImpl();
		int uid=Integer.parseInt(request.getParameter("uid"));
		PageBean pb=new PageBean();
		//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
    	if(request.getParameter("did")!=null){
    		//执行delete
    		if(useBiz.DeleteUsers(uid)){
    			pb=useBiz.SelectUserByPage(nowpage, pagesize);
    	    	session.setAttribute("UsersList",pb);
    	    	response.getWriter().printf("<script>alert('删除前台用户成功!');location.href='show_users.jsp'</script>");
    		}else{
    			response.getWriter().printf("<script>alert('删除前台用户失败!');location.href='show_users.jsp'</script>");
    		}
    	}else{
    		if(request.getParameter("eid").equals("2")){
    			//stop 
    			if(useBiz.StopUsers(uid)){
    				pb=useBiz.SelectUserByPage(nowpage, pagesize);
        	    	session.setAttribute("UsersList",pb);
        	    	response.getWriter().printf("<script>alert('前台用户禁用成功!');location.href='show_users.jsp'</script>");
    			}else{
    				response.getWriter().printf("<script>alert('前台用户禁用失败!');location.href='show_users.jsp'</script>");
    			}
    		}else{
    		    //life
    			if(useBiz.LifeUsers(uid)){
    				pb=useBiz.SelectUserByPage(nowpage, pagesize);
        	    	session.setAttribute("UsersList",pb);
        	    	response.getWriter().printf("<script>alert('前台用户反禁用成功!');location.href='show_users.jsp'</script>");
    			}else{
    				response.getWriter().printf("<script>alert('前台用户反禁用失败!');location.href='show_users.jsp'</script>");
    			}
    		}
    	}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
