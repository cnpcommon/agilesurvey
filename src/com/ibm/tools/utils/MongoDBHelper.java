package com.ibm.tools.utils;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBHelper {

	private static Logger LOGGER = Logger.getLogger(MongoDBHelper.class
			.getName());

	// To stop instantiation and access only static way
	private MongoDBHelper() {
		super();
	}

	private static MongoClient mongo;
	private static boolean isInitialized = false;
	private static MongoDatabase db;
	private static String collection;

	private static boolean init() {
		try {
			if (mongo == null) {
				// We may need to change to VCAP_SERVICES
				String dbUrl = LocalVCAPProperties.getLocalProperty("mongodb.server");
				collection =  LocalVCAPProperties.getLocalProperty("mongodb.dbname.dbcollection");
				int dbPort = Integer.parseInt(LocalVCAPProperties
						.getLocalProperty("mongodb.server.port"));

				MongoCredential credential = MongoCredential.createCredential(
						LocalVCAPProperties.getLocalProperty("mongodb.userid"),
						LocalVCAPProperties.getLocalProperty("mongodb.dbname"),
						LocalVCAPProperties.getLocalProperty("mongodb.pwd").toCharArray());
				
				ServerAddress srvrAddress = new ServerAddress(dbUrl,dbPort);
				mongo = new MongoClient(Arrays.asList(srvrAddress),Arrays.asList(credential));
				db = mongo.getDatabase(LocalVCAPProperties
						.getLocalProperty("mongodb.dbname"));
				
				System.out.println("DB Connection established : with " + dbUrl);
			}
			isInitialized = true;

		} catch (Exception ex) {
			LOGGER.log(Level.WARNING, "Error in initizlizing MongoBB", ex);
			isInitialized = false;
		}
		return isInitialized;
	}

	public static MongoCollection<Document> getCollection() {

		if (init()) {
			return db.getCollection(collection);
		} else {
			return null;
		}

	}
	public static MongoCollection<Document> getCollection(String collectionName) {

		if (init()) {
			return db.getCollection(collectionName);
		} else {
			return null;
		}

	}

}
