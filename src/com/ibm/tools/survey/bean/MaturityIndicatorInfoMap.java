package com.ibm.tools.survey.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to hold the maturity indicators in an hierarchy
 * @author SUDDUTT1
 *
 */
public class MaturityIndicatorInfoMap {

	private String principleId;
	private String practiceId;
	private String itemId;
	
	private Map<String,MaturityIndicator> levelIndicatorMap = new LinkedHashMap<>();
	
	
	public MaturityIndicatorInfoMap(String principle,String practice)
	{
		this.principleId = principle;
		this.practiceId = practice;
		this.itemId = String.valueOf(System.currentTimeMillis());
		
	}
	
	public boolean  addIndicator(MaturityIndicator indicator)
	{
		if(indicator.getPractice().trim().equals(this.practiceId) && indicator.getPrinciple().trim().equals(this.principleId))
		{
			levelIndicatorMap.put(String.valueOf(indicator.getLevel()), indicator);
			return true;
		}
		return false;
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

	/**
	 * @return the levelIndicatorMap
	 */
	public Map<String, MaturityIndicator> getLevelIndicatorMap() {
		return levelIndicatorMap;
	}

	/**
	 * @param levelIndicatorMap the levelIndicatorMap to set
	 */
	public void setLevelIndicatorMap(
			Map<String, MaturityIndicator> levelIndicatorMap) {
		this.levelIndicatorMap = levelIndicatorMap;
	}
	
	public  List<MaturityIndicator> getIndicators()
	{
		return new ArrayList<MaturityIndicator>(this.levelIndicatorMap.values());
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	
}
