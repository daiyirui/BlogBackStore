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
import com.back.po.Bloghot;

@SuppressWarnings("serial")
public class AddHotblogServlet extends HttpServlet {

	public AddHotblogServlet() {
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
		String btitle=request.getParameter("btitle").trim();
		String aremarks=request.getParameter("aremarks").trim();
		Bloghot hot=new Bloghot();
		hot.setBtitle(btitle);
		hot.setBremarks(aremarks);
		if(request.getParameter("bstate")!=null){
			hot.setBstate(1);
		}else{
			hot.setBstate(0);
		}
		String[] items=new String[5];
			//request.getParameterValues("");
		for (int i = 0; i < 5; i++) {
			if(request.getParameter("bitems"+i)!=""){
				items[i]=request.getParameter("bitems"+i).trim();
			}
		}
		IHotBlogBiz hotBiz=new HotBlogBizImpl();
		String msg=hotBiz.AddHotBlog(hot, items);
		if(msg=="ok"){
			HttpSession session=request.getSession();
			PageBean pb=new PageBean();
			pb=hotBiz.SelectByPage();
			//定义分页参数
	    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));	    	
	    	session.setAttribute("HotBlogList",pb);
			session.setAttribute("totalpageh", pb.getData().size());
			session.setAttribute("currentpageh",pagesize);
			response.getWriter().printf("<script>alert('添加微博热议成功');location.href='show_hotblog.jsp'</script>");
		}else{
			response.getWriter().printf("<script>alert('"+msg+"');location.href='addhotblog.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
