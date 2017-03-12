package com.back.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.back.dao.IRelationsDao;
import com.back.dao.impl.RelationsDaoImpl;
import com.back.po.Relations;

public class RelationsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if("listRelation".equals(action)) {
	        listRelation(request, response);
		}
	}
	private void listRelation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IRelationsDao relationsDao=new RelationsDaoImpl();
	    List<Relations> relationList = relationsDao.FindAllRelations();
	    request.setAttribute("relationList",relationList);
		request.getRequestDispatcher("./show_relation.jsp").forward(request, response);
	}

}
