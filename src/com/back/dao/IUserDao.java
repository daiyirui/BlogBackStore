package com.back.dao;

import com.back.filter.PageBean;

public interface IUserDao {
    //��ҳ��ʾǰ̨�û�
	public PageBean FindUserByPage(String strSQL,int currentPage,int pageSize);
	//delete users
	public int DeleteUsers(int uid);
	//����ǰ̨�û�
	public int StopUsers(int uid);
	//������ǰ̨�û�
	public int LifeUsers(int uid);
	//����ɾ��ǰ̨�û�
	public int DeleteMastUsers(String[] uidc);
}
