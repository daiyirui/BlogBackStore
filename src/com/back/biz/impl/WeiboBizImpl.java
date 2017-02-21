package com.back.biz.impl;

import com.back.biz.IWeiboBiz;
import com.back.dao.IWeiboDao;
import com.back.dao.impl.WeiboDaoImpl;
import com.back.filter.PageBean;

public class WeiboBizImpl implements IWeiboBiz {
    IWeiboDao weiboDao;
	public WeiboBizImpl() {
		// TODO Auto-generated constructor stub
		weiboDao=new WeiboDaoImpl();
	}

	@Override
	public PageBean SelectByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return weiboDao.FindByPage("SELECT * FROM weibo ", currentPage, pageSize);		
	}

	@Override
	public boolean DeleteWeibo(int wid) {
		// TODO Auto-generated method stub
		int a =weiboDao.DeleteWeibo(wid);
		if(a>0){
			return true;
		}else{
		   return false;
		}
	}

	@Override
	public boolean StopWeibo(int wid) {
		// TODO Auto-generated method stub
		int a =weiboDao.StopWeibo(wid);
		if(a>0){
			return true;
		}else{
		   return false;
		}
	}

	@Override
	public boolean LifeWeibo(int wid) {
		// TODO Auto-generated method stub
		int a =weiboDao.LifeWeibo(wid);
		if(a>0){
			return true;
		}else{
		   return false;
		}
	}

	@Override
	public boolean DeleteMastWeibo(String[] widc) {
		// TODO Auto-generated method stub
		int a =weiboDao.DeleteMastWeibo(widc);
		if(a>0){
			return true;
		}else{
			return false; 	
		}
		
	}

	
}
