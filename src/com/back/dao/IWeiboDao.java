package com.back.dao;

import com.back.filter.PageBean;

public interface IWeiboDao {
    //��ҳ��ʾ΢����Ϣ
	public PageBean FindByPage(String strSQL,int currentPage,int pageSize);
	//����ɾ��΢��
	public int DeleteWeibo(int wid);
	//����΢��
	public int StopWeibo(int wid);
	//������΢��
	public int LifeWeibo(int wid);
	//����ɾ��΢����Ϣ
	public int DeleteMastWeibo(String[] widc);
}
