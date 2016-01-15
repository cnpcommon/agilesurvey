package com.ibm.tools.survey.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ibm.tools.survey.bean.ApplicationConstants;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.survey.dbaccess.MonboDBAccess;

public class MonboDBAccessTest {

	static MonboDBAccess monDb = new MonboDBAccess();
	
	@Test
	public void testInsertDate() {
		
		
		UserDetails ud = new UserDetails();
    	ud.setDisplayName("Geoffrey");
    	ud.setEmailId("gmarinsk@us.ibm.com");
    	ud.setPassword("test123");
    	ud.setRole(ApplicationConstants.USER_ROLE_TRIBE_MANAGER);
    	ud.setType("UserDetails");
    	
		assertTrue(monDb.insertDate(ud));
	}

}
