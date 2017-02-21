package com.back.biz.impl;

import com.back.biz.ICollectionBiz;
import com.back.dao.ICollectionDao;
import com.back.dao.impl.CollectionDaoImpl;
import com.back.filter.PageBean;



public class CollectionBizImpl implements ICollectionBiz {
    ICollectionDao colDao;
	public CollectionBizImpl() {
		// TODO Auto-generated constructor stub
		colDao=new CollectionDaoImpl();
	}

	@Override
	public PageBean SelectByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return colDao.FindByPage("SELECT * FROM collection", currentPage, pageSize);
	}

	@Override
	public boolean DeleteMastCollection(String[] uidc) {
		// TODO Auto-generated method stub
		int a =colDao.DeleteMastCollection(uidc);
		if(a>0){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public boolean DeleteCollection(int lid) {
		// TODO Auto-generated method stub
		int a =colDao.DeleteCollection(lid);
		if(a>0){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public boolean StopCollection(int lid) {
		// TODO Auto-generated method stub
		int a =colDao.StopCollection(lid);
		if(a>0){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public boolean LifeCollection(int lid) {
		// TODO Auto-generated method stub
		int a =colDao.LifeCollection(lid);
		if(a>0){
			return true;
		}else{
		return false;
		}
	}

}
