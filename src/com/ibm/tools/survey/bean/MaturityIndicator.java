package com.ibm.tools.survey.bean;

import org.bson.types.ObjectId;

public class MaturityIndicator implements Persistable
{ 

	
	public  static final String TYPE="maturity_indicator";
	private ObjectId _id;

	private String type = TYPE ;
	private int level;
	private String indicatorText;
	private String comment;
	private int displayOrder;
	private String principle;
	private String practice;
	private boolean disabled;
	
	public MaturityIndicator()
	{
		super();
	}
	
	
	public MaturityIndicator(String principle, String practice,int level) {
		super();
		this.level = level;
		this.principle = principle;
		this.practice = practice;
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


	/**
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}


	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + level;
		result = prime * result
				+ ((practice == null) ? 0 : practice.hashCode());
		result = prime * result
				+ ((principle == null) ? 0 : principle.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaturityIndicator other = (MaturityIndicator) obj;
		if (level != other.level)
			return false;
		if (practice == null) {
			if (other.practice != null)
				return false;
		} else if (!practice.equals(other.practice))
			return false;
		if (principle == null) {
			if (other.principle != null)
				return false;
		} else if (!principle.equals(other.principle))
			return false;
		return true;
	}
	
	
}
