package com.vzwcoders.localq;

import java.util.HashSet;
import java.util.Set;

import javax.jms.JMSException;

import com.vzwcoders.jmx.JMXUtil;
import com.vzwcoders.local.processor.LogProcessor;

public class LocalReceiver   extends Thread{

	private static int BATCH_SIZE = 10;
	private String alertMsgs[] = new String[10];

	public LocalReceiver() {

	}

	public void run() {
		System.out.println("Started Receiver....");
		int count = 0;
		while (true) {
			
			String m = LocalSender.bq.poll();
			//System.out.println("Queue Depth "+LocalSender.bq.size()+" msg "+m);
			if(m==null){
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			//System.out.println("Local Message is : " + m);
			if (count == BATCH_SIZE) {
				matchWords(alertMsgs);
				count = 0;
			} else {
				alertMsgs[count++] = m;
			}
			
		}
	}
	private void matchWords(String alertMsgs[]) {
		//System.out.println("Messages posted to queue ");
		for (String msg : alertMsgs) {
			Set<String> matchs = new HashSet<String>();
			for (String key : LogProcessor.keywords) {
				// if(msg.toUpperCase().indexOf(key.toUpperCase())>-1)
				if (msg.indexOf(key) > -1)
					matchs.add(key);
			}
			if (matchs.size() > 0) {
				LogProcessor.MSG_COUNT_TO_Q++;
				try {
					JMXUtil.logStatsmbean.incrQCount(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//System.out.println("Messages posted to queue ");
				LogProcessor.s.sendMessage(msg + "#KEY#" + matchs);
			}
		}
	}
	public void init() throws JMSException {
		System.out.println("Staring the LocalReceiver ");
	}

	public static void main(String[] args) throws Exception {
		LocalReceiver receiver = new LocalReceiver();
		receiver.init();
		receiver.run();
	}
}
