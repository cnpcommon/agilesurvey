package com.ibm.tools.survey.dbaccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tools.survey.bean.Persistable;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.*;


/**
 * DAO Class to access survey config data
 * @author SUDDUTT1
 *
 */
public class SurveyConfigDAO {

	private static final Logger LOGGER = Logger.getLogger(SurveyConfigDAO.class.getName());
	
	private static final String COLLECTION_NAME = "survey_data";

	public <T extends Persistable> List<T> getAllTypes(String type,Class<T> clz)
	{

		
		MongoCollection<Document>  collection = MongoDBHelper.getCollection(COLLECTION_NAME);
		List<Document> docs = collection.find(eq("type", type)).into(new ArrayList<Document>());
		if(docs!=null && docs.size()>0)
		{
			 Gson serializer = new GsonBuilder().create();
			List<T> retList = new ArrayList<>();
			for(Document doc: docs)
			{
				T dbObject = serializer.fromJson(doc.toJson(), clz);
				retList.add(dbObject);
			}
			return retList;
		}
		else
		{
			return Collections.emptyList();
		}
	}
	public <T extends Persistable> boolean saveData(List<T> listofObjects)
	{
		boolean isSucess = false;
		try{
			MongoCollection<Document>  collection = MongoDBHelper.getCollection();
			if(listofObjects!=null && listofObjects.size()>0)
			{
				Gson serializer = new GsonBuilder().create();
				List<Document> docs = new ArrayList<>(listofObjects.size());
				for(T obj: listofObjects)
				{
					docs.add(Document.parse(serializer.toJson(obj)));
				}
				collection.insertMany(docs);
				isSucess = true;
			}
			
		}catch(Exception ex)
		{
			LOGGER.log(Level.WARNING,"|SURVERY_CONFIG_DAO| Data save failure",ex);
			isSucess = false;
		}
		return isSucess;
	}
	
}
