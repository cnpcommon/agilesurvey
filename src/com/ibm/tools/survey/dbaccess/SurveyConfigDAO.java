package com.ibm.tools.survey.dbaccess;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tools.survey.bean.MaturityIndicator;
import com.ibm.tools.survey.bean.Persistable;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

/**
 * DAO Class to access survey config data
 * 
 * @author SUDDUTT1
 *
 */
public class SurveyConfigDAO {

	private static final Logger LOGGER = Logger.getLogger(SurveyConfigDAO.class
			.getName());

	private static final String COLLECTION_NAME = "survey_data";

	public boolean updateMaturityIndicator(MaturityIndicator indicator) {
		// First search the indicators
		boolean isSucces = false;
		try {
			MongoCollection<Document> collection = MongoDBHelper
					.getCollection(COLLECTION_NAME);
			boolean isInsert = false;
			if(getSafeString(indicator.getQuestionid())!=null )
			{
				Document existingDoc = collection.find(
						and(eq("type", MaturityIndicator.TYPE),
								eq("questionid", indicator.getQuestionid()))).first();
				if (existingDoc == null) {
					isInsert = true;
				} else {
					Document updatedDocument = new Document();
					updatedDocument.put("disabled", indicator.isDisabled());
					updatedDocument.put("displayOrder", indicator.getDisplayOrder());
					updatedDocument.put("comment", indicator.getComment());
					updatedDocument.put("indicatorText", indicator.getIndicatorText());
					collection.updateOne(and(eq("type", MaturityIndicator.TYPE),
							eq("questionid", indicator.getQuestionid())), new Document("$set", updatedDocument));

				}
			}
			
			if(isInsert)
			{
				Gson serializer = new GsonBuilder().create();
				indicator.setQuestionid(String.valueOf(System.currentTimeMillis()));
				collection.insertOne(Document.parse(serializer
						.toJson(indicator)));
			}
			
			isSucces = true;
		} catch (Exception ex) {
			LOGGER.log(Level.WARNING,
					"|SURVERY_CONFIG_DAO| Indicator save failure", ex);
			isSucces = false;
		}
		return isSucces;
		// Then save or update
	}

	public <T extends Persistable> List<T> getAllTypes(String type, Class<T> clz) {

		MongoCollection<Document> collection = MongoDBHelper
				.getCollection(COLLECTION_NAME);
		List<Document> docs = collection.find(eq("type", type)).into(
				new ArrayList<Document>());
		if (docs != null && docs.size() > 0) {
			Gson serializer = new GsonBuilder().create();
			List<T> retList = new ArrayList<>();
			for (Document doc : docs) {
				T dbObject = serializer.fromJson(doc.toJson(), clz);
				retList.add(dbObject);
			}
			return retList;
		} else {
			return new ArrayList<>();
		}
	}

	public <T extends Persistable> boolean saveData(List<T> listofObjects) {
		boolean isSucess = false;
		try {
			MongoCollection<Document> collection = MongoDBHelper
					.getCollection();
			if (listofObjects != null && listofObjects.size() > 0) {
				Gson serializer = new GsonBuilder().create();
				List<Document> docs = new ArrayList<>(listofObjects.size());
				for (T obj : listofObjects) {
					docs.add(Document.parse(serializer.toJson(obj)));
				}
				collection.insertMany(docs);
				isSucess = true;
			}

		} catch (Exception ex) {
			LOGGER.log(Level.WARNING, "|SURVERY_CONFIG_DAO| Data save failure",
					ex);
			isSucess = false;
		}
		return isSucess;
	}
	
	public  boolean deleteDataAllType(String type)
	{
		MongoCollection<Document> collection = MongoDBHelper
				.getCollection();
		collection.deleteMany(eq("type",type));
		
		return true;
	}
	
	private String getSafeString(String input)
	{
		return (input !=null ? input.trim(): "");
	}
}
