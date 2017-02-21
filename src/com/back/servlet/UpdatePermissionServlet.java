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
	    //获取前台页面信息
		String pname=request.getParameter("pname").trim();
		String pcontent=request.getParameter("pcontent").trim();
		String premarks=request.getParameter("premarks").trim();
		String[] items=request.getParameterValues("items");
		int pid=Integer.parseInt(request.getParameter("pid"));
		HttpSession session=request.getSession();
		//更新权限信息================================
		Permissions permission=new Permissions();
		permission.setPid(pid);
		permission.setPname(pname);
		permission.setPcontent(pcontent);
		permission.setPremarks(premarks);
		IPermissionBiz perBiz=new PermissionBizImpl();
		if(perBiz.UpdatePermissions(permission)){			
			//证明permission表更新成功，进入items更新步骤
			//1.先判断权限items是否有更新改动，2.利用遍历来获取新增的权限items集合，
			//3.知道权限items改动后，利用not in（... sql语句来做删除操作以及更新后删除的权限items
			//4，添加新增的权限items集合，在percontent表中中insert 新记录
			List<String> ite=new ArrayList<String>();
			List<Percontent> lisPerContent=new ArrayList<Percontent>();
			//获取原先权限items集合
			if(session.getAttribute("permissionItems")!=null){
				lisPerContent=(List<Percontent>) session.getAttribute("permissionItems");
			}
			//遍历原先items集合
			for (int i = 0; i < lisPerContent.size(); i++) {
				if(lisPerContent.get(i).getPcremarks()=="ok"){
					ite.add(lisPerContent.get(i).getPcitems());
				}
			}
			//更新后权限和原先权限items进行比较，记录更新权限后，删除原先的权限items
			boolean  flg=false;
			List<String> ites=new ArrayList<String>();			
			for (int i = 0; i < items.length; i++) {
				//判断原先权限items里，是否存在更新后的权限items
				if(!ite.contains(items[i])){
					flg=true;
					//记录更新后新增的权限items，证明权限被更新过
					ites.add(items[i]);
				}
			}
			//批量删除，原先权限items有，但是更新后删除的percontent表中items记录
			//delete form percontent where pc_pid = 5 and pcitems not in (items[1],items...)
			//如果flg为true，证明原先items有改动
			IPerContentBiz perconBiz=new PerContentBizImpl();
			if(flg){
				if(perconBiz.DeletePerContentByObject(pid, items)){				
				}else{
					response.getWriter().printf("<script>alert('更新权限失败!');location.href='updatepermission.jsp'</script>");
					return;
				}
			}
			//添加更新后新增的权限items
			if(ites.size()!=0){
				//添加新增的权限items
				if(perconBiz.AddPercontent(pid, ites)){				
				}else{
					response.getWriter().printf("<script>alert('更新权限失败!');location.href='updatepermission.jsp'</script>");
					return;
				}
			}
			//以上更新权限信息items完毕，显示permission列表
			//定义分页参数
	    	int pagesize=Integer.parseInt(this.getServletConfig().getInitParameter("pagesize"));
	    	int nowpage=request.getParameter("np")!=null?Integer.parseInt(request.getParameter("np")):1;
	    	PageBean pb=new PageBean();
	    	pb=perBiz.SelectByPage(nowpage, pagesize);
	    	session.setAttribute("permissionPageBean",pb);
			response.getWriter().printf("<script>alert('更新"+pname+"权限成功!');location.href='permissionlist.jsp'</script>");
		}else{
			response.getWriter().printf("<script>alert('更新"+pname+"权限失败!');location.href='updatepermission.jsp'</script>");
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
