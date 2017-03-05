package com.back.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.common.JDBCUtil;
import com.back.dao.IAdminDao;
import com.back.po.Admins;
import com.back.po.Permissions;

public class AdminDaoImpl implements IAdminDao {

	@Override
	public List<Admins> AdminsList() {
		List<Admins> lstAdmin = new ArrayList<Admins>();
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet rs=null;
		try {
			connection = JDBCUtil.getConn();
			String sql="SELECT * FROM admins";
			statement = connection.prepareStatement(sql);
		    rs=statement.executeQuery();
			while(rs.next()){
				Admins admin=new Admins();
				admin.setAid(rs.getInt("aid"));
				admin.setA_pid(rs.getInt("a_pid"));
				admin.setAname(rs.getString("aname"));
				admin.setApwd(rs.getString("apwd"));
				admin.setAdate(rs.getDate("adate"));
				admin.setArealname(rs.getString("arealname"));
				admin.setAsex(rs.getString("asex"));
				admin.setAremarks(rs.getString("aremarks"));
				Permissions permission=new Permissions(); 
				String sql1="SELECT * FROM permissions where pid=?";
				statement = connection.prepareStatement(sql1);
				statement.setInt(1, rs.getInt("a_pid"));
				ResultSet re=statement.executeQuery();
				   if(re.next()){
					   permission.setPid(re.getInt("pid"));
					   permission.setPname(re.getString("pname"));
					   permission.setPcontent(re.getString("pcontent"));
					   permission.setPremarks(re.getString("premarks"));
					   admin.setPermission(permission);
				   }
				   lstAdmin.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			JDBCUtil.closeDB(connection, statement, rs);
		}
		return lstAdmin;
	}


	@Override
	public Admins LoginBackStrone(String aname, String apwd) {
		PreparedStatement statement = null;
		Connection connection = null;
		ResultSet rs=null;
		try {
			connection = JDBCUtil.getConn();
			String sql="SELECT * FROM admins where aname=? and apwd=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, aname);
			statement.setString(2, apwd);
		    rs=statement.executeQuery();
			Admins admin=null;
			if(rs.next()){
				admin=new Admins();
				admin.setAid(rs.getInt("aid"));
				admin.setA_pid(rs.getInt("a_pid"));
				admin.setAname(rs.getString("aname"));
				admin.setApwd(rs.getString("apwd"));
				admin.setAdate(rs.getDate("adate"));
				admin.setArealname(rs.getString("arealname"));
				admin.setAsex(rs.getString("asex"));
				admin.setAremarks(rs.getString("aremarks"));
				Permissions permission=new Permissions(); 
				String sql1="SELECT * FROM permissions where pid=?";
				statement = connection.prepareStatement(sql1);
				statement.setInt(1, rs.getInt("a_pid"));
				ResultSet re=statement.executeQuery();
				   if(re.next()){
					   permission.setPid(re.getInt("pid"));
					   permission.setPname(re.getString("pname"));
					   permission.setPcontent(re.getString("pcontent"));
					   permission.setPremarks(re.getString("premarks"));
					   admin.setPermission(permission);
				   }
				return admin;
			}else{
			    return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			JDBCUtil.closeDB(connection, statement, rs);
		}
	}


}
