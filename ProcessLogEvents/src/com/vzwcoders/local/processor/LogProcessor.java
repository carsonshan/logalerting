package com.vzwcoders.local.processor;

import java.util.HashSet;
import java.util.Set;

import javax.jms.JMSException;

import com.vzwcoders.localq.LocalReceiver;
import com.vzwcoders.localq.LocalSender;
import com.vzwcoders.mq.MsgSender;
import com.vzwcoders.util.FileUtil;

public class LogProcessor{
	public static Set<String> keywords=new HashSet<String>();
	static{
		keywords.addAll(FileUtil.loadProps("c:\\prop.txt"));
		System.out.println("KeyWords "+keywords);
	}
	public static MsgSender s=new MsgSender();
	public static void main(String[] args) throws Exception{
		LogProcessor l=new LogProcessor();
		l.run();
		
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