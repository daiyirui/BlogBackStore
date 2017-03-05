package com.back.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.common.JDBCUtil;
import com.back.dao.ICommentDao;
import com.back.po.Comment;
import com.back.po.Users;
public class CommentDaoImpl implements  ICommentDao {

	@Override
	public List<Comment> findAllComment() {
		//step5:创建List结合对象
				List<Comment> comments=new ArrayList<Comment>();
				Connection connection = null;
			    PreparedStatement statement = null;
			   try {
					String sql="SELECT * FROM comment";
					connection = JDBCUtil.getConn();
			        statement = connection.prepareStatement(sql);
			        ResultSet rs = statement.executeQuery();
					//step6:遍历结果集
						while (rs.next()) {
						   Comment comment=new Comment();		
						   comment.setCid(rs.getInt("cid"));
						   comment.setC_uid(rs.getInt("c_uid"));
						   comment.setC_wid(rs.getInt("c_wid"));
						   comment.setCcontent(rs.getString("ccontent"));
						   comment.setCdate(rs.getString("cdate"));
						   comment.setCimages(rs.getString("cimages"));
						   comment.setCremarks(rs.getString("cremarks"));
						   comment.setC_cid(rs.getInt("c_cid"));
						   comment.setFlag(rs.getInt("flag"));
						   sql = "SELECT * FROM users where uid=?";
						   statement = connection.prepareStatement(sql);
						   statement.setInt(1, rs.getInt("c_uid"));
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
							    comment.setUse(use);
						   }
						   //step6:添加到list集合
						   comments.add(comment);
						}
			   } catch (SQLException e) {
		           e.printStackTrace();
		           return null;
		       } finally {
		           JDBCUtil.closeDB(connection, statement, null);
		       }
			   return comments;
	}
	
	
  
}
