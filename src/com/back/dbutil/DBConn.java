package com.back.dbutil;

import java.sql.*;

public class DBConn {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	public void getConnection(){		
		try {
			Class.forName(Config.CLASS_NAME);
			String url=Config.DATABASE_URL+"://"+Config.SERVER_IP+":"+Config.SERVER_PORT+"/"+Config.DATABASE_SID;
			conn=DriverManager.getConnection(url,Config.USERNAME,Config.PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    public void closeConn(){
    	if(rs!=null){
    		try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	if(pstmt!=null){
    		try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}    	
    	if(conn!=null){
    		try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }	
    public int execOther(final String sql,final Object[] params){
    	this.getConnection();	
    	try {
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			int affectedRows=pstmt.executeUpdate();
			return affectedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}    	
    }
    public int execOther1(String sql,final Object[] params){
    	this.getConnection();	
    	try {
    		sql=sql+"(";
    		for (int i = 0; i < params.length; i++) {
				sql+="?,";
			}
    		sql=sql.substring(0, sql.length()-1)+")";
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			int affectedRows=pstmt.executeUpdate();
			return affectedRows;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}    	
    }
    public ResultSet execQuery(final String sql,final Object[] params){
    	this.getConnection();   	
    	try {
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			rs=pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		        	
    }
}
