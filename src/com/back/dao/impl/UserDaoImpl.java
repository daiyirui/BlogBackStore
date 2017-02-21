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
		//����1������һ��PageBean����		
		PageBean pb = new PageBean();
		//����2������һ��SQL��䣬������ȡemp���м�¼�ĸ���
		String strSQL1 = strSQL;
		strSQL1 = strSQL1.substring(strSQL1.toLowerCase().indexOf("from"));
		strSQL1 = "select count(*) "+strSQL1;
		//����3��ִ��SQL���õ�������������ֵ��pb�����totalRows;
		ResultSet rs = db.execQuery(strSQL1, new Object[]{});
		try {
			rs.next();			
			pb.setTotalRows(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pb.setTotalRows(0);
		}		
		//����4����ҪΪpb�����data���Ը�ֵ,���Ȼ�ȡ��ҳ�ĵ�һ�����ݵ��б�
		int start = (currentPage-1)*pageSize;
		//����5��������̬��SQL���
		strSQL = strSQL+" limit ?,?";
		rs = db.execQuery(strSQL, new Object[]{start,pageSize});
		//����6������ȡ�Ľ�������з�װ
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
		//����7������ȡ���ı�ҳ���ݸ�ֵ��pb�����data����
		pb.setData(lstUse);		
		//����8��Ϊ�������Ը�ֵ,����ΪtotalPages��ֵ����ΪtotalRows��pageSize�Ѿ�����ֵ
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//����9������װ�õ�pb���󷵻�
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
				//֤�����ȴ��û����ڣ�Ȼ��鿴�䱸ע�Ƿ��Ѿ���������
				if(rs.getString("uremarks")=="no"){
					//֤���Ѿ����ò��û���
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
				//֤�����ȴ��û����ڣ�Ȼ��鿴�䱸ע�Ƿ��Ѿ���������
				if(!rs.getString("uremarks").equals("no")){
					//֤���Ѿ����ò��û���
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
