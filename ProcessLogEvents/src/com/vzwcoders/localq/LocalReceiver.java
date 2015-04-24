package com.vzwcoders.localq;

import javax.jms.JMSException;

import com.vzwcoders.local.processor.ProcessLog;

public class LocalReceiver {

	private static int BATCH_SIZE = 1;
	private String alertMsgs[] = new String[1];

	public LocalReceiver() {

	}

	public void receiveMessage() {

		int count = 0;
		while (true) {
			System.out.println("Local msg Waiting for message");
			String m = LocalSender.bq.poll();
			if(m==null){
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			System.out.println("Local Message is : " + m);
			if (count == BATCH_SIZE) {
				new ProcessLog(alertMsgs).start();
				count = 0;
			} else {
				alertMsgs[count++] = m;
			}
			
		}
	}

	public void init() throws JMSException {
		System.out.println("Staring the LocalReceiver ");
	}

	public static void main(String[] args) throws Exception {
		LocalReceiver receiver = new LocalReceiver();
		receiver.init();
		receiver.receiveMessage();
	}
}
