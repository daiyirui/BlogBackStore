package com.back.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.back.biz.IAdminBiz;
import com.back.biz.impl.AdminBizImpl;

@SuppressWarnings("serial")
public class DownLoadAdminServlet extends HttpServlet {

	public DownLoadAdminServlet() {
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
	 * 导出数据思路：
	 * 1.获取数据库数据，List封装，
	 * 2.转换为excel文件(用poi.jar),格式导出到pc硬盘内.路径为tomcat发布项目相对于路径
	 * 3.下载到客户端pc机器内
	 */
	@SuppressWarnings("static-access")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("gbk");
		IAdminBiz adminBiz=new AdminBizImpl();
		if(adminBiz.ExportAdmins()){
			//下载操作3
			File pathsavefile=new File("F:\\Java方向\\apache-tomcat-7.0.5\\webapps\\back\\upload\\excel\\exceptadmin.xls");
			String fileName="exceptadmin.xls";
			//重新设置响应
			response.reset();
			//设置内容文件的类型  
			response.setContentType("APPLICATION/OCTET-STREAM");
			//转码
			fileName=response.encodeURL(new String(fileName.getBytes(),"gbk"));
			//以附件形式提醒用户下载，
			response.setHeader("Content-Disposition","; filename=\""+fileName+"\"");
			//得到响应的输出流，即向客户端输出信息的输出流
			ServletOutputStream out=response.getOutputStream();
			InputStream inStream=new FileInputStream(pathsavefile);
			byte[] b=new byte[1024];
			int len;
			while ((len=inStream.read(b))>0)
			out.write(b, 0, len);
			response.setStatus(response.SC_OK);
			response.flushBuffer();
			out.close();
			inStream.close();
		}else{
			response.getWriter().printf("<script>alert('导出后台用户失败!');location.href='show_admins.jsp'</script>");
		}
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
