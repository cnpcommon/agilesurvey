package com.ibm.tools.survey.bean;

import java.util.List;

public class MaturityAssesmentUser {
	
	private String id;
	private String role;
	private  String squadId;
	private String squadMasterId;
	private String reportTo;
	private List<String> tribes;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSquadId() {
		return squadId;
	}
	public void setSquadId(String squadId) {
		this.squadId = squadId;
	}
	public String getSquadMasterId() {
		return squadMasterId;
	}
	public void setSquadMasterId(String squadMasterId) {
		this.squadMasterId = squadMasterId;
	}
	public List<String> getTribes() {
		return tribes;
	}
	public void setTribes(List<String> tribes) {
		this.tribes = tribes;
	}
	public String getReportTo() {
		return reportTo;
	}
	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}
	

}
