package com.back.biz;

import com.back.filter.PageBean;
import com.back.po.Bloghot;

public interface IHotBlogBiz {
	 //�������
	public String AddHotBlog(Bloghot hot,String[] items);
	//��ҳ��ʾ
	public PageBean SelectByPage();
	//����ɾ��
	public boolean DeleteMastHotblog(String[] uidc);
	//����ɾ��
	public boolean DeleteHotblog(String btitle);
	//����
	public boolean StopHotblog(String btitle);
	//������
	public String LifeHotblog(String btitle);
}
