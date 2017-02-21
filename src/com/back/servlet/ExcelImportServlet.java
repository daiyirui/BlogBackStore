package com.back.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.back.biz.IAdminBiz;
import com.back.biz.impl.AdminBizImpl;
import com.back.filter.ImportExcel;
import com.back.filter.PageBean;
import com.back.po.Admins;
@SuppressWarnings("serial")
public class ExcelImportServlet extends HttpServlet {

	public ExcelImportServlet() {
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

	/**
	 * excel导入思路
	 * 1.首先用户先上传excel文件，excel安装poi格式上传
	 * 2.excel转换为list结合
	 * 3.insert 数据库admins表
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("gbk");
		HttpSession session=request.getSession();
		IAdminBiz adminBiz=new AdminBizImpl();
		//获取上传excel文件名
		String excel=request.getParameter("aname");
		//定义分页参数
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
    	PageBean pb=new PageBean();
    	//上传操作1
    	
    	try {
			FileItemFactory factory=new DiskFileItemFactory();
			ServletFileUpload fileload=new ServletFileUpload(factory);
			//设置文件大小，4m
			fileload.setSizeMax(4194304);
			List<FileItem> iteraor=fileload.parseRequest(request);
			Iterator<FileItem> iter=iteraor.iterator();			
			while (iter.hasNext()) {
				FileItem item=iter.next();
				if(item.isFormField()){					
				}else{
					//获取文件名，包含上传文件路径
					String filename=item.getName();
					if(filename!=""){
						File file=new File(filename);
						File filetoserver=new File(this.getServletContext().getRealPath("/upload/excel"),file.getName());
						if(!filetoserver.exists()){
							filetoserver.delete();
						}
						item.write(filetoserver);
						excel=request.getContextPath()+"/upload/excel/"+filename.substring(filename.lastIndexOf("\\")+1);
						//转换excel     excel:\\back\\upload\\excel\\exceptadmin.xls
						File fi=new File("F:\\Java方向\\apache-tomcat-7.0.5\\webapps"+excel);
						ImportExcel read=new ImportExcel(fi);
						List<Admins> lisadmin=read.ImportAdmins();
						//insert 数据库
						String msg=adminBiz.AddExcelAdmins(lisadmin);
						if(msg=="ok"){
							pb=adminBiz.SelectAdminsList(nowpage, pagesize);
					    	session.setAttribute("AdminsList",pb);
							response.getWriter().printf("<script>alert('导入"+lisadmin.size()+"个后台用户成功!');location.href='show_admins.jsp'</script>");
						}else{
							response.getWriter().printf("<script>alert('"+msg+"');location.href='mustaddadmins.jsp'</script>");
						}
//							
//						
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
