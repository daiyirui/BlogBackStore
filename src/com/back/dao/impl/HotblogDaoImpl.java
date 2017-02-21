package com.back.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.dao.IHotblogDao;
import com.back.dbutil.DBConn;
import com.back.filter.PageBean;
import com.back.po.Bloghot;

public class HotblogDaoImpl implements IHotblogDao{
    DBConn db;
	public HotblogDaoImpl() {
		// TOD
		db=new DBConn();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int AddHotBlog(Bloghot hot, String[] items) {
		//判断btitle是否已经重复
		int a=0;
		String sql="insert into bloghot values(null,?,?,?,0,?)";
		String sql2="select * from bloghot where btitle=?";
		ResultSet rs=db.execQuery(sql2, new Object[]{hot.getBtitle()});
		try {
			if(rs.next()){			
				a=4;//证明重复title
			}else{
				if(hot.getBstate()==1){
					String sql1="select count(distinct btitle) from bloghot where bstate=1";
					rs=db.execQuery(sql1, new Object[]{});
					if(rs.next()){
						if(rs.getInt(1)>=2){
							a=3;//证明已经有两个热议是上线状态
						}else{
							for (int i = 0; i < items.length; i++) {
								if(items[i]!=null){
									a=db.execOther(sql, new Object[]{hot.getBstate(),hot.getBtitle(),items[i],hot.getBremarks()});
								}
							}
						}
					}else{
						
					}
				}else{
					for (int i = 0; i < items.length; i++) {
						if(items[i]!=null){
							a=db.execOther(sql, new Object[]{hot.getBstate(),hot.getBtitle(),items[i],hot.getBremarks()});
						}
					}					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public PageBean FindByPage(String strSQL) {
		//步骤1：创建一个PageBean对象		
		PageBean pb = new PageBean();
		//步骤3：执行SQL语句得到结果并将结果赋值给pb对象的totalRows;
		ResultSet rs = db.execQuery("SELECT count(distinct btitle) FROM bloghot", new Object[]{});
		try {
			rs.next();			
			pb.setTotalRows(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		rs = db.execQuery(strSQL, new Object[]{});
		//步骤6：将获取的结果集进行封装
		List<Bloghot> lstHot = new ArrayList<Bloghot>();
		Bloghot hot=null;
		String title="";
		List<String> item=null;
		List<Integer> vote=null;
		try {
			while(rs.next()){
				if(!title.equals(rs.getString("btitle"))){
					title=rs.getString("btitle");
					hot=new Bloghot();
					hot.setBid(rs.getInt("bid"));
					hot.setBstate(rs.getInt("bstate"));
					hot.setBtitle(rs.getString("btitle"));
					
					hot.setBitems(rs.getString("bitems"));
					item=new ArrayList<String>();
					item.add(rs.getString("bitems"));//添加第一个items
					
					hot.setBvote(rs.getInt("bvote"));
					vote=new ArrayList<Integer>();
					vote.add(rs.getInt("bvote"));
					
					hot.setBremarks(rs.getString("bremarks"));
					lstHot.add(hot);
				}else{
					hot.setBitems(hot.getBitems()+","+rs.getString("bitems"));
					item.add(rs.getString("bitems"));
					vote.add(rs.getInt("bvote"));
				}
				if(item.size()<=5){
					hot.setItem(item);
					hot.setVote(vote);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();	
		}		
		//步骤7：将获取到的本页数据赋值给pb对象的data属性
		pb.setData(lstHot);	
		return pb;
	}

	@Override
	public int DeleteMastHotblog(String[] uidc) {
		String sql="delete from bloghot where bid in";
		int a =db.execOther1(sql, uidc);
		return a;
	}

	@Override
	public int DeleteHotblog(String btitle) {
		// TODO Auto-generated method stub
		String sql="delete from bloghot where btitle=?";
		int a =db.execOther(sql, new Object[]{btitle});
		return a;
	}

	@Override
	public int StopHotblog(String btitle) {
		// TODO Auto-generated method stub
		String sql="update bloghot set bstate=0 where btitle=?";
		int a =db.execOther(sql, new Object[]{btitle});
		return a;
	}

	@Override
	public int LifeHotblog(String btitle) {
		// TODO Auto-generated method stub
		String sql1="SELECT count(distinct btitle) FROM bloghot where bstate=1";
		ResultSet rs=db.execQuery(sql1, new Object[]{});
		int a=0;
		try {
			if(rs.next()){
				if(rs.getInt(1)>=2){
					a= 3;
				}else{
					String sql="update bloghot set bstate=1 where btitle=?";
					a =db.execOther(sql, new Object[]{btitle});				
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

}
