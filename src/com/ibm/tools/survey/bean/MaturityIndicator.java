package com.ibm.tools.survey.bean;

import org.bson.types.ObjectId;

public class MaturityIndicator {

	
	private static final String TYPE="maturity_indicator";
	private ObjectId _id;

	private String type = TYPE ;
	private int level;
	private String indicatorText;
	private String comment;
	private int displayOrder;
	private String principle;
	private String practice;
	
	
	public MaturityIndicator()
	{
		super();
	}
	
	
	public MaturityIndicator(String principle, String practice, int level,
			String indicatorText, String comment, int displayOrder) {
		super();
		this.principle = principle;
		this.practice = practice;
		this.level = level;
		this.indicatorText = indicatorText;
		this.comment = comment;
		this.displayOrder = displayOrder;
	}


	/**
	 * @return the _id
	 */
	public ObjectId get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the name
	 */
	public String getIndicatorText() {
		return indicatorText;
	}
	/**
	 * @param name the name to set
	 */
	public void setIndicatorText(String indicatorText) {
		this.indicatorText = indicatorText;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the displayOrder
	 */
	public int getDisplayOrder() {
		return displayOrder;
	}
	/**
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}


	/**
	 * @return the principle
	 */
	public String getPrinciple() {
		return principle;
	}


	/**
	 * @param principle the principle to set
	 */
	public void setPrinciple(String principle) {
		this.principle = principle;
	}


	/**
	 * @return the practice
	 */
	public String getPractice() {
		return practice;
	}


	/**
	 * @param practice the practice to set
	 */
	public void setPractice(String practice) {
		this.practice = practice;
	}
	
	
}
