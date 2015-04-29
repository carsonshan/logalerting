package com.vzwcoders.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;
import com.vzwcoders.connection.DBConnection;
import com.vzwcoders.vo.LogEvent;

public class EventDAO {
	static final Gson gson = new Gson();
	public static DBCollection table;
	public boolean insertEventLog(LogEvent logEvent)
	{
		//System.out.println("Inserting "+logEvent);
		BasicDBObject obj = (BasicDBObject)JSON.parse(gson.toJson(logEvent));
		table.insert(obj);
		return true;
	}
	public static void init(){
		DB schema = DBConnection.getDBSchema();
		table = schema.getCollection("LogEvent");
	}
}
