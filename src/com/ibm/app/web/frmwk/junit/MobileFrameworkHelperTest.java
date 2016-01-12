package com.ibm.app.web.frmwk.junit;

import static org.junit.Assert.*;

import org.bson.Document;
import org.junit.Test;

import com.ibm.app.web.frmwk.MobileFrameworkHelper;
import com.ibm.app.web.frmwk.bean.ActionConfigurations;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.client.MongoCollection;


public class MobileFrameworkHelperTest {

	public void test() {
		ActionConfigurations configs = MobileFrameworkHelper
				.loadActionConfigs(new String[] { "com.ibm.mytelco.action.LoginAction" });
		assertNotNull(configs);
		System.out.println(configs);
	}

	@Test
	public void testDB()
	{
		MongoCollection<Document> myCollection = MongoDBHelper.getCollection();
		//UserDetails userDetails = new UserDetails();
		//userDetails.setEmailId("Sudip@gamil.com");
		
		//myCollection.save(userDetails.toDBObject());
		assertNotNull(myCollection);
		
	}
}
