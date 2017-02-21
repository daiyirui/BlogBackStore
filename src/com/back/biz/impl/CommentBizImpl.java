package com.back.biz.impl;

import com.back.biz.ICommentBiz;
import com.back.dao.ICommentDao;
import com.back.dao.impl.CommentDaoImpl;
import com.back.filter.PageBean;

public class CommentBizImpl implements ICommentBiz {
    ICommentDao commentDao;
	public CommentBizImpl() {
		// TODO Auto-generated constructor stub
		commentDao=new CommentDaoImpl();
	}

	@Override
	public PageBean SelectByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return commentDao.FindByPage("SELECT * FROM comment", currentPage, pageSize);
		
	}

	@Override
	public boolean DeleteComment(int cid) {
		// TODO Auto-generated method stub
		int a =commentDao.DeleteComment(cid);
		if(a>0){
			return true;
		}else{
		    return false;
		}
	}

	@Override
	public boolean DeleteMastComment(String[] cids) {
		// TODO Auto-generated method stub
		int a =commentDao.DeleteMastComment(cids);
		if(a>0){
			return true;
		}else{
	     	return false;
		}
	}

}
