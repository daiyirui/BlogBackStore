package com.back.dao;


import java.util.List;

import com.back.po.Users;


public interface IUserDao {
   
	//搜索所有的用户
	public List<Users> FindAllUsers();
	
}
