package com.back.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.dao.IPermissionDao;
import com.back.dbutil.DBConn;
import com.back.filter.PageBean;
import com.back.po.Percontent;
import com.back.po.Permissions;

public class PermissionDaoImpl implements IPermissionDao {
    DBConn db;
    public PermissionDaoImpl(){
    	db=new DBConn();
    }
	@Override
	public int AddPermission(Permissions per) {
		// TODO Auto-generated method stub
		String sql="insert into permissions values(null,?,?,?)";
		int a = db.execOther(sql, new Object[]{per.getPname(),per.getPcontent(),per.getPremarks()});
		return a;
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
		List<Permissions> lstPer = new ArrayList<Permissions>();
		Permissions permiss=null;
		try {
			while(rs.next()){
				permiss=new Permissions();
				permiss.setPid(rs.getInt("pid"));
				permiss.setPname(rs.getString("pname"));
				permiss.setPcontent(rs.getString("pcontent"));
				permiss.setPremarks(rs.getString("premarks"));
				List<Percontent> content=new ArrayList<Percontent>();
				Percontent con=null;
				ResultSet re=db.execQuery("SELECT * FROM percontent where pc_pid=? order by pc_pid", new Object[]{rs.getInt("pid")});
				   while(re.next()){
					   con=new Percontent();
					   con.setPcid(re.getInt("pcid"));
					   con.setPc_pid(re.getInt("pc_pid"));
					   con.setPcitems(re.getString("pcitems"));
					   con.setPcurl(re.getString("pcurl"));
					   con.setPcremarks(re.getString("pcremarks"));
					   content.add(con);					   
				   }			
				 permiss.setContent(content);
				 lstPer.add(permiss);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();	
		}		
		//����7������ȡ���ı�ҳ���ݸ�ֵ��pb�����data����
		pb.setData(lstPer);		
		//����8��Ϊ�������Ը�ֵ,����ΪtotalPages��ֵ����ΪtotalRows��pageSize�Ѿ�����ֵ
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//����9������װ�õ�pb���󷵻�
		return pb;
	}
	@Override
	public Permissions FindBypid(int pid) {
		// TODO Auto-generated method stub
		String sql="select * FROM permissions where pid=?";
		ResultSet rs=db.execQuery(sql, new Object[]{pid});
		Permissions permiss=new Permissions();
		try {
			if(rs.next()){
				permiss.setPid(rs.getInt("pid"));
				permiss.setPname(rs.getString("pname"));
				permiss.setPcontent(rs.getString("pcontent"));
				permiss.setPremarks(rs.getString("premarks"));
				List<Percontent> content=new ArrayList<Percontent>();
				Percontent con=null;
				ResultSet re=db.execQuery("SELECT * FROM percontent where pc_pid=? order by pc_pid", new Object[]{rs.getInt("pid")});
				   while(re.next()){
					   con=new Percontent();
					   con.setPcid(re.getInt("pcid"));
					   con.setPc_pid(re.getInt("pc_pid"));
					   con.setPcitems(re.getString("pcitems"));
					   con.setPcurl(re.getString("pcurl"));
					   con.setPcremarks(re.getString("pcremarks"));
					   content.add(con);					   
				   }			
				 permiss.setContent(content);
				 return permiss;
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
	public Permissions FindByObject(Permissions per) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM permissions where pname=? and pcontent=?";
		ResultSet rs=db.execQuery(sql, new Object[]{per.getPname(),per.getPcontent()});
		try {
			if(rs.next()){
				Permissions pers=new Permissions();
				pers.setPid(rs.getInt("pid"));
				pers.setPname(rs.getString("pname"));
				pers.setPcontent(rs.getString("pcontent"));
				pers.setPremarks(rs.getString("premarks"));
				return pers;
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
	public int UpdatePermissions(Permissions per) {
		// TODO Auto-generated method stub
		String sql="update permissions set pname=?,pcontent=?,premarks=? where pid=?";
		int a =db.execOther(sql, new Object[]{per.getPname(),per.getPcontent(),per.getPremarks(),per.getPid()});
		return a;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//        Permissions per=new Permissions();
//        per.setPname("��������Ա");
//        per.setPcontent("ȫ��Ȩ��");
//        per.setPid(4);
//        per.setPremarks("4");
//        IPermissionDao d=new PermissionDaoImpl();
//        int a=d.UpdatePermissions(per);
//        Permissions p =d.FindByObject(per);
//        System.out.println(p.getPid());
//        PageBean p=d.FindByPage("SELECT * FROM permissions ",1,1);
//        List<Permissions> s=p.getData();
//        for (Permissions permissions : s) {
//        	for (Percontent con : permissions.getContent()) {
//        		System.out.println(" f "+con.getPcitems());	
//			}
//			
//		}
        
	}
	@Override
	public int DeletePermissions(int pid) {
		String sql="delete from permissions where pid=?";
		int a = db.execOther(sql, new Object[]{pid});
		return a;
	}
	@Override
	public List<Permissions> FindAll() {
		// TODO Auto-generated method stub
		String sql="select * from permissions";
		ResultSet rs=db.execQuery(sql, new Object[]{});
		List<Permissions> lstPer = new ArrayList<Permissions>();
		Permissions permiss=null;
		try {
			while(rs.next()){
				permiss=new Permissions();
				permiss.setPid(rs.getInt("pid"));
				permiss.setPname(rs.getString("pname"));
				permiss.setPcontent(rs.getString("pcontent"));
				permiss.setPremarks(rs.getString("premarks"));
				List<Percontent> content=new ArrayList<Percontent>();
				Percontent con=null;
				ResultSet re=db.execQuery("SELECT * FROM percontent where pc_pid=? order by pc_pid", new Object[]{rs.getInt("pid")});
				   while(re.next()){
					   con=new Percontent();
					   con.setPcid(re.getInt("pcid"));
					   con.setPc_pid(re.getInt("pc_pid"));
					   con.setPcitems(re.getString("pcitems"));
					   con.setPcurl(re.getString("pcurl"));
					   con.setPcremarks(re.getString("pcremarks"));
					   content.add(con);					   
				   }			
				 permiss.setContent(content);
				 lstPer.add(permiss);
			}
			return lstPer;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}		
	}
	@Override
	public Permissions FindBypname(String pname) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM permissions where pname=?";
		ResultSet rs=db.execQuery(sql, new Object[]{pname});
		Permissions permiss=new Permissions();
		try {
			if(rs.next()){
				permiss.setPid(rs.getInt("pid"));
				permiss.setPname(rs.getString("pname"));
				permiss.setPcontent(rs.getString("pcontent"));
				permiss.setPremarks(rs.getString("premarks"));
				List<Percontent> content=new ArrayList<Percontent>();
				Percontent con=null;
				ResultSet re=db.execQuery("SELECT * FROM percontent where pc_pid=? order by pc_pid", new Object[]{rs.getInt("pid")});
				   while(re.next()){
					   con=new Percontent();
					   con.setPcid(re.getInt("pcid"));
					   con.setPc_pid(re.getInt("pc_pid"));
					   con.setPcitems(re.getString("pcitems"));
					   con.setPcurl(re.getString("pcurl"));
					   con.setPcremarks(re.getString("pcremarks"));
					   content.add(con);					   
				   }			
				 permiss.setContent(content);
			}
			return permiss;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}		
	}
}