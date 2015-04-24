package com.vzwcoders.local.processor;

import java.util.HashSet;
import java.util.Set;

public class ProcessLog extends Thread {

	private String alertMsgs[] = new String[1];

	public ProcessLog(String[] msg) {
		this.alertMsgs = msg;
	}

	public static void main(String[] args) {

	}

	public void run() {

		matchWords();
	}

	private void matchWords() {
		for (String msg : alertMsgs) {
			Set<String> matchs = new HashSet<String>();
			for (String key : LogProcessor.keywords) {
				// if(msg.toUpperCase().indexOf(key.toUpperCase())>-1)
				if (msg.indexOf(key) > -1)
					matchs.add(key);
			}
			if (matchs.size() > 0) {
				LogProcessor.MSG_COUNT_TO_Q++;
				//System.out.println("Messages posted to queue ");
				LogProcessor.s.sendMessage(msg + "#KEY#" + matchs);
			}
		}
	}
}