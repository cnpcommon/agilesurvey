package com.ibm.tools.survey.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ibm.tools.survey.bean.AgilePrinciple;
import com.ibm.tools.survey.dbaccess.SurveyConfigDAO;

public class SurveyConfigDAOTest {

	private SurveyConfigDAO daoObject ;
	@Before
	public void setUp() throws Exception {
		daoObject = new SurveyConfigDAO();
	}

	//@Test
	public void testInsertAgilePrinciples() {
		//Build agile principles;
		List<AgilePrinciple> principles = new ArrayList<>();
		principles.add(new AgilePrinciple("1","Collaboration and Teamwork",1,"Loaded manually"));
		principles.add(new AgilePrinciple("2","Focus on the Customer and Business Value",2,"Loaded manually"));
		principles.add(new AgilePrinciple("3","Flexible, Adaptive and Continuously Improving",3,"Loaded manually"));
		principles.add(new AgilePrinciple("4","Iterative and Fast",4,"Loaded manually"));
		principles.add(new AgilePrinciple("5","Empowered and Self Directed Teams",5,"Loaded manually"));
		assertEquals(true, daoObject.saveData(principles));
	}
	@Test
	public void testGetAgilePrinciples() {
		//Build agile principles;
		List<AgilePrinciple> principles = daoObject.getAllTypes(AgilePrinciple.TYPE, AgilePrinciple.class);
		assertNotNull(principles);
		
	}

}
