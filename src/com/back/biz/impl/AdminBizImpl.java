package com.back.biz.impl;



import java.util.List;

import com.back.biz.IAdminBiz;
import com.back.dao.IAdminDao;
import com.back.dao.IPermissionDao;
import com.back.dao.impl.AdminDaoImpl;
import com.back.dao.impl.PermissionDaoImpl;
import com.back.filter.ExceptExcel;
import com.back.filter.PageBean;
import com.back.po.Admins;
import com.back.po.Permissions;

public class AdminBizImpl implements IAdminBiz {
    IAdminDao adminDao;
    public AdminBizImpl(){
    	adminDao=new AdminDaoImpl();
    }
	@Override
	public boolean AddAdmins(Admins admin) {
		// TODO Auto-generated method stub
		int a =adminDao.AddAdmins(admin);
		if(a>0){
			return true;
		}else{
			return false;	
		}
	}

	@Override
	public PageBean SelectAdminsList(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return adminDao.AdminsList("SELECT * FROM admins", currentPage, pageSize);
	}

	@Override
	public Admins SelectByaid(int aid) {
		// TODO Auto-generated method stub
		return adminDao.FindByaid(aid);
	}
	@Override
	public boolean UpdateAdmins(Admins admin) {
		// TODO Auto-generated method stub
		int a =adminDao.UpdateAdmins(admin);
		if(a>0){
			return true;
		}else{
			return false;	
		}
	}
	@Override
	public boolean DeleteAdmins(int aid) {
		// TODO Auto-generated method stub
		int a =adminDao.DeleteAdmins(aid);
		if(a>0){
			return true;
		}else{
			return false;	
		}
	}
	@Override
	public Admins LoginBackStrone(String aname, String apwd) {
		// TODO Auto-generated method stub
		return adminDao.LoginBackStrone(aname, apwd);
	}
	@Override
	public boolean ExportAdmins() {
		// TODO Auto-generated method stub
		List<Admins> lisAdmin=adminDao.ExportAdmins();
		ExceptExcel except=new ExceptExcel();		
		return except.ExceptAdmin(lisAdmin);		
	}
	@Override
	public String AddExcelAdmins(List<Admins> lisAdmins) {
		//˼·��1. ���ȸ���Ȩ������pname��ȡpermission�������ֵ�����Ϊ�գ�֤��Ȩ�����Ʋ�����
		//      2. ѭ������������ݿ�insert
		IPermissionDao perDao=new PermissionDaoImpl();
		String msg="ok";
		for (int i = 0; i < lisAdmins.size(); i++) {
			Permissions permiss=new Permissions();
			permiss=perDao.FindBypname(lisAdmins.get(i).getPname());
			if(permiss!=null){
				int a_pid=permiss.getPid();
				lisAdmins.get(i).setA_pid(a_pid);
				int a=adminDao.AddAdmins(lisAdmins.get(i));
				if(a>0){
					msg="ok";
				}else{
					msg="excel����ʧ��!";
					break;
				}
			}else{
				msg="excelȨ������"+lisAdmins.get(i).getPname()+"������!";
				break;
			}
		}
		return msg;
	}

}
