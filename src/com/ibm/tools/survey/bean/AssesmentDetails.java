package com.ibm.tools.survey.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class AssesmentDetails implements Persistable{

	public static final String TYPE="assesment_details";
	private ObjectId _id;
	private static final SimpleDateFormat DT_FMT = new SimpleDateFormat("YYYYMMddHHmmssssSSS");

	private String type = TYPE ;
	
	private String assessementId;
	private String owenerId;
	private List<String> squadList;
	private String name;
	private String releaseDate;
	private String comment;
	private List<MaturityIndicatorInfoMap> indicatorMap;
	private String dateModified;
	private boolean disabled;
	
	
	public AssesmentDetails(long assessementId, String owenerId,
			List<String> squadList, String name, String releaseDate,
			String comment) {
		super();
		this.assessementId = String.valueOf(assessementId);
		this.owenerId = owenerId;
		this.squadList = squadList;
		this.name = name;
		this.releaseDate = releaseDate;
		this.comment = comment;
		this.indicatorMap = new ArrayList<>();
		this.dateModified = DT_FMT.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * @return the _id
	 */
	public ObjectId get_id() {
		return _id;
	}
	public AssesmentDetails(long assessementId, String owenerId,
			List<String> squadList, String name, String releaseDate,
			String comment, List<MaturityIndicatorInfoMap> indicatorMap) {
		super();
		this.assessementId = String.valueOf(assessementId);
		this.owenerId = owenerId;
		this.squadList = squadList;
		this.name = name;
		this.releaseDate = releaseDate;
		this.comment = comment;
		this.indicatorMap = indicatorMap;
		this.dateModified = DT_FMT.format(new Date(System.currentTimeMillis()));
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
	 * @return the assessementId
	 */
	public String getAssessementId() {
		return assessementId;
	}
	/**
	 * @param assessementId the assessementId to set
	 */
	public void setAssessementId(String assessementId) {
		this.assessementId = assessementId;
	}
	/**
	 * @return the owenerId
	 */
	public String getOwenerId() {
		return owenerId;
	}
	/**
	 * @param owenerId the owenerId to set
	 */
	public void setOwenerId(String owenerId) {
		this.owenerId = owenerId;
	}
	/**
	 * @return the squadList
	 */
	public List<String> getSquadList() {
		return squadList;
	}
	/**
	 * @param squadList the squadList to set
	 */
	public void setSquadList(List<String> squadList) {
		this.squadList = squadList;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}
	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
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
	 * @return the indicatorMap
	 */
	public List<MaturityIndicatorInfoMap> getIndicatorMap() {
		return indicatorMap;
	}
	/**
	 * @param indicatorMap the indicatorMap to set
	 */
	public void setIndicatorMap(List<MaturityIndicatorInfoMap> indicatorMap) {
		this.indicatorMap = indicatorMap;
	}
	/**
	 * @return the dateModified
	 */

	public String getDateModified() {
		return dateModified;
	}
	/**
	 * @param dateModified the dateModified to set
	 */
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}
	
	/**
	 * @param _id the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
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
}
