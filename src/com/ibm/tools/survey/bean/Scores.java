package com.ibm.tools.survey.bean;

import org.bson.types.ObjectId;

public class Scores {
	
	public  static final String TYPE="score";
	private ObjectId _id;
	private String type = TYPE ;
	private String memberId;
	private String squadId;
	private long assestementId;
	
	private int value;
	private String practice;
	private String principle;
	
	public Scores()
	{
		super();
	}
	
	public Scores(String memberId,String squadId,long assestementId, int value,
			String practice, String principle) {
		super();
		this.memberId = memberId;
		this.squadId = squadId;
		this.assestementId = assestementId;
		this.value = value;
		this.practice = practice;
		this.principle = principle;
	}
	
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSquadId() {
		return squadId;
	}
	public void setSquadId(String squadId) {
		this.squadId = squadId;
	}
	public long getAssestementId() {
		return assestementId;
	}
	public void setAssestementId(long assestementId) {
		this.assestementId = assestementId;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getPractice() {
		return practice;
	}
	public void setPractice(String practice) {
		this.practice = practice;
	}
	public String getPrinciple() {
		return principle;
	}
	public void setPrinciple(String principle) {
		this.principle = principle;
	}
	
	
}
