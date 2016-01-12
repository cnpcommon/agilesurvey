package com.ibm.tools.survey.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

public class AssesmentDetails implements Persistable{

	public static final String TYPE="assesment_details";
	private ObjectId _id;

	private String type = TYPE ;
	
	private long assessementId;
	private String owenerId;
	private List<String> squadList;
	private String name;
	private String releaseDate;
	private String comment;
	private List<MaturityIndicator> indicators;
	private long dateModified;
	
	public AssesmentDetails(long assessementId, String owenerId,
			List<String> squadList, String name, String releaseDate,
			String comment) {
		super();
		this.assessementId = assessementId;
		this.owenerId = owenerId;
		this.squadList = squadList;
		this.name = name;
		this.releaseDate = releaseDate;
		this.comment = comment;
		this.indicators = new ArrayList<>(5);
		this.dateModified = (new Date()).getTime();
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
	 * @return the assessementId
	 */
	public long getAssessementId() {
		return assessementId;
	}

	/**
	 * @param assessementId the assessementId to set
	 */
	public void setAssessementId(long assessementId) {
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
	 * @return the indicators
	 */
	public List<MaturityIndicator> getIndicators() {
		return indicators;
	}

	/**
	 * @param indicators the indicators to set
	 */
	public void setIndicators(List<MaturityIndicator> indicators) {
		this.indicators = indicators;
	}
	
	/**
	 * Add maturity indicator
	 * @param indicator
	 */
	public void addIndicators(MaturityIndicator indicator) {
		this.indicators.add(indicator);
	}

	/**
	 * @return the dateModified
	 */
	public long getDateModified() {
		return dateModified;
	}

	/**
	 * @param dateModified the dateModified to set
	 */
	public void setDateModified(long dateModified) {
		this.dateModified = dateModified;
	}
	
	
}
