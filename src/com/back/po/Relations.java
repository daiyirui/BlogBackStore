package com.back.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Relations implements Serializable {
   private Integer rid;
   private Integer r_id;
   private Integer g_id;
   private Integer rstate;
   private String remarks;
   
   private int usertimes;//关注的人数
   private List<Users> uselist=new ArrayList<Users>();
   private Users use=new Users();
   

public Relations(){}
   public Relations(Integer rid, Integer r_id, Integer g_id, Integer rstate,String remarks){
	   this.rid=rid;
	   this.r_id=r_id;
	   this.g_id=g_id;
	   this.rstate=rstate;
	   this.remarks=remarks;
   }
   public Relations( Integer r_id, Integer g_id, Integer rstate,String remarks){	 
	   this.r_id=r_id;
	   this.g_id=g_id;
	   this.rstate=rstate;
	   this.remarks=remarks;
   }
   public int getUsertimes() {
		return usertimes;
	}
	public void setUsertimes(int usertimes) {
		this.usertimes = usertimes;
	}
	public List<Users> getUselist() {
		return uselist;
	}
	public void setUselist(List<Users> uselist) {
		this.uselist = uselist;
	}
	public Users getUse() {
		return use;
	}
	public void setUse(Users use) {
		this.use = use;
	}
	public String getRemarks() {
	    return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
	public Integer getG_id() {
		return g_id;
	}
	public void setG_id(Integer g_id) {
		this.g_id = g_id;
	}
	public Integer getRstate() {
		return rstate;
	}
	public void setRstate(Integer rstate) {
		this.rstate = rstate;
	}
   
}
