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
		//step1:����pagebean����,Ϊ��������Ը�ֵ
		PageBean pb=new PageBean();
		//step2:sql��䣬������ȡweibo���м�¼���� count(*)   SELECT * FROM weibo order by wdate desc
		String strSql1=strSQL;
		strSql1=strSql1.substring(strSql1.toLowerCase().indexOf("from"));
		strSql1 = "select count(*) "+strSql1;
		System.out.println("SqlCount:"+strSql1);
		//step3:ִ��sql���õ��������������ֵ��pb��totalRows������
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
		//step4:Ϊpb��data���Ը�ֵ������Ϊ��ȡ��ҳ��һ����¼�����б�
		int start=(currentPage-1)*pageSize;
		//step5:������ֵdata��sql���
		strSQL=strSQL+" limit ?,? ";
		rs=db.execQuery(strSQL, new Object[]{start,pageSize});
		//step6:��ȡdata�������
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
		//step7:��ֵ��Ӧ����
		pb.setData(lisColl);
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		//step*:���ؽ��
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
				//֤�����ȴ��û����ڣ�Ȼ��鿴�䱸ע�Ƿ��Ѿ���������
				if(rs.getString("lremarks")=="no"){
					//֤���Ѿ����ò��û���
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
				//֤�����ȴ��û����ڣ�Ȼ��鿴�䱸ע�Ƿ��Ѿ���������
				if(!rs.getString("lremarks").equals("no")){
					//֤���Ѿ����ò��û���
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
