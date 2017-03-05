package com.back.dao;

import java.util.List;

import com.back.po.Admins;

public interface IAdminDao {
	public int AddAdmins(Admins admin);
	public List<Admins> AdminsList();
	public Admins FindByaid(int aid);
	public int UpdateAdmins(Admins admin);
	public int DeleteAdmins(int aid);
	public Admins LoginBackStrone(String aname,String apwd);
	public List<Admins> ExportAdmins();
	//public int AddExcelAdmins(Admins admin);
}
