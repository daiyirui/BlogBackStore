package com.back.dao;

import java.util.List;

import com.back.filter.PageBean;
import com.back.po.Users;

public interface IRelationsDao {
   //��ҳ��ʾ
	public PageBean FindByPage(String strSQL,int currentPage,int pageSize);
	//��ȡ��ע���ѽ��
	public List<Users> FindByuid(int uid);
}
