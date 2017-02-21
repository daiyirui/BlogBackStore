package com.back.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.back.dao.ICommentDao;
import com.back.dbutil.DBConn;
import com.back.filter.PageBean;
import com.back.po.Comment;
import com.back.po.Users;
import com.back.po.Weibo;

public class CommentDaoImpl implements ICommentDao {
    DBConn db;
	public CommentDaoImpl() {
		// TODO Auto-generated constructor stub
		db=new DBConn();
	}

	@Override
	public PageBean FindByPage(String strSQL, int currentPage, int pageSize) {
		//步骤1：创建一个PageBean对象		
		PageBean pb = new PageBean();
		//步骤2：创建一个SQL语句，用来获取emp表中记录的个数
		String strSQL1 = strSQL;
		strSQL1 = strSQL1.substring(strSQL1.toLowerCase().indexOf("from"));
		strSQL1 = "select count(*) "+strSQL1;
		//步骤3：执行SQL语句得到结果并将结果赋值给pb对象的totalRows;
		ResultSet rs = db.execQuery(strSQL1, new Object[]{});
		try {
			rs.next();			
			pb.setTotalRows(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		//步骤4：需要为pb对象的data属性赋值,首先获取本页的第一条数据的行标
		int start = (currentPage-1)*pageSize;
		//步骤5：创建动态的SQL语句
		strSQL = strSQL+" limit ?,?";
		rs = db.execQuery(strSQL, new Object[]{start,pageSize});
		//步骤6：将获取的结果集进行封装
		List<Comment> lstComment = new ArrayList<Comment>();
		Comment comm=null;
		try {
			while(rs.next()){
				comm=new Comment();
				comm.setCid(rs.getInt("cid"));
				comm.setCcontent(rs.getString("ccontent"));
				comm.setCdate(rs.getString("cdate"));
				comm.setCremarks(rs.getString("cremarks"));
//				comm.setCimages(rs.getString("cimages"));
				String s="";
				if(rs.getString("cimages")!=null){
					s=rs.getString("cimages").replaceAll("/Microblog/","");
					comm.setCimages(s);
				}else{
					comm.setCimages(rs.getString("cimages"));
				}
				comm.setC_uid(rs.getInt("c_uid"));
				comm.setC_wid(rs.getInt("c_wid"));			
				ResultSet re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{rs.getInt("c_uid")});
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
					    comm.setUse(use);
				}
				re=db.execQuery("SELECT * FROM weibo where wid=?", new Object[]{rs.getInt("c_wid")});
				if(re.next()){
					Weibo weibo=new Weibo();		
					weibo.setWid(re.getInt("wid"));
					weibo.setWcontent(re.getString("wcontent"));
					weibo.setWdate(re.getString("wdate"));
					weibo.setWimage(re.getString("wimage"));
					weibo.setWtimes(re.getInt("wtimes"));
					weibo.setWremarks(re.getString("wremarks"));
					weibo.setWcountcomment(re.getInt("wcountcomment"));
					weibo.setW_uid(re.getInt("w_uid"));
					re=db.execQuery("SELECT * FROM users where uid=?", new Object[]{re.getInt("w_uid")});
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
					comm.setWeibo(weibo);
				}				
				lstComment.add(comm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			db.closeConn();	
		}		
		//步骤7：将获取到的本页数据赋值给pb对象的data属性
		pb.setData(lstComment);		
		//步骤8：为其余属性赋值,无需为totalPages赋值，以为totalRows和pageSize已经被赋值
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);				
		//步骤9：将封装好的pb对象返回
		return pb;
	}

	@Override
	public int DeleteComment(int cid) {
		// TODO Auto-generated method stub
		String sql="delete from comment where cid=?";
		int a =db.execOther(sql, new Object[]{cid});
		return a;
	}

	@Override
	public int DeleteMastComment(String[] cids) {
		// TODO Auto-generated method stub
		String sql="delete from comment where cid in";
		int a =db.execOther1(sql, cids);
		return a;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//        ICommentDao c=new CommentDaoImpl();
//        PageBean p=new PageBean();
//        p=c.FindByPage("SELECT * FROM comment",1,3);
//        List<Comment> l=p.getData();
//        for (Comment comment : l) {
//			System.out.println(comment.getWeibo().getWcontent());
//		}        
	}
}
