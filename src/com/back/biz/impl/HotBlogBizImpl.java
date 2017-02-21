package com.back.biz.impl;

import com.back.biz.IHotBlogBiz;
import com.back.dao.IHotblogDao;
import com.back.dao.impl.HotblogDaoImpl;
import com.back.filter.PageBean;
import com.back.po.Bloghot;

public class HotBlogBizImpl implements IHotBlogBiz {
    IHotblogDao hotDao;
	public HotBlogBizImpl() {
		// TODO Auto-generated constructor stub
		hotDao=new HotblogDaoImpl();
	}

	@Override
	public String AddHotBlog(Bloghot hot, String[] items) {
		// TODO Auto-generated method stub
		String mag="";
		   int a=hotDao.AddHotBlog(hot, items);
		   if(a==4){
			   mag="�˱����Ѿ���ӹ���";
		   }else if(a==3){
			   mag="�����������ߵ�΢������";
		   }else if(a==0){
			   mag="���΢������ʧ��";
		   }else{
			   mag="ok";
			   
		   } 
		return mag;
	}

	@Override
	public PageBean SelectByPage() {
		// TODO Auto-generated method stub
		return hotDao.FindByPage("select * from bloghot");
	}

	@Override
	public boolean DeleteMastHotblog(String[] uidc) {
		// TODO Auto-generated method stub\
		int a =hotDao.DeleteMastHotblog(uidc);
		if(a>0){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public boolean DeleteHotblog(String btitle) {
		// TODO Auto-generated method stub
		int a =hotDao.DeleteHotblog(btitle);
		if(a>0){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public boolean StopHotblog(String btitle) {
		// TODO Auto-generated method stub
		int a =hotDao.StopHotblog(btitle);
		if(a>0){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public String LifeHotblog(String btitle) {
		// TODO Auto-generated method stub
		int a=hotDao.LifeHotblog(btitle);
		if(a==0){
			return btitle+"����ʧ��";
		}else if(a==3){
			return "�Ѿ����������ߵ�΢������";
		}else{
			return "ok";	
		}
		
	}

}
