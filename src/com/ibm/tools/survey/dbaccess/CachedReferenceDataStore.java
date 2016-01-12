package com.ibm.tools.survey.dbaccess;

import java.util.ArrayList;
import java.util.List;

import com.ibm.tools.survey.bean.AgilePractice;
import com.ibm.tools.survey.bean.AgilePrinciple;
import com.ibm.tools.survey.bean.MaturityLevel;

public class CachedReferenceDataStore {
	
	private static List<AgilePrinciple> _agilePriciples = new ArrayList<>();
	private static List<AgilePractice> _agilePractices = new ArrayList<>();
	private static List<MaturityLevel> _levels= new ArrayList<>();
	
	
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
	

}
