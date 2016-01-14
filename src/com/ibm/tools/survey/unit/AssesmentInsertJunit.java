package com.ibm.tools.survey.unit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ibm.tools.survey.bean.*;
import com.ibm.tools.survey.dbaccess.SurveyConfigDAO;

public class AssesmentInsertJunit {

	@Test
	public void insertAssesment(){
	List<String> squadList=new ArrayList<>();
	squadList.add("cnp1");
	squadList.add("esdw");
		
	 AssesmentDetails assesmentDetails=new AssesmentDetails(894567l,"gmarinsk@us.ibm.com" , squadList , "Assesment1", "14-jan-16", "testComment");
	 List<AssesmentDetails> assesmentList=new ArrayList<>();
	 assesmentList.add(assesmentDetails);
	 SurveyConfigDAO daoObject=new SurveyConfigDAO();
		daoObject.saveData(assesmentList);
		
	}
	

}
