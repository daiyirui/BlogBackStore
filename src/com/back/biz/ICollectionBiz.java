package com.back.biz;

import com.back.filter.PageBean;

public interface ICollectionBiz {
	//��ҳ��ʾ
	public PageBean SelectByPage(int currentPage,int pageSize);
	//����ɾ��
	public boolean DeleteMastCollection(String[] uidc);
	//����ɾ��
	public boolean DeleteCollection(int lid);
	//����
	public boolean StopCollection(int lid);
	//������
	public boolean LifeCollection(int lid);
}
