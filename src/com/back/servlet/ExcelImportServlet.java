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
	 * excel����˼·
	 * 1.�����û����ϴ�excel�ļ���excel��װpoi��ʽ�ϴ�
	 * 2.excelת��Ϊlist���
	 * 3.insert ���ݿ�admins��
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("gbk");
		HttpSession session=request.getSession();
		IAdminBiz adminBiz=new AdminBizImpl();
		//��ȡ�ϴ�excel�ļ���
		String excel=request.getParameter("aname");
		//�����ҳ����
    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
    	PageBean pb=new PageBean();
    	//�ϴ�����1
    	
    	try {
			FileItemFactory factory=new DiskFileItemFactory();
			ServletFileUpload fileload=new ServletFileUpload(factory);
			//�����ļ���С��4m
			fileload.setSizeMax(4194304);
			List<FileItem> iteraor=fileload.parseRequest(request);
			Iterator<FileItem> iter=iteraor.iterator();			
			while (iter.hasNext()) {
				FileItem item=iter.next();
				if(item.isFormField()){					
				}else{
					//��ȡ�ļ����������ϴ��ļ�·��
					String filename=item.getName();
					if(filename!=""){
						File file=new File(filename);
						File filetoserver=new File(this.getServletContext().getRealPath("/upload/excel"),file.getName());
						if(!filetoserver.exists()){
							filetoserver.delete();
						}
						item.write(filetoserver);
						excel=request.getContextPath()+"/upload/excel/"+filename.substring(filename.lastIndexOf("\\")+1);
						//ת��excel     excel:\\back\\upload\\excel\\exceptadmin.xls
						File fi=new File("F:\\Java����\\apache-tomcat-7.0.5\\webapps"+excel);
						ImportExcel read=new ImportExcel(fi);
						List<Admins> lisadmin=read.ImportAdmins();
						//insert ���ݿ�
						String msg=adminBiz.AddExcelAdmins(lisadmin);
						if(msg=="ok"){
							pb=adminBiz.SelectAdminsList(nowpage, pagesize);
					    	session.setAttribute("AdminsList",pb);
							response.getWriter().printf("<script>alert('����"+lisadmin.size()+"����̨�û��ɹ�!');location.href='show_admins.jsp'</script>");
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
