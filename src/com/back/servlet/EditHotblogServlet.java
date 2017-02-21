package com.back.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IHotBlogBiz;
import com.back.biz.impl.HotBlogBizImpl;
import com.back.filter.PageBean;

@SuppressWarnings("serial")
public class EditHotblogServlet extends HttpServlet {

	public EditHotblogServlet() {
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
		String title=request.getParameter("btitle").trim();
		String btitle=new String(title.getBytes("ISO-8859-1"), "gbk");
		IHotBlogBiz hotBiz=new HotBlogBizImpl();
		HttpSession session=request.getSession();
    	//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));    	
    	PageBean pb=new PageBean();
    	if(request.getParameter("did")!=null){
    		//delete method
    		if(hotBiz.DeleteHotblog(btitle)){
    			pb=hotBiz.SelectByPage();
    			session.setAttribute("HotBlogList",pb);
    			session.setAttribute("totalpageh", pb.getData().size());
    			session.setAttribute("currentpageh",pagesize);
    			//response.sendRedirect("show_.jsp");    	    	
    	    	response.getWriter().printf("<script>alert('"+btitle+"删除成功!');location.href='show_hotblog.jsp'</script>");		
    		}else{
    			response.getWriter().printf("<script>alert('"+btitle+"删除失败!');location.href='show_hotblog.jsp'</script>");
    		}
    	}else{
    		if(request.getParameter("eid").equals("2")){
    			//stop method
    			if(hotBiz.StopHotblog(btitle)){
    				pb=hotBiz.SelectByPage();
        			session.setAttribute("HotBlogList",pb);
        			session.setAttribute("totalpageh", pb.getData().size());
        			session.setAttribute("currentpageh",pagesize);
        	    	response.getWriter().printf("<script>alert('"+btitle+"下线成功!');location.href='show_hotblog.jsp'</script>");
    			}else{
    				response.getWriter().printf("<script>alert('"+btitle+"下线失败!');location.href='show_hotblog.jsp'</script>");
    			}
    		}else{
    			//life method
    			String msg=hotBiz.LifeHotblog(btitle);    			
    			if(msg!="ok"){
        	    	response.getWriter().printf("<script>alert('"+msg+"');location.href='show_hotblog.jsp'</script>");
    			}else{
    				pb=hotBiz.SelectByPage();
        			session.setAttribute("HotBlogList",pb);
        			session.setAttribute("totalpageh", pb.getData().size());
        			session.setAttribute("currentpageh",pagesize);
    				response.getWriter().printf("<script>alert('"+btitle+"上线成功!');location.href='show_hotblog.jsp'</script>");
    			}
    		}
    	}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
