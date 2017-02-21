package com.back.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.dao.IPerContentDao;
import com.back.dbutil.DBConn;
import com.back.po.Percontent;

public class PerContentDaoImpl implements IPerContentDao {
    DBConn db;
    public PerContentDaoImpl(){
    	db=new DBConn();
    }
	@Override
	public int AddPercontent(int pid, String items) {
		// TODO Auto-generated method stub
		String sql="insert into percontent values(null,?,?,null,null)";
		int a =db.execOther(sql, new Object[]{pid,items});
		return a;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        IPerContentDao c=new PerContentDaoImpl();
        List<Percontent> l=c.FindByManager("π‹¿Ì‘±");
        for (Percontent percontent : l) {
			System.out.println(percontent.getPcitems());
		}
	}
	@Override
	public List<Percontent> FindByManager(String manager) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM percontent where pc_pid=(select pid from permissions where pname=?)";
		List<Percontent> lisContent=new ArrayList<Percontent>();
		Percontent cn=null;
		ResultSet rs=db.execQuery(sql, new Object[]{manager});
		try {
			while (rs.next()) {
				cn=new Percontent();
				cn.setPc_pid(rs.getInt("pc_pid"));
				cn.setPcid(rs.getInt("pcid"));
				cn.setPcitems(rs.getString("pcitems"));
				lisContent.add(cn);
			}
			return lisContent;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			db.closeConn();
		}		
	}
	@Override
	public int DeletePerContentByObject(int pcid, String[] items) {
		// TODO Auto-generated method stub
		String sql="delete from percontent where pc_pid="+pcid+" and pcitems not in ";
		int a =db.execOther1(sql, items);
		return a;
	}
	@Override
	public int DeletePerContentBypcid(int pid) {
		// TODO Auto-generated method stub
		String sql="delete from percontent where pc_pid=?";
		int a =db.execOther(sql, new Object[]{pid});
		return a;
	}
}
