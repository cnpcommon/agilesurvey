package com.ibm.tools.survey.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.survey.dbaccess.MonboDBAccess;

public class MonboDBAccessTest {

	MonboDBAccess monDb = new MonboDBAccess();
	
	@Test
	public void testInsertDate() {
		
		
		UserDetails ud = new UserDetails();
    	ud.setDisplayName("Suman");
    	ud.setEmailId("sumanmandal@in.ibm.com");
    	ud.setPassword("test123");
    	ud.setRole("tribe");
    	
		assertTrue(monDb.insertDate(ud));
	}

}
