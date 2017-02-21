package com.back.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IPerContentBiz;
import com.back.biz.IPermissionBiz;
import com.back.biz.impl.PerContentBizImpl;
import com.back.biz.impl.PermissionBizImpl;
import com.back.po.Percontent;
import com.back.po.Permissions;

@SuppressWarnings("serial")
public class SelectDetailPermissionServlet extends HttpServlet {

	public SelectDetailPermissionServlet() {
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
		int pid=Integer.parseInt(request.getParameter("pid"));
		HttpSession session=request.getSession();
		//获取单个权限信息对象
		IPermissionBiz perBiz=new PermissionBizImpl();
		Permissions permission=new Permissions();
		permission=perBiz.SelectBypid(pid);
		session.setAttribute("permissionDetail", permission);
		//获取全部权限items信息集合
		List<Percontent> lisPerContent=new ArrayList<Percontent>();
		IPerContentBiz perconBiz=new PerContentBizImpl();
		lisPerContent=perconBiz.SelectByManager("管理员");
		//通过循环赋值全部权限items参数标记
		for (int i = 0; i < permission.getContent().size(); i++) {
			for (int j = 0; j < lisPerContent.size(); j++) {
				if(permission.getContent().get(i).getPcitems().equals(lisPerContent.get(j).getPcitems())){
					lisPerContent.get(j).setPcremarks("ok");					
				}
			}
		}
		session.setAttribute("permissionItems",lisPerContent);
		response.sendRedirect("updatepermission.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}
}
