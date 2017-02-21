package com.back.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.filter.PageBean;
import com.back.po.Relations;

@SuppressWarnings("serial")
public class SelectRelationDetailServlet extends HttpServlet {

	public SelectRelationDetailServlet() {
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
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("gbk");
		int gid=Integer.parseInt(request.getParameter("gid"));
		//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	PageBean pb=new PageBean();
		HttpSession session=request.getSession();
		if(session.getAttribute("relationList")!=null){
			pb=(PageBean) session.getAttribute("relationList");	
		}
		List<Relations> lisRel=new ArrayList<Relations>();
		Relations rel=new Relations();
		lisRel=pb.getData();
		for (Relations relations : lisRel) {
			if(relations.getG_id().equals(gid)){
				System.out.println("uname "+relations.getUse().getUname());
				rel=relations;
				break;
			}
		}
		session.setAttribute("relationDetail",rel);
		session.setAttribute("totalpage", rel.getUselist().size());
		session.setAttribute("currentpage",pagesize);
		response.sendRedirect("relationdetail.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
