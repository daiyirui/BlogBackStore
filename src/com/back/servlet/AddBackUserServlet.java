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
		String aname=request.getParameter("aname").trim();
		String apwd=request.getParameter("apwd").trim();
		int a_pid=Integer.parseInt(request.getParameter("a_pid"));
		String asex=request.getParameter("asex").trim();
        String arealname=request.getParameter("arealname").trim();
        String aremarks=request.getParameter("aremarks").trim();
        Admins admin=new Admins(); 
        admin.setA_pid(a_pid);
        admin.setAname(aname);
        admin.setApwd(apwd);
        admin.setArealname(arealname);
        admin.setAremarks(aremarks);
        admin.setAsex(asex);
        HttpSession session=request.getSession();
        PageBean pb=new PageBean();
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
        IAdminBiz adminBiz=new AdminBizImpl();
        if(request.getParameter("ap")!=null){
        	//ִ��update
        	int aid=Integer.parseInt(request.getParameter("aid"));
        	admin.setAid(aid);
        	if(adminBiz.UpdateAdmins(admin)){
        		pb=adminBiz.SelectAdminsList(nowpage, pagesize);
    	    	session.setAttribute("AdminsList",pb);
    	    	response.getWriter().printf("<script>alert('���º�̨�û�"+aname+"�ɹ�!');location.href='show_admins.jsp'</script>");
        	}else{
        		response.getWriter().printf("<script>alert('���º�̨�û�ʧ��!');location.href='udpateadmins.jsp'</script>");
        	}
        }else{
        	//ִ��insert����
            if(adminBiz.AddAdmins(admin)){            	
    	    	pb=adminBiz.SelectAdminsList(nowpage, pagesize);
    	    	session.setAttribute("AdminsList",pb);
    	    	response.getWriter().printf("<script>alert('��Ӻ�̨�û�"+aname+"�ɹ�!');location.href='show_admins.jsp'</script>");
            }else{
            	response.getWriter().printf("<script>alert('��Ӻ�̨�û�ʧ��!');location.href='addadmins.jsp'</script>");
            }        	
        }
        
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
