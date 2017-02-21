package com.back.dao;

import com.back.filter.PageBean;

public interface ICollectionDao {
  //��ҳ��ʾ
	public PageBean FindByPage(String strSQL,int currentPage,int pageSize);
	//����ɾ��
	public int DeleteMastCollection(String[] uidc);
	//����ɾ��
	public int DeleteCollection(int lid);
	//����
	public int StopCollection(int lid);
	//������
	public int LifeCollection(int lid);
}
