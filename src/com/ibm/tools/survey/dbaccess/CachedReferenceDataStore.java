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
	private static Map<String,AgilePrinciple> _agilePrincipleMap = new LinkedHashMap<>();
	private static List<AgilePractice> _agilePractices = new ArrayList<>();
	private static Map<String,AgilePractice> _agilePracticeMap = new LinkedHashMap<>();
	private static List<MaturityLevel> _levels= new ArrayList<>();
	private static Map<String,MaturityLevel> _levelMap = new LinkedHashMap<>();
	
	private static Map<String,List<AgilePractice>> _principleToPracticeMap= new LinkedHashMap<>();
	
	
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
	public static Map<String,MaturityLevel> getLevelsMap()
	{
		if(_levelMap!=null && _levelMap.size()>0)
		{
			return _levelMap;
		}
		else
		{
			for(MaturityLevel elemtent: getLevels())
			{
				_levelMap.put(String.valueOf(elemtent.getLevel()), elemtent);
			}
			return _levelMap;
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
	public static Map<String,AgilePrinciple> getAgilePrinciplesMap()
	{
		if(_agilePrincipleMap!=null && _agilePrincipleMap.size()>0)
		{
			return _agilePrincipleMap;
		}
		else
		{
			for(AgilePrinciple elemtent: getPrinciples())
			{
				_agilePrincipleMap.put(String.valueOf(elemtent.getPrincipleId()), elemtent);
			}
			return _agilePrincipleMap;
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
	public static Map<String,AgilePractice> getAgilePracticeMap()
	{
		if(_agilePracticeMap!=null && _agilePracticeMap.size()>0)
		{
			return _agilePracticeMap;
		}
		else
		{
			for(AgilePractice elemtent: getPractices())
			{
				_agilePracticeMap.put(String.valueOf(elemtent.getPracticeId()), elemtent);
			}
			return _agilePracticeMap;
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
	

}
