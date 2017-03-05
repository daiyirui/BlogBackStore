package com.back.dao;

import java.util.List;

import com.back.po.Permissions;

public interface IPermissionDao {
	public int AddPermission(Permissions per);
	public Permissions FindByObject(Permissions per);
	public Permissions FindBypid(int pid);
	public int UpdatePermissions(Permissions per);
	public int DeletePermissions(int pid);
	public List<Permissions> FindAll();
	public Permissions FindBypname(String pname);
}
