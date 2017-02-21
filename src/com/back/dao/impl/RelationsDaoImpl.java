package com.back.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.dao.IRelationsDao;
import com.back.dbutil.DBConn;
import com.back.filter.PageBean;
import com.back.po.Relations;
import com.back.po.Users;

public class RelationsDaoImpl implements IRelationsDao {
    DBConn db;
	public RelationsDaoImpl() {
		// TODO Auto-generated constructor stub
		db=new DBConn();
	}
  //SELECT distinct g_id,(select count(*) from relations where g_id=b.g_id),(select uname from users where uid=b.g_id),(select udate from users where uid=b.g_id) FROM relations as b order by (select count(*) from relations where g_id=b.g_id) desc
	@Override
	public PageBean FindByPage(String strSQL, int currentPage, int pageSize) {
		//����1������һ��PageBean����		
		PageBean pb = new PageBean();		
		//����3��ִ��SQL���õ�������������ֵ��pb�����totalRows;
		ResultSet rs = db.execQuery("select count(*) from relations", new Object[]{});
		try {
			rs.next();			
			pb.setTotalRows(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//����4����ҪΪpb�����data���Ը�ֵ,���Ȼ�ȡ��ҳ�ĵ�һ�����ݵ��б�
		int start = (currentPage-1)*pageSize;
		//����5��������̬��SQL���
		strSQL = strSQL+" limit ?,?";
		rs = db.execQuery(strSQL, new Object[]{start,pageSize});
		//����6������ȡ�Ľ�������з�װ
		List<Relations> lstRel = new ArrayList<Relations>();
		Relations relation=null;
		try {
			while(rs.next()){
				relation=new Relations();
				relation.setG_id(rs.getInt(1));
				relation.setUsertimes(rs.getInt(2));
				Users use=new Users();
				use.setUname(rs.getString(3));
				use.setUdate(rs.getString(4));
				relation.setUse(use);
				relation.setUselist(this.FindByuid(rs.getInt("g_id")));				
				lstRel.add(relation);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();	
		}		
		//����7������ȡ���ı�ҳ���ݸ�ֵ��pb�����data����
		pb.setData(lstRel);		
		//����8��Ϊ�������Ը�ֵ,����ΪtotalPages��ֵ����ΪtotalRows��pageSize�Ѿ�����ֵ
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//����9������װ�õ�pb���󷵻�
		return pb;
	}

	@Override
	public List<Users> FindByuid(int uid) {
		// TODO Auto-generated method stub
		List<Users> uselist=new ArrayList<Users>();
		Users use=null;
		ResultSet re=db.execQuery("SELECT * FROM relations where g_id=?",new Object[]{uid});
		try {
			while (re.next()) {
				use=new Users();
				ResultSet rt=db.execQuery("select * from users where uid=?",new Object[]{re.getInt("r_id")});
				while (rt.next()) {
					use.setUid(rt.getInt("uid"));
					use.setUname(rt.getString("uname"));
					use.setUpwd(rt.getString("upwd"));
					use.setUnickname(rt.getString("unickname"));
					use.setUsex(rt.getString("usex"));
					use.setUaddress(rt.getString("uaddress"));
					use.setUdate(rt.getString("udate"));
					use.setUpic(rt.getString("upic"));
					use.setUqq(rt.getString("uqq"));
					use.setUedu(rt.getString("uedu"));
					use.setUques(rt.getString("uques"));
					use.setUrealname(rt.getString("urealname"));
					use.setUremarks(rt.getString("uremarks"));
					uselist.add(use);
				}
			}
			return uselist;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//        IRelationsDao r=new RelationsDaoImpl();
//        PageBean p=new PageBean();
//        p=r.FindByPage("SELECT distinct g_id,(select count(*) from relations where g_id=b.g_id),(select uname from users where uid=b.g_id),(select udate from users where uid=b.g_id) FROM relations as b order by (select count(*) from relations where g_id=b.g_id) desc",1,5);
//        List<Relations> l=p.getData();
//        for (Relations relations : l) {
//			List<Users> us=relations.getUselist();
//			for (Users users : us) {
//				System.out.println(users.getUname());
//				
//			}
//			System.out.println("+++++++");
//		}
	}

}
