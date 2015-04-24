package com.vzwcoders.connection;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class DBConnection {

	public static MongoClient mongo=null;
	public static DB schema = null; 
	public static DB getDBSchema()
	{
		if(null == mongo)
		{
			try
			{
				mongo = new MongoClient("localhost", 27017);
				schema = mongo.getDB("testdb");
		    } catch (UnknownHostException e) {
				e.printStackTrace();
			    } catch (MongoException e) {
				e.printStackTrace();
			    }
		}
		
		return schema;
		
	}
}
