package com.back.biz.impl;

import java.util.List;

import com.back.biz.IPerContentBiz;
import com.back.dao.IPerContentDao;
import com.back.dao.impl.PerContentDaoImpl;
import com.back.po.Percontent;

public class PerContentBizImpl implements IPerContentBiz {
    IPerContentDao perconDao;
    public PerContentBizImpl(){
    	perconDao=new PerContentDaoImpl();
    }
	@Override
	public List<Percontent> SelectByManager(String manager) {
		// TODO Auto-generated method stub
		return perconDao.FindByManager(manager);
	}
	@Override
	public boolean DeletePerContentByObject(int pcid, String[] items) {
		// TODO Auto-generated method stub
		int a =perconDao.DeletePerContentByObject(pcid, items);
		if(a>0){
			return true;
		}else{
		    return false;
		}
	}
	@Override
	public boolean AddPercontent(int pid, List<String> items) {
		// TODO Auto-generated method stub
		boolean s=true;
        for (int i = 0; i < items.size(); i++) {
			int a=perconDao.AddPercontent(pid, items.get(i));
			if(a>0){
				s=true;
			}else{
				s=false;
				break;
			}
		}		
		return s;
	}
}