package com.vzwcoders.util;
import com.vzwcoders.local.processor.LogProcessor;
import com.vzwcoders.localq.LocalSender;

public class LogStatsRunner extends Thread {
  public void run() {
    try {
    	System.out.println("Listing for Stats");
		while (true) {
			Thread.sleep(10000);
			System.out.println("Status :\n Total Message "+LogProcessor.TOT_MSG_COUNT);
			System.out.println("Status :\n Message sent to Queue"+LogProcessor.MSG_COUNT_TO_Q);
			System.out.println("Queue Depth "+LocalSender.bq.size());
	
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  public static void main(String[] args) {
	new LogStatsRunner().run();
}
}
