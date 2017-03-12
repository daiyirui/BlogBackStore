package com.back.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.common.JDBCUtil;
import com.back.dao.IUserDao;
import com.back.po.Users;

public class UserDaoImpl implements IUserDao {
	@Override
	public List<Users> FindAllUsers() {

		String sql = "SELECT * FROM users  order by uid asc";
		List<Users> users = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JDBCUtil.getConn();
			statement = connection.prepareStatement(sql);
			users = new ArrayList<Users>();
			// step3:获取查询结果
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				// step6:获取结果对象
				Users use = new Users();
				use.setUid(rs.getInt("uid"));
				use.setUname(rs.getString("uname"));
				use.setUpwd(rs.getString("upwd"));
				use.setUnickname(rs.getString("unickname"));
				use.setUsex(rs.getString("usex"));
				use.setUaddress(rs.getString("uaddress"));
				use.setUdate(rs.getDate("udate"));
				use.setUpic(rs.getString("upic"));
				use.setUqq(rs.getString("uqq"));
				use.setUemail(rs.getString("uemail"));
				use.setUedu(rs.getString("uedu"));
				use.setUques(rs.getString("uques"));
				use.setUrealname(rs.getString("urealname"));
				use.setUremarks(rs.getString("uremarks"));
				// 结合添加对象
				users.add(use);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.closeDB(connection, statement, null);
		}
	}

	@Override
	public Users FindUsersById(Integer uid) {
		String sql = "SELECT * FROM users where uid=?";
		Connection connection = null;
		PreparedStatement statement = null;
		Users use = new Users();
		try {
			connection = JDBCUtil.getConn();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, uid);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				// step6:获取结果对象
				use.setUid(rs.getInt("uid"));
				use.setUname(rs.getString("uname"));
				use.setUpwd(rs.getString("upwd"));
				use.setUnickname(rs.getString("unickname"));
				use.setUsex(rs.getString("usex"));
				use.setUaddress(rs.getString("uaddress"));
				use.setUdate(rs.getDate("udate"));
				use.setUpic(rs.getString("upic"));
				use.setUqq(rs.getString("uqq"));
				use.setUemail(rs.getString("uemail"));
				use.setUedu(rs.getString("uedu"));
				use.setUques(rs.getString("uques"));
				use.setUrealname(rs.getString("urealname"));
				use.setUremarks(rs.getString("uremarks"));
				// 结合添加对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.closeDB(connection, statement, null);
		}
		return use;
	}

}
