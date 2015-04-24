package com.vzwcoders.util;
import com.vzwcoders.localq.MsgConsumer;

public class StatsRunner extends Thread {
  public void run() {
    try {
   	System.out.println("Listing for refresh command");
		while (true) {
			Thread.sleep(10000);
			System.out.println("Status :\n Message Recived from Queue "+MsgConsumer.CONSUMER_RECEIVE_MSG_COUNT);
			System.out.println("Status :\n Message Recived inserted to DB "+MsgConsumer.MSG_INSERT_COUNT);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  public static void main(String[] args) {
	new StatsRunner().run();
}
}
