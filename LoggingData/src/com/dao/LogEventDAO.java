package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.connection.DBConnection;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vo.LogEvent;

public class LogEventDAO {

	public List<LogEvent> getLogEvents()
	{
		List<LogEvent> eventList = new ArrayList<LogEvent>();
		DB schema = DBConnection.getDBSchema();
		DBCollection table = schema.getCollection("LogEvent");
		
		eventList = retrieveData(table);
		
		return eventList;
	}
	
	public List<LogEvent> retrieveData(DBCollection table){
		
		List<LogEvent> eventList = new ArrayList<LogEvent>();
	    DBCursor cursor = table.find();
	    cursor.sort(new BasicDBObject("_id",-1)).limit(10);
	    System.out.println("New sorting!!!!!");
	    try {
	       while(cursor.hasNext()) {
	          DBObject dbobj = cursor.next();
	        //Converting BasicDBObject to a custom Class(Employee)
	          LogEvent logEvent = (new Gson()).fromJson(dbobj.toString(), LogEvent.class);
	          eventList.add(logEvent);
	          System.out.println(logEvent);
	       }
	    } finally {
	       cursor.close();
	    }
	    
	    return eventList;

	}

}
