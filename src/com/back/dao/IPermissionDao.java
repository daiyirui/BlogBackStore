package com.back.dao;

import java.util.List;

import com.back.filter.PageBean;
import com.back.po.Permissions;

public interface IPermissionDao {
    //��Ӻ�̨�û�Ȩ��
	public int AddPermission(Permissions per);
	//���ݲ������Բ�ѯpermission��������
	public Permissions FindByObject(Permissions per);
	//��ҳ��ʾȨ����Ϣ
	public PageBean FindByPage(String strSQL,int currentPage,int pageSize);
	//��ѯ����������Ϣ
	public Permissions FindBypid(int pid);
	//����Ȩ����Ϣ
	public int UpdatePermissions(Permissions per);
	//ɾ��Ȩ����Ϣ
	public int DeletePermissions(int pid);
	//��ȡȫ��Ȩ�޼�����Ϣ
	public List<Permissions> FindAll();
	//excel���룬����pname��ѯ��������
	public Permissions FindBypname(String pname);
}
