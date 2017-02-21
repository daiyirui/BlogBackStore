package com.back.dao;

import java.util.List;

import com.back.filter.PageBean;
import com.back.po.Admins;

public interface IAdminDao {
    //insert 
	public int AddAdmins(Admins admin);
	//��ҳ��ʾ��̨�û���Ϣ
	public PageBean AdminsList(String strSQL,int currentPage,int pageSize);
	//��ȡ����admins������Ϣ
	public Admins FindByaid(int aid);
	//���º�̨�û�
	public int UpdateAdmins(Admins admin);
	//ɾ����̨�û�
	public int DeleteAdmins(int aid);
	//��̨�û���½
	public Admins LoginBackStrone(String aname,String apwd);
	//����excel
	public List<Admins> ExportAdmins();
	//����excel insert����
	//public int AddExcelAdmins(Admins admin);
}
