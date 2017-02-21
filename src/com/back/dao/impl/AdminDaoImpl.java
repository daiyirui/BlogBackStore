package com.back.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.dao.IAdminDao;
import com.back.dbutil.DBConn;
import com.back.filter.PageBean;
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
	public PageBean AdminsList(String strSQL, int currentPage, int pageSize) {
		//步骤1：创建一个PageBean对象		
		PageBean pb = new PageBean();
		//步骤2：创建一个SQL语句，用来获取emp表中记录的个数
		String strSQL1 = strSQL;
		strSQL1 = strSQL1.substring(strSQL1.toLowerCase().indexOf("from"));
		strSQL1 = "select count(*) "+strSQL1;
		//步骤3：执行SQL语句得到结果并将结果赋值给pb对象的totalRows;
		ResultSet rs = db.execQuery(strSQL1, new Object[]{});
		try {
			rs.next();			
			pb.setTotalRows(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//步骤4：需要为pb对象的data属性赋值,首先获取本页的第一条数据的行标
		int start = (currentPage-1)*pageSize;
		//步骤5：创建动态的SQL语句
		strSQL = strSQL+" limit ?,?";
		rs = db.execQuery(strSQL, new Object[]{start,pageSize});
		//步骤6：将获取的结果集进行封装
		List<Admins> lstAdmin = new ArrayList<Admins>();
		Admins admin=null;
		try {
			while(rs.next()){
				admin=new Admins();
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
				lstAdmin.add(admin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();	
		}		
		//步骤7：将获取到的本页数据赋值给pb对象的data属性
		pb.setData(lstAdmin);		
		//步骤8：为其余属性赋值,无需为totalPages赋值，以为totalRows和pageSize已经被赋值
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//步骤9：将封装好的pb对象返回
		return pb;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
       IAdminDao ad=new AdminDaoImpl();      
       Admins a=new Admins(5,3,"hello5","444","","hello9","女",null);
    	   //ad.FindByaid(2);
       int as =ad.UpdateAdmins(a);
       System.out.println(as);
	}

	@Override
	public Admins LoginBackStrone(String aname, String apwd) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM admins where aname=? and apwd=?";
		ResultSet rs=db.execQuery(sql, new Object[]{aname,apwd});
		Admins admin=new Admins();
		try {
			if(rs.next()){
				admin=new Admins();
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
				return admin;
			}else{
			    return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
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
