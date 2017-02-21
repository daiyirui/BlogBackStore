package com.back.biz;

import java.util.List;

import com.back.filter.PageBean;
import com.back.po.Admins;

public interface IAdminBiz {
	//insert 
	public boolean AddAdmins(Admins admin);
	//��ҳ��ʾ
	public PageBean SelectAdminsList(int currentPage,int pageSize);
	//��ȡ����admins������Ϣ
	public Admins SelectByaid(int aid);
	//���º�̨�û�
	public boolean UpdateAdmins(Admins admin);
	//ɾ����̨�û�
	public boolean DeleteAdmins(int aid);
	//��̨�û���½
	public Admins LoginBackStrone(String aname,String apwd);
	//����excel
	public boolean ExportAdmins();
	//����excel insert����
	public String AddExcelAdmins(List<Admins> lisAdmins);
	
}
