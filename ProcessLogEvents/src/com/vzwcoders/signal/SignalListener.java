package com.vzwcoders.signal;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.vzwcoders.local.processor.LogProcessor;

public class SignalListener extends Thread {
  public void run() {
    int PORT = 4445;
    byte[] buf = new byte[1000];
    DatagramSocket sk =null;
    try {
    	DatagramPacket dgp = new DatagramPacket(buf, buf.length);
    	LogProcessor l=new LogProcessor();
		 sk = new DatagramSocket(PORT);
		System.out.println("Listing for refresh command");
		while (true) {
		  sk.receive(dgp);
		  String rcvd = new String(dgp.getData(), 0, dgp.getLength()) + ", from address: "
		      + dgp.getAddress() + ", port: " + dgp.getPort();
		  System.out.println(rcvd);
		  LogProcessor.init();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		sk.close();
	}
  }
  public static void main(String[] args) {
	new SignalListener().run();
}
}
