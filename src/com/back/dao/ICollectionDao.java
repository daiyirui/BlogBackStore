package com.back.dao;

import java.util.List;

import com.back.po.Collection;


public interface ICollectionDao {
   
	//获取我所有收藏的微博
	public List<Collection> FindAllCollection();
	
}
