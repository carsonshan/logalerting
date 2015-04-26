package com.vzwcoders.util;
import com.vzwcoders.local.processor.LogProcessor;

public class LogStatsRunner extends Thread {
  public void run() {
    try {
    	System.out.println("Listing for Stats");
		while (true) {
			Thread.sleep(3000);
			/*System.out.println("Status :\n Total Message "+LogProcessor.TOT_MSG_COUNT);
			System.out.println("Status :\n Message sent to Queue"+LogProcessor.MSG_COUNT_TO_Q);
			System.out.println("Queue Depth "+LocalSender.bq.size());*/
			System.out.println("Writing status to File..." +LogProcessor.LINES_READ_FROM_FILE);
			FileUtil.fileWrite(System.getProperty("fileName"), LogProcessor.LINES_READ_FROM_FILE);
			System.out.println("Completed writing to File");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  public static void main(String[] args) {
	new LogStatsRunner().run();
}
}
