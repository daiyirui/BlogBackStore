package com.back.dao;

import java.util.List;

import com.back.po.Admins;

public interface IAdminDao {
	public List<Admins> AdminsList();
	public Admins LoginBackStrone(String aname,String apwd);
}
