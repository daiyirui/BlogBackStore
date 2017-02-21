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
public class MastDeleteCollectionServlet extends HttpServlet {

	public MastDeleteCollectionServlet() {
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
		request.setCharacterEncoding("gbk");//chkOrdersnos
		String[] idc=request.getParameterValues("chkOrdersnos");
		HttpSession session=request.getSession();
		ICollectionBiz colBiz=new CollectionBizImpl();
    	//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
    	PageBean pb=new PageBean();    	
    		if(colBiz.DeleteMastCollection(idc)){
    			pb=colBiz.SelectByPage(nowpage, pagesize);
    			session.setAttribute("collectionList",pb);
    	    	response.getWriter().printf("<script>alert('批量删除"+idc.length+"评论信息成功!');location.href='show_collection.jsp'</script>");		
    		}else{
    			response.getWriter().printf("<script>alert('评论批量删除失败!');location.href='show_collection.jsp'</script>");
    		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
