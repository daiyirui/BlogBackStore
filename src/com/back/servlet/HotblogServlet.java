package com.back.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.back.dao.ICollectionDao;
import com.back.dao.impl.CollectionDaoImpl;
import com.back.po.Collection;

public class HotblogServlet extends HttpServlet {

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
		if("listHotblog".equals(action)) {
			listHotblog(request,response);
		}
	}
	private void listHotblog(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ICollectionDao weibodao = new CollectionDaoImpl();
	    List<Collection> collectionList=weibodao.FindAllCollection();
		request.setAttribute("collectionList",collectionList);
		request.getRequestDispatcher("show_collection.jsp").forward(request, response);
	}

}
