package com.back.biz;

import com.back.filter.PageBean;

public interface ICommentBiz {
	//������ʾ����
	public PageBean SelectByPage(int currentPage,int pageSize);
	//ɾ������
	public boolean DeleteComment(int cid);
	//����ɾ������
	public boolean DeleteMastComment(String[] cids);
}
