package com.back.dao;

import java.util.List;

import com.back.po.Percontent;

public interface IPerContentDao {
   //添加权限items
	public int AddPercontent(int pid,String items);
	//获取权限集合
	public List<Percontent> FindByManager(String manager);
    //批量删除更新后的权限items
	public int DeletePerContentByObject(int pcid,String[] items);
	//删除外键权限items
	public int DeletePerContentBypcid(int pid);
	
}
