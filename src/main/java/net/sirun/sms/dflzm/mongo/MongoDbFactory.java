package net.sirun.sms.dflzm.mongo;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbFactory {
	private static final MongoDbFactory factory = new MongoDbFactory();
	private static MongoClient mongoClient = null;

	static {
		if (mongoClient == null) {
			ServerAddress serverAddress = new ServerAddress("192.168.9.51", 27017);
			// ServerAddress serverAddress = new
			// ServerAddress("localhost",10001);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);
			// MongoCredential credential =
			// MongoCredential.createScramSha1Credential("pj"/* username */,
			// dbName/* dbname */, "123456"/* pw */.toCharArray());
			MongoCredential credential = MongoCredential.createScramSha1Credential("dba"/* username */,
					"admin"/* dbname */, "sirun123"/* pw */.toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);
			mongoClient = new MongoClient(addrs, credentials);
		}

	}

	public static MongoClient getMongoClient() {
		return mongoClient;
	}

	public static MongoDatabase getMongoDatabase(String dbName) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
		return mongoDatabase;
	}

	public static MongoCollection<Document> getMongoCollection(String dbName, String tableName) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
		MongoCollection<Document> collection = null;
		collection = mongoDatabase.getCollection(tableName);

		return collection;
	}

}
