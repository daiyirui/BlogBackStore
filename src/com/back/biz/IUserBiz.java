package com.back.biz;

import com.back.filter.PageBean;

public interface IUserBiz {
	//��ҳ��ʾǰ̨�û�
	public PageBean SelectUserByPage(int currentPage,int pageSize);
	//delete users
	public boolean DeleteUsers(int uid);
	//����ǰ̨�û�
	public boolean StopUsers(int uid);
	//������ǰ̨�û�
	public boolean LifeUsers(int uid);
	//����ɾ��ǰ̨�û�
	public boolean DeleteMastUsers(String[] uidc);
}
