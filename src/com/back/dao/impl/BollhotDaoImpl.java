package com.back.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	@Override
	public int addHot(Bloghot hot) {
		int flag = 0;
		Connection conn = null;
		PreparedStatement stat = null;
        String sql="insert into bloghot(bstate,btitle,bimages,bvote,bremarks) values(?,?,?,?,null)";
	    try {
	        conn = JDBCUtil.getConn();
	        stat = conn.prepareStatement(sql);
	        stat.setInt(1,hot.getBstate());
	        stat.setString(2,hot.getBtitle());
	        stat.setString(3,hot.getBimages());
	        stat.setInt(4,hot.getBvote());
	        flag = stat.executeUpdate();
	    } catch (Exception e) {
	            e.printStackTrace();
	            return flag;
	    } finally {
	            JDBCUtil.closeDB(conn, stat,null);
	    }
		return flag;
	}

	@Override
	public int addItem(Bloghotitem item) {
		int flag = 0;
		Connection conn = null;
		PreparedStatement stat = null;
        String sql="insert into bloghotitem(bitemName,bitemimage,bvote,bid,remark) values(?,?,?,?,?)";
	    try {
	        conn = JDBCUtil.getConn();
	        stat = conn.prepareStatement(sql);
	        stat.setString(1,item.getBitemName());
	        stat.setString(2,item.getBitemimage());
	        stat.setInt(3,item.getBvote());
	        stat.setInt(4,item.getBid());
	        stat.setString(5,item.getBremarks());
	        flag = stat.executeUpdate();
	    } catch (Exception e) {
	            e.printStackTrace();
	            return flag;
	    } finally {
	            JDBCUtil.closeDB(conn, stat,null);
	    }
		return flag;
	}
	
}