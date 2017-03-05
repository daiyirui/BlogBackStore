package com.back.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.common.JDBCUtil;
import com.back.dao.IWeiboDao;
import com.back.po.Users;
import com.back.po.Weibo;


public class WeiboDaoImpl implements IWeiboDao {
	
	@Override
	public List<Weibo> FindAllWeibo(){
		//step5:创建List结合对象
				List<Weibo> lisWeibo=new ArrayList<Weibo>();
				Connection connection = null;
			    PreparedStatement statement = null;
			    try {
					String sql="SELECT * FROM weibo order by wdate desc";
					connection = JDBCUtil.getConn();
			        statement = connection.prepareStatement(sql);
			        ResultSet rs = statement.executeQuery();
					//step6:遍历结果集
						while (rs.next()) {
						   Weibo weibo=new Weibo();		
						   weibo.setWid(rs.getInt("wid"));
						   weibo.setWcontent(rs.getString("wcontent"));
						   weibo.setWdate(rs.getString("wdate"));
						   weibo.setWimage(rs.getString("wimage"));
						   weibo.setWtimes(rs.getInt("wtimes"));
						   weibo.setWremarks(rs.getString("wremarks"));
						   weibo.setWcountcomment(rs.getInt("wcountcomment"));
						   weibo.setW_uid(rs.getInt("w_uid"));
						   weibo.setW_wid(rs.getInt("w_wid"));
						  
						   sql = "SELECT * FROM users where uid=?";
						   statement = connection.prepareStatement(sql);
						   statement.setInt(1, rs.getInt("w_uid"));
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
							    weibo.setUse(use);
						   }
						   lisWeibo.add(weibo);
						}
			   } catch (SQLException e) {
		           e.printStackTrace();
		       } finally {
		           JDBCUtil.closeDB(connection, statement, null);
		       }
			   return lisWeibo;
	}
	
	
}