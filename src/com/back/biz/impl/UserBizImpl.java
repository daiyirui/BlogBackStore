package com.back.biz.impl;

import com.back.biz.IUserBiz;
import com.back.dao.IUserDao;
import com.back.dao.impl.UserDaoImpl;
import com.back.filter.PageBean;

public class UserBizImpl implements IUserBiz {
    IUserDao useDao;
	public UserBizImpl() {
		// TODO Auto-generated constructor stub
		useDao=new UserDaoImpl();
	}

	@Override
	public PageBean SelectUserByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return useDao.FindUserByPage("select * from users", currentPage, pageSize);
	}

	@Override
	public boolean DeleteUsers(int uid) {
		// TODO Auto-generated method stub
		int a =useDao.DeleteUsers(uid);
		if(a>0){
			return true;
		}else{
		   return false;
		}
	}

	@Override
	public boolean StopUsers(int uid) {
		// TODO Auto-generated method stub
		int a =useDao.StopUsers(uid);		
		if(a>0){
			return true;
		}else{
		   return false;
		}
	}

	@Override
	public boolean LifeUsers(int uid) {
		// TODO Auto-generated method stub
		int a =useDao.LifeUsers(uid);
		if(a>0){
			return true;
		}else{
		   return false;
		}
	}

	@Override
	public boolean DeleteMastUsers(String[] uidc) {
		// TODO Auto-generated method stub
		int a =useDao.DeleteMastUsers(uidc);
		if(a>0){
			return true;
		}else{
		   return false;
		}
	}

}
