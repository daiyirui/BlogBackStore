package com.back.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.back.common.JDBCUtil;
import com.back.dao.IAdminDao;
import com.back.dbutil.DBConn;
import com.back.po.Admins;
import com.back.po.Permissions;

public class AdminDaoImpl implements IAdminDao {
    DBConn db;    
	public AdminDaoImpl() {
	   db=new DBConn();
	}

	@Override
	public int AddAdmins(Admins admin) {
		// TODO Auto-generated method stub
		String sql="insert into admins values(null,?,?,?,now(),?,?,?)";
		int a =db.execOther(sql, new Object[]{admin.getA_pid(),admin.getAname(),admin.getApwd(),admin.getArealname(),admin.getAsex(),admin.getAremarks()});
		return a;
	}
//	@Override
//	public int AddExcelAdmins(Admins admin) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	@Override
	public int UpdateAdmins(Admins admin) {
		// TODO Auto-generated method stub
		String sql="update admins set aname=?,apwd=?,asex=?,arealname=?,aremarks=?,a_pid=?,adate=now() where aid=?";
		int a =db.execOther(sql, new Object[]{admin.getAname(),admin.getApwd(),admin.getAsex()
				,admin.getArealname(),admin.getAremarks(),admin.getA_pid(),admin.getAid()});		
		return a;
	}
	@Override
	public int DeleteAdmins(int aid) {
		// TODO Auto-generated method stub
		String sql="delete from admins where aid=?";
		int a =db.execOther(sql, new Object[]{aid});		
		return a;
	}
	@Override
	public Admins FindByaid(int aid) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM admins where aid=?";
		Admins admin=new Admins();
		ResultSet rs=db.execQuery(sql, new Object[]{aid});
		try {
			if(rs.next()){
				admin.setAid(rs.getInt("aid"));
				admin.setA_pid(rs.getInt("a_pid"));
				admin.setAname(rs.getString("aname"));
				admin.setApwd(rs.getString("apwd"));
				admin.setAdate(rs.getString("adate"));
				admin.setArealname(rs.getString("arealname"));
				admin.setAsex(rs.getString("asex"));
				admin.setAremarks(rs.getString("aremarks"));
				Permissions permission=new Permissions(); 
				ResultSet re=db.execQuery("SELECT * FROM permissions where pid=?", new Object[]{rs.getInt("a_pid")});
				   if(re.next()){
					   permission.setPid(re.getInt("pid"));
					   permission.setPname(re.getString("pname"));
					   permission.setPcontent(re.getString("pcontent"));
					   permission.setPremarks(re.getString("premarks"));
					   admin.setPermission(permission);
				   }			
			}
			return admin;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}		
	}
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
       IAdminDao ad=new AdminDaoImpl();      
       Admins a=new Admins();
    	   //ad.FindByaid(2);
       int as =ad.UpdateAdmins(a);
       System.out.println(as);
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

	@Override
	public List<Admins> ExportAdmins() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM admins";
		ResultSet rs=db.execQuery(sql, new Object[]{});
		Admins admin=null;
		List<Admins> lstAdmin=new ArrayList<Admins>();
		try {
			while (rs.next()) {
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
				ResultSet re=db.execQuery("SELECT * FROM permissions where pid=?", new Object[]{rs.getInt("a_pid")});
				   if(re.next()){
					   permission.setPid(re.getInt("pid"));
					   permission.setPname(re.getString("pname"));
					   permission.setPcontent(re.getString("pcontent"));
					   permission.setPremarks(re.getString("premarks"));
					   admin.setPermission(permission);
				   }			
				lstAdmin.add(admin);
			}
			return lstAdmin;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}
		
	}
}
