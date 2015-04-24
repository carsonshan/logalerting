package com.mongo;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import com.vo.Employee;
import com.vo.LogEvent;

public class Mongo3 {

	public void insertObject()
	{
		try {

		MongoClient mongo = new MongoClient("localhost", 27017);
		 
		/**** Get database ****/
		// if database doesn't exists, MongoDB will create it for you
		DB db = mongo.getDB("testdb");
	 
		/**** Get collection / table from 'testdb' ****/
		// if collection doesn't exists, MongoDB will create it for you
		DBCollection table = db.getCollection("LogEvent");

		//DBCollection table = db.getCollection("testCollection");

//		Employee emp = new Employee();
//		emp.setId("1001");
//		emp.setName("John Doe");
		
		LogEvent logEvent = new LogEvent();
		logEvent.setId(102);
		logEvent.setData("Exception Found :  Null Pointer Eception");
		logEvent.setKeyWords("Exception");
		logEvent.setCreatedTimeStamp(new Date());

		//Converting a custom Class(Employee) to BasicDBObject
		Gson gson = new Gson();
		BasicDBObject obj = (BasicDBObject)JSON.parse(gson.toJson(logEvent));
		table.insert(obj);
		findEmployee(new BasicDBObject("id",102),table);
	    } catch (UnknownHostException e) {
			e.printStackTrace();
		    } catch (MongoException e) {
			e.printStackTrace();
		    }



	}
	public void findEmployee(BasicDBObject query, DBCollection table){

	    DBCursor cursor = table.find(query).limit(5);
	    cursor.sort(new BasicDBObject("createdTimeStamp",-1));
	    System.out.println("new code 111222333 ");
	    try {
	       while(cursor.hasNext()) {
	          DBObject dbobj = cursor.next();
	        //Converting BasicDBObject to a custom Class(Employee)
	          LogEvent logEvent = (new Gson()).fromJson(dbobj.toString(), LogEvent.class);
	          System.out.println(logEvent);
	       }
	    } finally {
	       cursor.close();
	    }

	}
	
}
