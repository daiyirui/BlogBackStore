package com.back.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IAdminBiz;
import com.back.biz.impl.AdminBizImpl;
import com.back.filter.PageBean;
import com.back.po.Admins;

@SuppressWarnings("serial")
public class AddBackUserServlet extends HttpServlet {

	public AddBackUserServlet() {
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
		//获取前台页面表单信息
		String aname=request.getParameter("aname").trim();
		String apwd=request.getParameter("apwd").trim();
		int a_pid=Integer.parseInt(request.getParameter("a_pid"));
		String asex=request.getParameter("asex").trim();
        String arealname=request.getParameter("arealname").trim();
        String aremarks=request.getParameter("aremarks").trim();
        //封装实体类
        Admins admin=new Admins(); 
        admin.setA_pid(a_pid);
        admin.setAname(aname);
        admin.setApwd(apwd);
        admin.setArealname(arealname);
        admin.setAremarks(aremarks);
        admin.setAsex(asex);
        HttpSession session=request.getSession();
        PageBean pb=new PageBean();
    	//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
        IAdminBiz adminBiz=new AdminBizImpl();
        if(request.getParameter("ap")!=null){
        	//执行update
        	int aid=Integer.parseInt(request.getParameter("aid"));
        	admin.setAid(aid);
        	if(adminBiz.UpdateAdmins(admin)){
        		pb=adminBiz.SelectAdminsList(nowpage, pagesize);
    	    	session.setAttribute("AdminsList",pb);
    	    	response.getWriter().printf("<script>alert('更新后台用户"+aname+"成功!');location.href='show_admins.jsp'</script>");
        	}else{
        		response.getWriter().printf("<script>alert('更新后台用户失败!');location.href='udpateadmins.jsp'</script>");
        	}
        }else{
        	//执行insert操作
            if(adminBiz.AddAdmins(admin)){            	
    	    	pb=adminBiz.SelectAdminsList(nowpage, pagesize);
    	    	session.setAttribute("AdminsList",pb);
    	    	response.getWriter().printf("<script>alert('添加后台用户"+aname+"成功!');location.href='show_admins.jsp'</script>");
            }else{
            	response.getWriter().printf("<script>alert('添加后台用户失败!');location.href='addadmins.jsp'</script>");
            }        	
        }
        
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
