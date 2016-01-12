package com.ibm.tools.survey.dbaccess;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tools.survey.bean.UserDetails;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;


public class MonboDBAccess {
	
    Gson GSON_SERIALIZER = new GsonBuilder().create();
    Gson gson = new Gson();
	public boolean insertDate(UserDetails userdetails){
		
		MongoCollection<Document>  collection = MongoDBHelper.getCollection();
		collection.insertOne(Document.parse(GSON_SERIALIZER.toJson(userdetails)));
		
		BasicDBObject whereQuery = new BasicDBObject();
   	    whereQuery.put("emailId", userdetails.getEmailId());
   	    
   	    Document cursorDoc = collection.find(whereQuery).first();
		UserDetails userDetailsfromDB  = gson.fromJson(cursorDoc.toJson(),UserDetails.class );
		
		if(userdetails.equals(userDetailsfromDB))
		   return true;
		else
			return false;
		
	}

}
