package com.back.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.back.dao.IWeiboDao;
import com.back.dao.impl.WeiboDaoImpl;
import com.back.po.Weibo;

@SuppressWarnings("serial")
public class WeiboServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if("listWeibo".equals(action)) {
			listWeibo(request,response);
		}
		
	}
	private void listWeibo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IWeiboDao weibodao = new WeiboDaoImpl();
	    List<Weibo>	WeiboList=weibodao.FindAllWeibo();
		request.setAttribute("WeiboList",WeiboList);
		request.getRequestDispatcher("show_weibo.jsp").forward(request, response);
	}
}
