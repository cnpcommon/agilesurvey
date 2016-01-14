package com.ibm.tools.survey.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MaturityIndicatorAccumulator {

	private Map<String,MaturityIndicatorInfoMap> map=new LinkedHashMap<>();
	
	public MaturityIndicatorAccumulator()
	{
		
	}
	
	public List<MaturityIndicatorInfoMap> getAccumulatedList(List<MaturityIndicator> indicatorList)
	{
		for(MaturityIndicator indicator:indicatorList)
		{
			String key = indicator.getPrinciple()+"_"+indicator.getPractice();
			MaturityIndicatorInfoMap info = map.get(key);
			if(info==null)
			{
				info = new MaturityIndicatorInfoMap(indicator.getPrinciple().trim(), indicator.getPractice().trim());
				map.put(key, info);
			}
			info.addIndicator(indicator);
		}
		
		return new ArrayList<>(map.values());
	}
	
}
