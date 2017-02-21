package com.back.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.back.biz.IPerContentBiz;
import com.back.biz.IPermissionBiz;
import com.back.biz.impl.PerContentBizImpl;
import com.back.biz.impl.PermissionBizImpl;
import com.back.filter.PageBean;
import com.back.po.Percontent;
import com.back.po.Permissions;

@SuppressWarnings("serial")
public class UpdatePermissionServlet extends HttpServlet {

	public UpdatePermissionServlet() {
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
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("gbk");
	    //��ȡǰ̨ҳ����Ϣ
		String pname=request.getParameter("pname").trim();
		String pcontent=request.getParameter("pcontent").trim();
		String premarks=request.getParameter("premarks").trim();
		String[] items=request.getParameterValues("items");
		int pid=Integer.parseInt(request.getParameter("pid"));
		HttpSession session=request.getSession();
		//����Ȩ����Ϣ================================
		Permissions permission=new Permissions();
		permission.setPid(pid);
		permission.setPname(pname);
		permission.setPcontent(pcontent);
		permission.setPremarks(premarks);
		IPermissionBiz perBiz=new PermissionBizImpl();
		if(perBiz.UpdatePermissions(permission)){			
			//֤��permission����³ɹ�������items���²���
			//1.���ж�Ȩ��items�Ƿ��и��¸Ķ���2.���ñ�������ȡ������Ȩ��items���ϣ�
			//3.֪��Ȩ��items�Ķ�������not in��... sql�������ɾ�������Լ����º�ɾ����Ȩ��items
			//4�����������Ȩ��items���ϣ���percontent������insert �¼�¼
			List<String> ite=new ArrayList<String>();
			List<Percontent> lisPerContent=new ArrayList<Percontent>();
			//��ȡԭ��Ȩ��items����
			if(session.getAttribute("permissionItems")!=null){
				lisPerContent=(List<Percontent>) session.getAttribute("permissionItems");
			}
			//����ԭ��items����
			for (int i = 0; i < lisPerContent.size(); i++) {
				if(lisPerContent.get(i).getPcremarks()=="ok"){
					ite.add(lisPerContent.get(i).getPcitems());
				}
			}
			//���º�Ȩ�޺�ԭ��Ȩ��items���бȽϣ���¼����Ȩ�޺�ɾ��ԭ�ȵ�Ȩ��items
			boolean  flg=false;
			List<String> ites=new ArrayList<String>();			
			for (int i = 0; i < items.length; i++) {
				//�ж�ԭ��Ȩ��items��Ƿ���ڸ��º��Ȩ��items
				if(!ite.contains(items[i])){
					flg=true;
					//��¼���º�������Ȩ��items��֤��Ȩ�ޱ����¹�
					ites.add(items[i]);
				}
			}
			//����ɾ����ԭ��Ȩ��items�У����Ǹ��º�ɾ����percontent����items��¼
			//delete form percontent where pc_pid = 5 and pcitems not in (items[1],items...)
			//���flgΪtrue��֤��ԭ��items�иĶ�
			IPerContentBiz perconBiz=new PerContentBizImpl();
			if(flg){
				if(perconBiz.DeletePerContentByObject(pid, items)){				
				}else{
					response.getWriter().printf("<script>alert('����Ȩ��ʧ��!');location.href='updatepermission.jsp'</script>");
					return;
				}
			}
			//��Ӹ��º�������Ȩ��items
			if(ites.size()!=0){
				//���������Ȩ��items
				if(perconBiz.AddPercontent(pid, ites)){				
				}else{
					response.getWriter().printf("<script>alert('����Ȩ��ʧ��!');location.href='updatepermission.jsp'</script>");
					return;
				}
			}
			//���ϸ���Ȩ����Ϣitems��ϣ���ʾpermission�б�
			//�����ҳ����
	    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
	    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
	    	PageBean pb=new PageBean();
	    	pb=perBiz.SelectByPage(nowpage, pagesize);
	    	session.setAttribute("permissionPageBean",pb);
			response.getWriter().printf("<script>alert('����"+pname+"Ȩ�޳ɹ�!');location.href='permissionlist.jsp'</script>");
		}else{
			response.getWriter().printf("<script>alert('����"+pname+"Ȩ��ʧ��!');location.href='updatepermission.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
