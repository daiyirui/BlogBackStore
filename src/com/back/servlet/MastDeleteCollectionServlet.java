package com.back.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.ICollectionBiz;
import com.back.biz.impl.CollectionBizImpl;
import com.back.dao.ICollectionDao;
import com.back.dao.impl.CollectionDaoImpl;
import com.back.filter.PageBean;

@SuppressWarnings("serial")
public class MastDeleteCollectionServlet extends HttpServlet {

	public MastDeleteCollectionServlet() {
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
		ICollectionDao colBiz=new CollectionDaoImpl();
		String[] idc=request.getParameterValues("chkOrdersnos");
		for(int i = 0;i<uidc.length;i++) {
			colBiz.DeleteUser(Integer.parseInt(uidc[i]));
		}
		HttpSession session=request.getSession();
		
    		if(colBiz.DeleteMastCollection(idc)){
    			session.setAttribute("collectionList",pb);
    	    	response.getWriter().printf("<script>alert('����ɾ��"+idc.length+"������Ϣ�ɹ�!');location.href='show_collection.jsp'</script>");		
    		}else{
    			response.getWriter().printf("<script>alert('��������ɾ��ʧ��!');location.href='show_collection.jsp'</script>");
    		}
	}

	
}
