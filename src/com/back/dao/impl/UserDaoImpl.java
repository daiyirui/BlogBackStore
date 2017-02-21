package com.back.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.dao.IUserDao;
import com.back.dbutil.DBConn;
import com.back.filter.PageBean;
import com.back.po.Users;

public class UserDaoImpl implements IUserDao {
    DBConn db;
    public UserDaoImpl(){
    	db=new DBConn();
    }
	@Override
	public PageBean FindUserByPage(String strSQL, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
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
			pb.setTotalRows(0);
		}		
		//步骤4：需要为pb对象的data属性赋值,首先获取本页的第一条数据的行标
		int start = (currentPage-1)*pageSize;
		//步骤5：创建动态的SQL语句
		strSQL = strSQL+" limit ?,?";
		rs = db.execQuery(strSQL, new Object[]{start,pageSize});
		//步骤6：将获取的结果集进行封装
		List<Users> lstUse = new ArrayList<Users>();
		Users use=null;
		try {
			while(rs.next()){
				use=new Users();
				use.setUid(rs.getInt("uid"));
				use.setUname(rs.getString("uname"));
				use.setUpwd(rs.getString("upwd"));
				use.setUnickname(rs.getString("unickname"));
				use.setUsex(rs.getString("usex"));
				use.setUaddress(rs.getString("uaddress"));
				use.setUdate(rs.getString("udate"));
				use.setUpic(rs.getString("upic"));
				use.setUqq(rs.getString("uqq"));
				use.setUedu(rs.getString("uedu"));
				use.setUques(rs.getString("uques"));
				use.setUrealname(rs.getString("urealname"));
				use.setUremarks(rs.getString("uremarks"));
				lstUse.add(use);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();	
		}		
		//步骤7：将获取到的本页数据赋值给pb对象的data属性
		pb.setData(lstUse);		
		//步骤8：为其余属性赋值,无需为totalPages赋值，以为totalRows和pageSize已经被赋值
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//步骤9：将封装好的pb对象返回
		return pb;
	}
	@Override
	public int DeleteUsers(int uid) {
		// TODO Auto-generated method stub
		String sql="delete from users where uid=?";
		int a =db.execOther(sql, new Object[]{uid});		
		return a;
	}
	@Override
	public int StopUsers(int uid) {
		// TODO Auto-generated method stub
		String sql1="select * from users where uid=?";
		ResultSet rs=db.execQuery(sql1, new Object[]{uid});
		int a=0;
		try {
			if(rs.next()){
				//证明首先此用户存在，然后查看其备注是否已经被禁用了
				if(rs.getString("uremarks")=="no"){
					//证明已经禁用才用户了
					a= 0;
				}else{
					String sql="update users set uremarks='no' where uid=?";
					a =db.execOther(sql, new Object[]{uid});				
				}				
			}			
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			db.closeConn();
		}		
	}
	@Override
	public int LifeUsers(int uid) {
		// TODO Auto-generated method stub
		String sql1="select * from users where uid=?";
		ResultSet rs=db.execQuery(sql1, new Object[]{uid});
		int a=0;
		try {
			if(rs.next()){
				//证明首先此用户存在，然后查看其备注是否已经被禁用了
				if(!rs.getString("uremarks").equals("no")){
					//证明已经禁用才用户了
					a= 0;
				}else{
					String sql="update users set uremarks=null where uid=?";
					a =db.execOther(sql, new Object[]{uid});				
				}				
			}			
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			db.closeConn();
		}
	}
	@Override
	public int DeleteMastUsers(String[] uidc) {
		// TODO Auto-generated method stub
		String sql="delete from users where uid in";
		int a =db.execOther1(sql, uidc);
		return a;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//        PageBean p=new PageBean();
//        IUserDao u=new UserDaoImpl();
//        p=u.FindUserByPage("select * from users", 1,6);
//        System.out.println("count:"+p.getTotalRows()+" - "+p.getTotalPages());
//        List<Users> l=p.getData();
//        for (Users users : l) {
//			System.out.println(users.getUname());
//		}
	}
	
}
