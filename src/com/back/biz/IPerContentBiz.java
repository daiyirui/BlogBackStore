package com.back.biz;

import java.util.List;

import com.back.po.Percontent;

public interface IPerContentBiz {
	//获取权限集合
	public List<Percontent> SelectByManager(String manager);
	 //批量删除更新后的权限items
	public boolean DeletePerContentByObject(int pcid,String[] items);
	//更新新增权限items
	public boolean AddPercontent(int pid,List<String> items);
}
