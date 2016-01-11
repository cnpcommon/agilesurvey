package com.ibm.tools.survey.bean;

import org.bson.types.ObjectId;

public class AgilePrinciple {

	private static final String TYPE="agile_principle";
	private ObjectId _id;

	private String type = TYPE ;
	private String principleId;
	private String practiceId;
	private String description;
	private int displayOrder;
	private String comment;
	
	public AgilePrinciple()
	{
		super();
	}
	
	public AgilePrinciple(String principleId, String practiceId, String description,
			int displayOrder, String comment) {
		super();
		this.principleId = principleId;
		this.description = description;
		this.displayOrder = displayOrder;
		this.comment = comment;
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
	 * @return the principleId
	 */
	public String getPrincipleId() {
		return principleId;
	}
	/**
	 * @param principleId the principleId to set
	 */
	public void setPrincipleId(String principleId) {
		this.principleId = principleId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the practiceId
	 */
	public String getPracticeId() {
		return practiceId;
	}

	/**
	 * @param practiceId the practiceId to set
	 */
	public void setPracticeId(String practiceId) {
		this.practiceId = practiceId;
	}
	
	
	
}
