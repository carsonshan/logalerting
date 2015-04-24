package com.vzwcoders.processor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vzwcoders.dao.EventDAO;
import com.vzwcoders.vo.LogEvent;

public class ProcessLog  extends Thread{
	
	private String alertMsgs[]=new String[10];
	
	public ProcessLog(String[] msg){
		this.alertMsgs=msg;
	}
public static void main(String[] args) {
	
}
public void run(){
	
	System.out.println("Processing new set");
	for(String msg:alertMsgs){
		Set<String> matchs=new HashSet<String>();
		for(String key: LogProcessor.keywords){
			//if(msg.indexOf(key)>-1)
				matchs.add(key);
		}
		if(matchs.size()>0){
			new EventDAO().insertEventLog(new LogEvent(LogProcessor.count++, msg, matchs.toString(), new Timestamp(new Date().getTime())));
			System.out.println("inserted in to db");
		}
	}
}
}