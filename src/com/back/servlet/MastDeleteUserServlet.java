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
public class MastDeleteUserServlet extends HttpServlet {

	public MastDeleteUserServlet() {
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
		//��ȡǰ̨����ѡ��ѡ��value
		String[] uidc=request.getParameterValues("chkOrdersnos");
		IUserBiz useBiz=new UserBizImpl();
		if(useBiz.DeleteMastUsers(uidc)){
			HttpSession session=request.getSession();
	        PageBean pb=new PageBean();
	    	//�����ҳ����
	    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
	    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
	    	pb=useBiz.SelectUserByPage(nowpage, pagesize);
	    	session.setAttribute("UsersList",pb);
	    	response.getWriter().printf("<script>alert('����ɾ��ǰ̨"+uidc.length+"���û��ɹ�!');location.href='show_users.jsp'</script>");
		}else{
			response.getWriter().printf("<script>alert('����ɾ��ǰ̨�û��ɹ�!');location.href='show_users.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
