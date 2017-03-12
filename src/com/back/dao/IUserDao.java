package com.back.dao;


import java.util.List;

import com.back.po.Users;


public interface IUserDao {
   
	//搜索所有的用户
	public List<Users> FindAllUsers();
	//通过User id查找用户
	public Users FindUsersById(Integer uid);
}
