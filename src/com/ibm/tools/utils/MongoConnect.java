package com.ibm.tools.utils;

import com.google.gson.Gson;
import com.ibm.tools.survey.bean.UserDetails;
import com.mongodb.*;
import com.mongodb.util.JSON;
class MongoConnect
{
    public static void main(String args[]) throws Exception
    {
    	Gson gson = new Gson();
    	UserDetails ud = new UserDetails();
    	ud.setDisplayName("Sudip");
    	ud.setEmailId("suddutt1@in.ibm.com");
    	ud.setPassword("cnp4test");
    	ud.setRole("tribe");
    	String jsonsting  = gson.toJson(ud);
    	DBObject dbObject = (DBObject) JSON.parse(jsonsting);
        String textUri = "mongodb://cnpcommon:cnp4test@ds041140.mongolab.com:41140/msurvey1";

        // Create MongoClientURI object from which you get MongoClient obj
        MongoClientURI uri = new MongoClientURI(textUri);

        // Connect to that uri
        MongoClient m = new MongoClient(uri);

        // get the database named sample
        DB d=m.getDB("msurvey1");

        // get the collection mycollection in sample
        DBCollection collection = d.getCollection("survey_data");

        // Insert that object into the collection
        //collection.insert(dbObject);
        
        
        BasicDBObject whereQuery = new BasicDBObject();

   	     whereQuery.put("emailId", "suddutt1@in.ibm.com");
   	      
   	    DBCursor cursorDoc = collection.find(whereQuery);
        
        
       // DBCursor cursorDoc = collection.find();
		while (cursorDoc.hasNext()) {
			//collection.remove(cursorDoc.next());
			
			String s = String.format("%s",cursorDoc.next());
			System.out.println(s);
			
		}

    }
}