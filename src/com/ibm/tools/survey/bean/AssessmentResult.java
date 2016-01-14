package com.ibm.tools.survey.bean;

import com.ibm.tools.survey.dbaccess.CachedReferenceDataStore;

public class AssessmentResult {
	private String _id;
	private String practiceName;
	private String currentScore;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
		
	}
	
	public String getPracticeName() {
		return practiceName;
	}
	
	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}
	public String getCurrentScore() {
		return currentScore;
	}
	public void setCurrentScore(String currentScore) {
		this.currentScore = currentScore;
	}
	public void setNameForcefully(){
		//System.out.println("Search for id in the map"+_id);
		String practiceName=CachedReferenceDataStore.getPracticeIdtoNameMap().get(_id);
		//System.out.println("practice name from map"+practiceName);
		//setPracticeName(practiceName);
		this.practiceName=practiceName;
	}
	

}
