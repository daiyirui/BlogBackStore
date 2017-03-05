package com.back.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.common.JDBCUtil;
import com.back.dao.ICollectionDao;
import com.back.po.Collection;
import com.back.po.Users;

public class CollectionDaoImpl implements ICollectionDao {
    //获取登录用户的所有收藏
	@Override
	public List<Collection> FindAllCollection() {
		List<Collection> collections=new ArrayList<Collection>();
		Connection connection = null;
	    PreparedStatement statement = null;
	    try {
			String sql="SELECT * FROM collection  order by ldate desc";
			connection = JDBCUtil.getConn();
	        statement = connection.prepareStatement(sql);
	        ResultSet rs = statement.executeQuery();
			//step6:遍历结果集
				while (rs.next()) {
					Collection collection=new Collection();		
					collection.setLid(rs.getInt("lid"));
					collection.setL_uid(rs.getInt("l_uid"));
					collection.setLcontent(rs.getString("lcontent"));
					collection.setLdate(rs.getString("ldate"));
					collection.setLimages(rs.getString("limages"));
					collection.setL_wid(rs.getInt("l_wid"));
				    sql = "SELECT * FROM users where uid=?";
				    statement = connection.prepareStatement(sql);
				    statement.setInt(1, rs.getInt("l_uid"));
				   ResultSet re=statement.executeQuery();
				   if(re.next()){
					    Users use=new Users();
					    use.setUid(re.getInt("uid"));
					    use.setUname(re.getString("uname"));
					    use.setUpwd(re.getString("upwd"));
					    use.setUnickname(re.getString("unickname"));
					    use.setUsex(re.getString("usex"));
					    use.setUaddress(re.getString("uaddress"));
					    use.setUdate(re.getDate("udate"));
					    use.setUpic(re.getString("upic"));
					    use.setUqq(re.getString("uqq"));
					    use.setUedu(re.getString("uedu"));
					    use.setUques(re.getString("uques"));
					    use.setUrealname(re.getString("urealname"));
					    use.setUremarks(re.getString("uremarks"));
					    collection.setUse(use);
				   }
				   //step6:添加到list集合
				   collections.add(collection);
				}
	   } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           JDBCUtil.closeDB(connection, statement, null);
       }
	   return collections;
	}

  
}
