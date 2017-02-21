package com.back.biz;

import java.util.List;

import com.back.filter.PageBean;
import com.back.po.Permissions;

public interface IPermissionBiz {
   //���Ȩ��
	public boolean AddPermission(Permissions per,String[] items);
	//��ҳ��ʾȨ����Ϣ
	public PageBean SelectByPage(int currentPage,int pageSize);
	//��ѯ����������Ϣ
	public Permissions SelectBypid(int pid);
	//����Ȩ����Ϣ
	public boolean UpdatePermissions(Permissions per);
	//ɾ��Ȩ����Ϣ
	public boolean DeletePermissions(int pid);
	//��ȡȫ��Ȩ�޼�����Ϣ
	public List<Permissions> SelectAll();
}
