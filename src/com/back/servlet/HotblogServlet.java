package com.back.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.back.dao.IBollhotDao;
import com.back.dao.ICollectionDao;
import com.back.dao.impl.BollhotDaoImpl;
import com.back.dao.impl.CollectionDaoImpl;
import com.back.po.Bloghot;
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
		if("listBlogHot".equals(action)) {
			listBlogHot(request,response);
		}
	}
	private void listBlogHot(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IBollhotDao bollhotDao = new BollhotDaoImpl();
	    List<Bloghot> hotBlogList=bollhotDao.FindAllHot();
		request.setAttribute("hotBlogList",hotBlogList);
		request.getRequestDispatcher("show_hotblog.jsp").forward(request, response);
	}

}
