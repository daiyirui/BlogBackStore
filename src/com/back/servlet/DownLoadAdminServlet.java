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
	 * ��������˼·��
	 * 1.��ȡ���ݿ����ݣ�List��װ��
	 * 2.ת��Ϊexcel�ļ�(��poi.jar),��ʽ������pcӲ����.·��Ϊtomcat������Ŀ�����·��
	 * 3.���ص��ͻ���pc������
	 */
	@SuppressWarnings("static-access")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("gbk");
		IAdminBiz adminBiz=new AdminBizImpl();
		if(adminBiz.ExportAdmins()){
			//���ز���3
			File pathsavefile=new File("F:\\Java����\\apache-tomcat-7.0.5\\webapps\\back\\upload\\excel\\exceptadmin.xls");
			String fileName="exceptadmin.xls";
			//����������Ӧ
			response.reset();
			//���������ļ�������  
			response.setContentType("APPLICATION/OCTET-STREAM");
			//ת��
			fileName=response.encodeURL(new String(fileName.getBytes(),"gbk"));
			//�Ը�����ʽ�����û����أ�
			response.setHeader("Content-Disposition","; filename=\""+fileName+"\"");
			//�õ���Ӧ�������������ͻ��������Ϣ�������
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
			response.getWriter().printf("<script>alert('������̨�û�ʧ��!');location.href='show_admins.jsp'</script>");
		}
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
