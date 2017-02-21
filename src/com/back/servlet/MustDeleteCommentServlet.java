package com.back.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.ICommentBiz;
import com.back.biz.impl.CommentBizImpl;
import com.back.filter.PageBean;

@SuppressWarnings("serial")
public class MustDeleteCommentServlet extends HttpServlet {

	public MustDeleteCommentServlet() {
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
		ICommentBiz commentBiz=new CommentBizImpl();		
		HttpSession session=request.getSession();
		PageBean pb=new PageBean();
		//定义分页参数
	    int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
	    int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
	    if(request.getParameterValues("chkDeptnos")!=null){
	    	String[] cids= request.getParameterValues("chkDeptnos");
	    	if(commentBiz.DeleteMastComment(cids)){
	 		    pb=commentBiz.SelectByPage(nowpage, pagesize);
	 	    	session.setAttribute("CommentList",pb);
	 	    	response.getWriter().printf("<script>alert('批量删除"+cids.length+"条评论成功!');location.href='show_comment.jsp'</script>");
	 	    }else{
	 	    	response.getWriter().printf("<script>alert('批量删除评论失败!');location.href='show_comment.jsp'</script>");
	 	    } 	
	    }else{
	    	response.getWriter().printf("<script>alert('至少选择一条评论信息!');location.href='show_comment.jsp'</script>");
	    }	
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
