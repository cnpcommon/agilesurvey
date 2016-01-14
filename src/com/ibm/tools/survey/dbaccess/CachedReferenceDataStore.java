package com.ibm.tools.survey.dbaccess;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ibm.tools.survey.bean.AgilePractice;
import com.ibm.tools.survey.bean.AgilePrinciple;

import com.ibm.tools.survey.bean.MaturityLevel;

public class CachedReferenceDataStore {
	
	private static List<AgilePrinciple> _agilePriciples = new ArrayList<>();
	private static List<AgilePractice> _agilePractices = new ArrayList<>();
	private static List<MaturityLevel> _levels= new ArrayList<>();
	
	private static Map<String,List<AgilePractice>> _principleToPracticeMap= new LinkedHashMap<>();
	private static Map<String,String> _practiceIdtoNameMap=new LinkedHashMap<>();
	
	
	private CachedReferenceDataStore()
	{
		super();
	}
	
	public static List<MaturityLevel> getLevels()
	{
		if(_levels!=null && _levels.size()>0)
		{
			return _levels;
		}else
		{
			_levels = (new SurveyConfigDAO()).getAllTypes(MaturityLevel.TYPE, MaturityLevel.class);
			return _levels;
		}
		
	}
	public static List<AgilePrinciple> getPrinciples()
	{
		if(_agilePriciples!=null && _agilePriciples.size()>0)
		{
			return _agilePriciples;
		}else
		{
			_agilePriciples = (new SurveyConfigDAO()).getAllTypes(AgilePrinciple.TYPE, AgilePrinciple.class);
			return _agilePriciples;
		}
		
	}
	public static List<AgilePractice> getPractices()
	{
		if(_agilePractices!=null && _agilePractices.size()>0)
		{
			return _agilePractices;
		}else
		{
			_agilePractices = (new SurveyConfigDAO()).getAllTypes(AgilePractice.TYPE, AgilePractice.class);
			return _agilePractices;
		}
		
	}
	public static Map<String,List<AgilePractice>> getPrincipleToPracticeMap()
	{
		if(_principleToPracticeMap!=null && _principleToPracticeMap.size()>0)
		{
			return _principleToPracticeMap;
		}
		else
		{
			for(AgilePractice practice: getPractices())
			{
				List<AgilePractice> practiceList = _principleToPracticeMap.get(practice.getPrincipleId());
				if(practiceList==null)
				{
					practiceList = new ArrayList<>();
					_principleToPracticeMap.put(practice.getPrincipleId(), practiceList);
				}
				practiceList.add(practice);
			}
			return _principleToPracticeMap;
		}
		
	}
	public static Map<String,String> getPracticeIdtoNameMap()
	{
		if(_practiceIdtoNameMap!=null && _practiceIdtoNameMap.size()>0)
		{
			return _practiceIdtoNameMap;
		}
		else
		{
			for(AgilePractice practice: getPractices())
			{
				_practiceIdtoNameMap.put(practice.getPracticeId(), practice.getShortName());
				//System.out.println("store in the map"+practice.getPracticeId()+practice.getShortName());
			}
			return _practiceIdtoNameMap;
		}
		
	}
	

}
