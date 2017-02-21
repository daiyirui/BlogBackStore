package com.back.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.dao.ICollectionDao;
import com.back.dbutil.DBConn;
import com.back.filter.PageBean;
import com.back.po.Collection;
import com.back.po.Users;

public class CollectionDaoImpl implements ICollectionDao {
    DBConn db;
	public CollectionDaoImpl() {
		// TODO Auto-generated constructor stub
		db=new DBConn();
	}

	@Override
	public PageBean FindByPage(String strSQL, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		//step1:创建pagebean对象,为其五个属性赋值
		PageBean pb=new PageBean();
		//step2:sql语句，用来获取weibo表中记录数量 count(*)   SELECT * FROM weibo order by wdate desc
		String strSql1=strSQL;
		strSql1=strSql1.substring(strSql1.toLowerCase().indexOf("from"));
		strSql1 = "select count(*) "+strSql1;
		System.out.println("SqlCount:"+strSql1);
		//step3:执行sql语句得到结果并将其结果赋值给pb的totalRows变量；
		ResultSet rs=db.execQuery(strSql1, new Object[]{});
		try {
			if(rs.next()){
				pb.setTotalRows(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pb.setTotalRows(0);
		}
		//step4:为pb的data属性赋值，首先为获取本页第一条记录设置行标
		int start=(currentPage-1)*pageSize;
		//step5:创建赋值data的sql语句
		strSQL=strSQL+" limit ?,? ";
		rs=db.execQuery(strSQL, new Object[]{start,pageSize});
		//step6:获取data结果集合
		List<Collection> lisColl=new ArrayList<Collection>();
		Collection coll=null;
		try {
			while (rs.next()) {
				coll=new Collection();
				coll.setLid(rs.getInt("lid"));
				coll.setLcontent(rs.getString("lcontent"));
				coll.setLdate(rs.getString("ldate"));
				String s="";//
				if(rs.getString("limages")!=null){
					s=rs.getString("limages").replaceAll("/Microblog/","");
					coll.setLimages(s);
				}else{
					coll.setLimages(rs.getString("limages"));	
				}				
				coll.setLremarks(rs.getString("lremarks"));
				coll.setL_uid(rs.getInt("l_uid"));			
				ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("l_uid")});
				if(re.next()){
					    Users use=new Users();
					    use.setUid(re.getInt("uid"));
					    use.setUname(re.getString("uname"));
					    use.setUpwd(re.getString("upwd"));
					    use.setUnickname(re.getString("unickname"));
					    use.setUsex(re.getString("usex"));
					    use.setUaddress(re.getString("uaddress"));
					    use.setUdate(re.getString("udate"));
					    use.setUpic(re.getString("upic"));
					    use.setUqq(re.getString("uqq"));
					    use.setUedu(re.getString("uedu"));
					    use.setUques(re.getString("uques"));
					    use.setUrealname(re.getString("urealname"));					    
					    use.setUremarks(re.getString("uremarks"));
					    coll.setUse(use);
				}			
				lisColl.add(coll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();
		}
		//step7:赋值相应属性
		pb.setData(lisColl);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:返回结果
		return pb;
	}

	@Override
	public int DeleteMastCollection(String[] uidc) {
		// TODO Auto-generated method stub
		String sql="delete from collection where lid in";
		int a =db.execOther1(sql, uidc);
		return a;
	}

	@Override
	public int DeleteCollection(int lid) {
		// TODO Auto-generated method stub
		String sql="delete from collection where lid=?";
		int a =db.execOther(sql, new Object[]{lid});
		return a;
	}

	@Override
	public int StopCollection(int lid) {
		// TODO Auto-generated method stub
		String sql1="select * from collection where lid=?";
		ResultSet rs=db.execQuery(sql1, new Object[]{lid});
		int a=0;
		try {
			if(rs.next()){
				//证明首先此用户存在，然后查看其备注是否已经被禁用了
				if(rs.getString("lremarks")=="no"){
					//证明已经禁用才用户了
					a= 0;
				}else{
					String sql="update collection set lremarks='no' where lid=?";
					a =db.execOther(sql, new Object[]{lid});				
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
	public int LifeCollection(int lid) {
		// TODO Auto-generated method stub
		String sql1="select * from collection where lid=?";
		ResultSet rs=db.execQuery(sql1, new Object[]{lid});
		int a=0;
		try {
			if(rs.next()){
				//证明首先此用户存在，然后查看其备注是否已经被禁用了
				if(!rs.getString("lremarks").equals("no")){
					//证明已经禁用才用户了
					a= 0;
				}else{
					String sql="update collection set lremarks='null' where lid=?";
					a =db.execOther(sql, new Object[]{lid});				
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
