package com.ibm.tools.survey.unit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;
import com.ibm.tools.survey.bean.AssesmentDetails;
import com.ibm.tools.survey.dbaccess.SurveyConfigDAO;

public class AssesmentInsertJunit {

	@Test
	@Ignore
	public void insertAssesment(){
	List<String> squadList=new ArrayList<>();
	squadList.add("cnp1");
	squadList.add("esdw");
		
	 AssesmentDetails assesmentDetails=new AssesmentDetails(894567l,"gmarinsk@us.ibm.com" , squadList , "Assesment3", "10-02-2016", "testComment3");
	 List<AssesmentDetails> assesmentList=new ArrayList<>();
	 assesmentList.add(assesmentDetails);
	 SurveyConfigDAO daoObject=new SurveyConfigDAO();
		daoObject.saveData(assesmentList);
		
	}
	@Test
	@Ignore
	public void testGetAllAssesmentDetails(){
	
	// AssesmentDetails assesmentDetails=new AssesmentDetails(894567l,"gmarinsk@us.ibm.com" , squadList , "Assesment1", "14-jan-16", "testComment");
	 List<AssesmentDetails> assesmentList=new SurveyConfigDAO().getAllAssesmentDetails("gmarinsk@us.ibm.com");
	 Gson gson=new Gson();
	 for(AssesmentDetails ad:assesmentList){
		 System.out.println(gson.toJson(ad));
	 }
	}

}
