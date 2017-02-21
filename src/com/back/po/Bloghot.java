package com.back.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Bloghot implements Serializable {
    private Integer bid;
    private Integer bstate;
    private String btitle;
    private String bitems;
    private Integer bvote;
    private String bremarks;
    private List<Integer> vote=new ArrayList<Integer>();
    private List<String> item=new ArrayList<String>();
   
	public Bloghot(){    	
    }
    public Bloghot(Integer bid, Integer bstate,String btitle,String bitems,Integer bvote,String bremarks){
    	this.bid=bid;
    	this.bstate=bstate;
    	this.btitle=btitle;
    	this.bitems=bitems;
    	this.bvote=bvote;
    	this.bremarks=bremarks;
    }
    public Bloghot( Integer bstate,String btitle,String bitems,Integer bvote,String bremarks){    	
    	this.bstate=bstate;
    	this.btitle=btitle;
    	this.bitems=bitems;
    	this.bvote=bvote;
    	this.bremarks=bremarks;
    }
    public List<Integer> getVote() {
		return vote;
	}
	public void setVote(List<Integer> vote) {
		this.vote = vote;
	}
	public List<String> getItem() {
		return item;
	}
	public void setItem(List<String> item) {
		this.item = item;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getBstate() {
		return bstate;
	}
	public void setBstate(Integer bstate) {
		this.bstate = bstate;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBitems() {
		return bitems;
	}
	public void setBitems(String bitems) {
		this.bitems = bitems;
	}
	public Integer getBvote() {
		return bvote;
	}
	public void setBvote(Integer bvote) {
		this.bvote = bvote;
	}
	public String getBremarks() {
		return bremarks;
	}
	public void setBremarks(String bremarks) {
		this.bremarks = bremarks;
	}
}
