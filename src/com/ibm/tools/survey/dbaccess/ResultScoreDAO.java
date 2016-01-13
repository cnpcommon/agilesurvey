package com.ibm.tools.survey.dbaccess;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tools.survey.bean.Scores;
import com.ibm.tools.utils.MongoDBHelper;
import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;

/**
 * DAO Class to access Score data
 * 
 * @author Suman Mandal
 *
 */
public class ResultScoreDAO {

	private static final Logger LOGGER = Logger.getLogger(ResultScoreDAO.class
			.getName());

	private static final String COLLECTION_NAME = "survey_data";

	public  boolean getResults(long assestementId,String squadId) {

		MongoCollection<Document> collection = MongoDBHelper
				.getCollection(COLLECTION_NAME);
		AggregateIterable<Document> iterable=collection.aggregate(asList(
		        new Document("$match", new Document("type", Scores.TYPE)
			        .append("assestementId",assestementId)
			        .append("squadId",squadId)),
		        new Document("$group", new Document("_id", 
		        		new Document("practice", "$practice").append("principle", "$principle").append("squadId", "$squadId"))
		        		.append("avg score", new Document("$avg", "$value")))));
		iterable.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document.toJson());
		    }
		});
		return true;
	}

	public  boolean saveData(List<Scores> listofObjects) {
		boolean isSucess = false;
		try {
			MongoCollection<Document> collection = MongoDBHelper
					.getCollection();
			if (listofObjects != null && listofObjects.size() > 0) {
				Gson serializer = new GsonBuilder().create();
				List<Document> docs = new ArrayList<>(listofObjects.size());
				for (Scores obj : listofObjects) {
					docs.add(Document.parse(serializer.toJson(obj)));
				}
				collection.insertMany(docs);
				isSucess = true;
			}

		} catch (Exception ex) {
			LOGGER.log(Level.WARNING, "|ResultScoreDAO| Data save failure",
					ex);
			isSucess = false;
		}
		return isSucess;
	}

}
