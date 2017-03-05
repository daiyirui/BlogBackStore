package com.back.dao;

import java.util.List;

import com.back.po.Comment;


public interface ICommentDao {
    //显示评论信息,评论哪一条微博wid
	public  List<Comment>  findByComment(int c_wid);
	//添加评论
	public int InsertComment(Comment comm);
	//删除评论
	public int DeleteComment(int cid);
	
}
