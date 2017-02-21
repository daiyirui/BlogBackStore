package com.back.dao;

import com.back.filter.PageBean;

public interface ICommentDao {
    //分析显示评论
	public PageBean FindByPage(String strSQL,int currentPage,int pageSize);
	//删除评论
	public int DeleteComment(int cid);
	//批量删除评论
	public int DeleteMastComment(String[] cids);
}
