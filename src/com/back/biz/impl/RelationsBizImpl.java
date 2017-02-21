package com.back.biz.impl;

import com.back.biz.IRelationsBiz;
import com.back.dao.IRelationsDao;
import com.back.dao.impl.RelationsDaoImpl;
import com.back.filter.PageBean;

public class RelationsBizImpl implements IRelationsBiz {
    IRelationsDao relDao;
	public RelationsBizImpl() {
		// TODO Auto-generated constructor stub
		relDao=new RelationsDaoImpl();
	}

	@Override
	public PageBean SelectByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return relDao.FindByPage("SELECT distinct g_id,(select count(*) from relations where g_id=b.g_id),(select uname from users where uid=b.g_id),(select udate from users where uid=b.g_id) FROM relations as b order by (select count(*) from relations where g_id=b.g_id) desc", currentPage, pageSize);
	}

}
