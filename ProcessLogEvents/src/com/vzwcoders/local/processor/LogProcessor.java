package com.vzwcoders.local.processor;

import java.util.HashSet;
import java.util.Set;

import javax.jms.JMSException;

import com.vzwcoders.localq.LocalReceiver;
import com.vzwcoders.localq.LocalSender;
import com.vzwcoders.mq.MsgSender;

public class LogProcessor{
	public static Set<String> keywords=new HashSet<String>();
	static{
		keywords.add("beautiful");
		keywords.add("kindness");
	}
	public static MsgSender s=new MsgSender();
	public static void main(String[] args) throws Exception{
		//srun(args);
		
	}
	public  void run()  {
		try {
			LocalSender ls=new LocalSender(System.getProperty("fileName"));
			ls.init();
			ls.start();
			LogProcessor.s.init();
			LocalReceiver lr=new LocalReceiver();
			lr.init();
			lr.receiveMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}