package com.back.biz;

import com.back.filter.PageBean;

public interface IWeiboBiz {
	//��ҳ��ʾ΢����Ϣ
	public PageBean SelectByPage(int currentPage,int pageSize);
	//����ɾ��΢��
	public boolean DeleteWeibo(int wid);
	//����΢��
	public boolean StopWeibo(int wid);
	//������΢��
	public boolean LifeWeibo(int wid);
	//����ɾ��΢����Ϣ
	public boolean DeleteMastWeibo(String[] widc);
}
