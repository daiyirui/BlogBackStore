package com.back.biz;

import java.util.List;

import com.back.po.Percontent;

public interface IPerContentBiz {
	//��ȡȨ�޼���
	public List<Percontent> SelectByManager(String manager);
	 //����ɾ�����º��Ȩ��items
	public boolean DeletePerContentByObject(int pcid,String[] items);
	//��������Ȩ��items
	public boolean AddPercontent(int pid,List<String> items);
}
