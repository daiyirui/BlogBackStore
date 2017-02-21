package com.back.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.back.biz.ICollectionBiz;
import com.back.biz.impl.CollectionBizImpl;
import com.back.filter.PageBean;

@SuppressWarnings("serial")
public class EditCollectionServlet extends HttpServlet {

	public EditCollectionServlet() {
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
		ICollectionBiz colBiz=new CollectionBizImpl();
		int lid=Integer.parseInt(request.getParameter("lid"));
		PageBean pb=new PageBean();
		//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
    	if(request.getParameter("did")!=null){
    		//执行delete
    		if(colBiz.DeleteCollection(lid)){
    			pb=colBiz.SelectByPage(nowpage, pagesize);
    			session.setAttribute("collectionList",pb);
    	    	response.getWriter().printf("<script>alert('删除评论信息成功!');location.href='show_collection.jsp'</script>");
    		}else{
    			response.getWriter().printf("<script>alert('删除评论信息失败!');location.href='show_collection.jsp'</script>");
    		}
    	}else{
    		if(request.getParameter("eid").equals("2")){
    			//stop 
    			if(colBiz.StopCollection(lid)){
    				pb=colBiz.SelectByPage(nowpage, pagesize);
    				session.setAttribute("collectionList",pb);
        	    	response.getWriter().printf("<script>alert('评论信息禁用成功!');location.href='show_collection.jsp'</script>");
    			}else{
    				response.getWriter().printf("<script>alert('评论信息禁用失败!');location.href='show_collection.jsp'</script>");
    			}
    		}else{
    		    //life
    			if(colBiz.LifeCollection(lid)){
    				pb=colBiz.SelectByPage(nowpage, pagesize);
    				session.setAttribute("collectionList",pb);
        	    	response.getWriter().printf("<script>alert('评论信息反禁用成功!');location.href='show_collection.jsp'</script>");
    			}else{
    				response.getWriter().printf("<script>alert('评论信息反禁用失败!');location.href='show_collection.jsp'</script>");
    			}
    		}
    	}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
