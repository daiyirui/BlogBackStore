package com.back.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.dao.IWeiboDao;
import com.back.dbutil.DBConn;
import com.back.filter.PageBean;
import com.back.po.Users;
import com.back.po.Weibo;

public class WeiboDaoImpl implements IWeiboDao {
    DBConn db;
	public WeiboDaoImpl() {
		// TODO Auto-generated constructor stub
		db=new DBConn();
	}

	@Override
	public PageBean FindByPage(String strSQL, int currentPage, int pageSize) {
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
		}		
		//����4����ҪΪpb�����data���Ը�ֵ,���Ȼ�ȡ��ҳ�ĵ�һ�����ݵ��б�
		int start = (currentPage-1)*pageSize;
		//����5��������̬��SQL���
		strSQL = strSQL+" limit ?,?";
		rs = db.execQuery(strSQL, new Object[]{start,pageSize});
		//����6������ȡ�Ľ�������з�װ
		List<Weibo> lstWeibo = new ArrayList<Weibo>();
		Weibo weibo=null;
		try {
			while(rs.next()){
				weibo=new Weibo();		
				   weibo.setWid(rs.getInt("wid"));
				   weibo.setWcontent(rs.getString("wcontent"));
				   weibo.setWdate(rs.getString("wdate"));
				   String s="";
				   if(rs.getString("wimage")!=null){
					   s=rs.getString("wimage").replaceAll("/Microblog/","");
					   weibo.setWimage(s);
				   }else{
					   weibo.setWimage(rs.getString("wimage"));  
				   }				   //
				   weibo.setWtimes(rs.getInt("wtimes"));
				   weibo.setWremarks(rs.getString("wremarks"));
				   weibo.setWcountcomment(rs.getInt("wcountcomment"));
				   weibo.setW_uid(rs.getInt("w_uid"));
				   ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("w_uid")});
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
					    weibo.setUse(use);
				   }			
				lstWeibo.add(weibo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();	
		}		
		//����7������ȡ���ı�ҳ���ݸ�ֵ��pb�����data����
		pb.setData(lstWeibo);		
		//����8��Ϊ�������Ը�ֵ,����ΪtotalPages��ֵ����ΪtotalRows��pageSize�Ѿ�����ֵ
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//����9������װ�õ�pb���󷵻�
		return pb;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        IWeiboDao w=new WeiboDaoImpl();
        int a =w.LifeWeibo(27);
        System.out.println(a);
//        PageBean p=new PageBean();
//        p=w.FindByPage("SELECT * FROM weibo ", 2,6);
//        List<Weibo> l=p.getData();
//        for (Weibo weibo : l) {
//			System.out.println(weibo.getUse().getUname());
//		}
	}

	@Override
	public int DeleteWeibo(int wid) {
		// TODO Auto-generated method stub
		String sql="delete from weibo where wid=?";
		int a =db.execOther(sql, new Object[]{wid});
		return a;
	}

	@Override
	public int StopWeibo(int wid) {
		//��עΪno
		//1.��ȡ��΢����Ϣ��ע�Ƿ�Ϊno		
		String sql1="select * from weibo where wid=?";
		ResultSet rs=db.execQuery(sql1, new Object[]{wid});
		int a=0;
		try {
			if(rs.next()){
				if(rs.getString("wremarks")=="no"){
					//֤����΢����Ϣ�Ѿ�����
					return 0;					
				}else{
					//֤����΢����δ����
					String sql="update weibo set wremarks='no' where wid=?";
					a =db.execOther(sql, new Object[]{wid});
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
	public int LifeWeibo(int wid) {
		// TODO Auto-generated method stub
		//1.��ȡ��΢����Ϣ��ע�Ƿ�Ϊno		
		String sql1="select * from weibo where wid=?";
		ResultSet rs=db.execQuery(sql1, new Object[]{wid});
		int a=0;
		try {
			if(rs.next()){
				if(!rs.getString("wremarks").equals("no")){
					//֤����΢����Ϣ�Ѿ�����
					return 0;					
				}else{
					//֤����΢����δ����
					String sql="update weibo set wremarks=null where wid=?";
					a =db.execOther(sql, new Object[]{wid});
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
	public int DeleteMastWeibo(String[] widc) {
		// TODO Auto-generated method stub
		String sql="delete from weibo where wid in ";
		int a =db.execOther1(sql, widc);
		return a;
	}

}
