package com.back.dao;

import java.util.List;

import com.back.po.Percontent;

public interface IPerContentDao {
   //���Ȩ��items
	public int AddPercontent(int pid,String items);
	//��ȡȨ�޼���
	public List<Percontent> FindByManager(String manager);
    //����ɾ�����º��Ȩ��items
	public int DeletePerContentByObject(int pcid,String[] items);
	//ɾ�����Ȩ��items
	public int DeletePerContentBypcid(int pid);
	
}
