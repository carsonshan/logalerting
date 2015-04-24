package com.vzwcoders.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;
import com.vzwcoders.connection.DBConnection;
import com.vzwcoders.vo.LogEvent;

public class EventDAO {

	public boolean insertEventLog(LogEvent logEvent)
	{
		System.out.println("Inserting "+logEvent);
		DB schema = DBConnection.getDBSchema();
		DBCollection table = schema.getCollection("LogEvent");
		Gson gson = new Gson();
		BasicDBObject obj = (BasicDBObject)JSON.parse(gson.toJson(logEvent));
		table.insert(obj);
		return true;
	}
}
