package com.back.dao;

import com.back.filter.PageBean;

public interface ICommentDao {
    //������ʾ����
	public PageBean FindByPage(String strSQL,int currentPage,int pageSize);
	//ɾ������
	public int DeleteComment(int cid);
	//����ɾ������
	public int DeleteMastComment(String[] cids);
}
