package com.back.dao;

import com.back.filter.PageBean;
import com.back.po.Bloghot;

public interface IHotblogDao {
    //�������
	public int AddHotBlog(Bloghot hot,String[] items);
	//��ҳ��ʾ
	public PageBean FindByPage(String strSQL);
	//����ɾ��
	public int DeleteMastHotblog(String[] uidc);
	//����ɾ��
	public int DeleteHotblog(String btitle);
	//����
	public int StopHotblog(String btitle);
	//������
	public int LifeHotblog(String btitle);
}
