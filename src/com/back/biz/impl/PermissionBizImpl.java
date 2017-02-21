package com.back.biz.impl;

import java.util.List;

import com.back.biz.IPermissionBiz;
import com.back.dao.IPerContentDao;
import com.back.dao.IPermissionDao;
import com.back.dao.impl.PerContentDaoImpl;
import com.back.dao.impl.PermissionDaoImpl;
import com.back.filter.PageBean;
import com.back.po.Permissions;

public class PermissionBizImpl implements IPermissionBiz {
    IPermissionDao permissionDao=null;
    IPerContentDao perconDao=null;
    public PermissionBizImpl(){
    	permissionDao=new PermissionDaoImpl();
    	perconDao=new PerContentDaoImpl();
    }
	@Override
	public boolean AddPermission(Permissions per, String[] items) {
		/*
		*1.添加permission表一条记录，
		*2.根据新添加的permission记录，获取其主键ID
		*3.添加具体权限内容表percontent权限记录，添加条数根据权限数量而定。
		*/
		int a= permissionDao.AddPermission(per);
		boolean flag=true;
		if(a>0){
			//证明添加permission表，第一步添加成功
			int pid=(permissionDao.FindByObject(per)).getPid();
			for (int i = 0; i < items.length; i++) {
				if(perconDao.AddPercontent(pid, items[i])!=1){
					flag=false;
					break;
				}
			}
			return flag;
		}else{
		    return false;
		}		
	}
	@Override
	public PageBean SelectByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return permissionDao.FindByPage("SELECT * FROM permissions", currentPage, pageSize);
	}
	@Override
	public Permissions SelectBypid(int pid) {
		// TODO Auto-generated method stub
		return permissionDao.FindBypid(pid);
	}
	@Override
	public boolean UpdatePermissions(Permissions per) {
		// TODO Auto-generated method stub
		int a =permissionDao.UpdatePermissions(per);
		if(a>0){
			return true;
		}else{
		    return false;
		}
	}
	@Override
	public boolean DeletePermissions(int pid) {
		// TODO Auto-generated method stub		
		int a =perconDao.DeletePerContentBypcid(pid);
			//
		if(a>0){
			int b=permissionDao.DeletePermissions(pid);
			if(b>0){
				return true;
			}else{
			   return false;
			}
		}else{
		    return false;
		}
	}
	@Override
	public List<Permissions> SelectAll() {
		// TODO Auto-generated method stub
		return permissionDao.FindAll();
	}
}
