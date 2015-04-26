package com.vzwcoders.jmx;

import javax.management.NotificationBroadcasterSupport;

public class LogProcessor
	extends NotificationBroadcasterSupport implements LogProcessorMBean {
    private int messageCount = 1;
    private int qCount = 1;
    public String startTime;
	@Override
	public int getMessageCount() {
		return messageCount;
	}

	@Override
	public void incrMessageCount(int size) {
		messageCount+=size;
	}

	@Override
	public int getQCount() {
		return qCount;
	}

	@Override
	public void incrQCount(int size) {
		qCount+=size;
	}

	@Override
	public String getStartTime() {
		return startTime;
	}

	
}
