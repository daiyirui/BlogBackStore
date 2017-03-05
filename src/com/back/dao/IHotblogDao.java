package com.back.dao;

import com.back.po.Bloghot;

public interface IHotblogDao {
	public int AddHotBlog(Bloghot hot,String[] items);
	public int DeleteMastHotblog(String[] uidc);
	public int DeleteHotblog(String btitle);
	public int StopHotblog(String btitle);
	public int LifeHotblog(String btitle);
}
