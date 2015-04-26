package com.vzwcoders.local.processor;

import java.util.HashSet;
import java.util.Set;

import javax.jms.JMSException;

import com.vzwcoders.jmx.JMXUtil;
import com.vzwcoders.localq.LocalReceiver;
import com.vzwcoders.localq.LocalSender;
import com.vzwcoders.mq.MsgSender;
import com.vzwcoders.signal.SignalListener;
import com.vzwcoders.util.FileUtil;
import com.vzwcoders.util.LogStatsRunner;

public class LogProcessor extends Thread{
	public static Set<String> keywords=new HashSet<String>();
	public static int LINES_READ_FROM_FILE;
	public static long MSG_COUNT_TO_Q;
	public static int TOT_MSG_COUNT;
	static{
		init();
	}
	public static void init() {
		keywords.clear();
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
			String fileName=System.getProperty("fileName");
			if(fileName==null || fileName.length()==0){
				System.out.println("Please provide File name in commandline argument!!!!");
				System.exit(0);
			}
			System.out.println("Log Processor is starting Version X0007");
			JMXUtil.init();
			LocalSender ls=new LocalSender(fileName);
			ls.start();
			LogProcessor.s.init();
			System.out.println("Starting Receiver ...");
			LocalReceiver lr=new LocalReceiver();
			lr.start();
			//refresh signal listener
			new SignalListener().start();
			new LogStatsRunner().start();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public void initJMX(){
		
	}
}