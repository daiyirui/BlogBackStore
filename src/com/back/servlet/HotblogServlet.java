package com.back.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.back.dao.IBollhotDao;
import com.back.dao.impl.BollhotDaoImpl;
import com.back.po.Bloghot;
import com.back.po.Bloghotitem;

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
		}else if("insertBlogHot".equals(action)) {
			try {
				insertBlogHot(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void insertBlogHot(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IBollhotDao bloghotDao = new BollhotDaoImpl();
		
		Bloghot bloghot = new Bloghot();
		//要插入的微博
    	String bloghot_image=request.getParameter("blogHotUpfile");
    	bloghot.setBstate(1);
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload fileload=new ServletFileUpload(factory);
		//设置文件大小，4m
		fileload.setSizeMax(4194304);
		List<FileItem> iteraor=fileload.parseRequest(request);
		Iterator<FileItem> iter=iteraor.iterator();
		
		bloghot.setBvote(0);
		while (iter.hasNext()) {
			FileItem item=iter.next();
			if(item.isFormField()){
				if("btitle".equals(item.getFieldName())){
					bloghot.setBtitle(item.getString("utf-8"));						
				}
			}else{
				//获取文件名，包含上传文件路径
				String filename=item.getName();
				if(filename!=""){
					File file=new File(filename);
					File filetoserver=new File(this.getServletContext().getRealPath("/upload/pic"),file.getName());
					item.write(filetoserver);
					bloghot_image=request.getContextPath()+"/upload/pic/"+filename.substring(filename.lastIndexOf("\\")+1);
					bloghot.setBimages(bloghot_image);
				}
			}
		}
		bloghotDao.addHot(bloghot);
		List<Bloghot> bloghots = bloghotDao.FindAllHot();
		bloghot=bloghots.get(bloghots.size()-1);
		String bitems0 = request.getParameter("bitems0");
		String itemUpfile0 = request.getParameter("itemUpfile0");
		bloghotDao.addItem(insertItem(request, response,bitems0,itemUpfile0,bloghot.getBid()));
		
		String bitems1 = request.getParameter("bitems1");
		String itemUpfile1 = request.getParameter("itemUpfile1");
		bloghotDao.addItem(insertItem(request, response,bitems1,itemUpfile1,bloghot.getBid()));
		
		listBlogHot(request,response);
	}
	
	private Bloghotitem insertItem(HttpServletRequest request,
			HttpServletResponse response,String title,String image,int bid) throws Exception{
		Bloghotitem bloghotitem = new Bloghotitem();
		bloghotitem.setBvote(0);
		bloghotitem.setBid(bid);
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload fileload=new ServletFileUpload(factory);
		//设置文件大小，4m
		fileload.setSizeMax(4194304);
		List<FileItem> iteraor=fileload.parseRequest(request);
		Iterator<FileItem> iter=iteraor.iterator();
		while (iter.hasNext()) {
			FileItem item=iter.next();
			System.out.println(item);
			if(item.isFormField()){
				if(title.equals(item.getFieldName())){
					bloghotitem.setBitemName(item.getString("utf-8"));						
				}
			}else{
				//获取文件名，包含上传文件路径
				String filename=item.getName();
				if(filename!=""){
					File file=new File(filename);
					File filetoserver=new File(this.getServletContext().getRealPath("/upload/pic"),file.getName());
					item.write(filetoserver);
					image=request.getContextPath()+"/upload/pic/"+filename.substring(filename.lastIndexOf("\\")+1);
					bloghotitem.setBitemimage(image);
				}
			}
		}
		   return bloghotitem;
	}
	
	
	private void listBlogHot(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		IBollhotDao bollhotDao = new BollhotDaoImpl();
	    List<Bloghot> hotBlogList=bollhotDao.FindAllHot();
		request.setAttribute("hotBlogList",hotBlogList);
		request.getRequestDispatcher("show_hotblog.jsp").forward(request, response);
	}

}
