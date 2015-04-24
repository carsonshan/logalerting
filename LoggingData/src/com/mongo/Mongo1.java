package com.mongo;

import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class Mongo1 {

	public void sampleInsert()
	{
		try {
			 
			/**** Connect to MongoDB ****/
			// Since 2.10.0, uses MongoClient
			MongoClient mongo = new MongoClient("localhost", 27017);
		 
			/**** Get database ****/
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("testdb");
		 
			/**** Get collection / table from 'testdb' ****/
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = db.getCollection("user");
		 
			/**** Insert ****/
			// create a document to store key and value
			
//			BasicDBObject document = new BasicDBObject();
//			document.put("_id", 1);
//			document.put("name", "vishnu");
//			document.put("age", 30);
//			document.put("createdDate", new Date());
//			table.insert(document);
//			
//			BasicDBObject document1 = new BasicDBObject();
//			document.put("_id", 2);
//			document1.put("name", "prasad");
//			document1.put("age", 30);
//			document1.put("createdDate", new Date());
//			table.insert(document1);

		 
			/**** Find and display ****/
	        DBObject query = BasicDBObjectBuilder.start().add("_id", 2).get();
	        DBCursor cursor1 = table.find(query);
	        System.out.println("With id :");
	        while(cursor1.hasNext()){
	            System.out.println(cursor1.next());
	        }
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", "prasad");
		 
			DBCursor cursor = table.find(searchQuery);
			
			System.out.println("Selected data ");
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
//		 
//			/**** Update ****/
//			// search document where name="mkyong" and update it with new values
//			BasicDBObject query = new BasicDBObject();
//			query.put("name", "mkyong");
//		 
//			BasicDBObject newDocument = new BasicDBObject();
//			newDocument.put("name", "mkyong-updated");
//		 
//			BasicDBObject updateObj = new BasicDBObject();
//			updateObj.put("$set", newDocument);
//		 
//			table.update(query, updateObj);
//		 
//			/**** Find and display ****/
//			BasicDBObject searchQuery2 
//			    = new BasicDBObject().append("name", "mkyong-updated");
//		 
//			DBCursor cursor2 = table.find(searchQuery2);
//		 
//			while (cursor2.hasNext()) {
//				System.out.println(cursor2.next());
//			}
		 
			/**** Done ****/
			System.out.println("Done");
		 
		    } catch (UnknownHostException e) {
			e.printStackTrace();
		    } catch (MongoException e) {
			e.printStackTrace();
		    }
		 
		  }
}
