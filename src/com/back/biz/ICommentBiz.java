package com.back.biz;

import com.back.filter.PageBean;

public interface ICommentBiz {
	//分析显示评论
	public PageBean SelectByPage(int currentPage,int pageSize);
	//删除评论
	public boolean DeleteComment(int cid);
	//批量删除评论
	public boolean DeleteMastComment(String[] cids);
}
