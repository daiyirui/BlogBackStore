package com.back.biz;

import com.back.filter.PageBean;

public interface IRelationsBiz {
	//��ҳ��ʾ
	public PageBean SelectByPage(int currentPage,int pageSize);
}
