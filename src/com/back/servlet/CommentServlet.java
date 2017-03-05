package com.back.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.back.dao.ICommentDao;
import com.back.dao.impl.CommentDaoImpl;
import com.back.po.Comment;

@SuppressWarnings("serial")
public class CommentServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
	    if("listComment".equals(action)) {
	    	listComment(request,response);
    	
		}
	}
	private void listComment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ICommentDao commentdao = new CommentDaoImpl();
		List<Comment> CommentList = commentdao.findAllComment();
		request.setAttribute("CommentList",CommentList);
		request.getRequestDispatcher("show_comment.jsp").forward(request, response);	
	}
	

}
