package com.back.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IPermissionBiz;
import com.back.biz.impl.PermissionBizImpl;
import com.back.filter.PageBean;
import com.back.po.Permissions;

@SuppressWarnings("serial")
public class AddPermissionServlet extends HttpServlet {

	public AddPermissionServlet() {
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
	    String[] items=request.getParameterValues("items");
	    //权限名称
	    String pname=request.getParameter("pname").trim();
	    //权限说明
	    String pcontent=request.getParameter("pcontent").trim();
	    //权限备注
	    String premarks=request.getParameter("premarks").trim();
	    if(premarks.equals("")){
	    	premarks=null;
	    }
	    Permissions permission=new Permissions(pname, pcontent, premarks);
	    IPermissionBiz perBiz=new PermissionBizImpl();
	    if(perBiz.AddPermission(permission, items)){
	    	//System.out.println("Permission Add success!");
	    	HttpSession session=request.getSession();
	    	//定义分页参数
	    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
	    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
	    	PageBean pb=new PageBean();
	    	pb=perBiz.SelectByPage(nowpage, pagesize);
	    	session.setAttribute("permissionPageBean",pb);
	    	response.getWriter().printf("<script>alert('添加权限成功!');location.href='permissionlist.jsp'</script>");
	    }else{
	    	response.getWriter().printf("<script>alert('添加权限失败!');location.href='addpermission.jsp'</script>");
	    }		
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
