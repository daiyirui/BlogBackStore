package com.back.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.back.common.JDBCUtil;
import com.back.po.Bloghot;
import com.back.po.Bloghotitem;


public class BollhotDaoImpl implements com.back.dao.IBollhotDao {
   
	@Override
	public List<Bloghot> FindAllHot() {
		Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql="SELECT * FROM bloghot where bstate=1 order by bvote desc";
		List<Bloghot> litHot=new ArrayList<Bloghot>();
		conn = JDBCUtil.getConn();
	        try {
	            stat = conn.createStatement();
	            rs = stat.executeQuery(sql);
	            while (rs.next()) {
	            	Bloghot hot=new Bloghot();
			        hot.setBid(rs.getInt("bid"));
			        hot.setBstate(rs.getInt("bstate"));
					hot.setBtitle(rs.getString("btitle"));
					hot.setBimages(rs.getString("bimages"));
					hot.setBvote(rs.getInt("bvote"));
					hot.setBremarks(rs.getString("bremarks"));
					hot.setBitems(FindAllHotItem(rs.getInt("bid")));
					litHot.add(hot);
				}
				return litHot;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            JDBCUtil.closeDB(conn, stat, rs);
	        }
	}
	public List<Bloghotitem> FindAllHotItem(Integer bid) {
		Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        String sql="SELECT * FROM bloghotitem where bid="+bid;
		List<Bloghotitem> bloghotitems=new ArrayList<Bloghotitem>();
		conn = JDBCUtil.getConn();
	    try {
	            stat = conn.createStatement();
	            rs = stat.executeQuery(sql);
	            while (rs.next()) {
	            	Bloghotitem item=new Bloghotitem();
	            	item.setBloghotitemid(rs.getInt("bloghotitemid"));
			        item.setBid(rs.getInt("bid"));
			        item.setBitemimage(rs.getString("bitemimage"));
			        item.setBitemName(rs.getString("bitemName"));
			        item.setBvote(rs.getInt("bvote"));
			        item.setBremarks(rs.getString("remark"));
			        bloghotitems.add(item);
				}
				return bloghotitems;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        } finally {
	            JDBCUtil.closeDB(conn, stat, rs);
	   }
  }
}