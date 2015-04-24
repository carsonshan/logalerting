package com.vzwcoders.signal;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class RefreshKeywords {
	
	public void sendRefresh(){
	 try {
		DatagramSocket s = new DatagramSocket();
		
		    byte[] buf = new byte[100];

		    InetAddress hostAddress = InetAddress.getByName("localhost");
		      buf = "Refresh".getBytes();

		      DatagramPacket out = new DatagramPacket(buf, buf.length, hostAddress, 4445);
		      s.send(out);
		      System.out.println("Send message to refresh");
		      s.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	   }
	
	public static void main(String[] args) {
		new RefreshKeywords().sendRefresh();
	}
}
