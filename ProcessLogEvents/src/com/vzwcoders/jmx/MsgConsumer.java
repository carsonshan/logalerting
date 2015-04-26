package com.vzwcoders.jmx;

import javax.management.NotificationBroadcasterSupport;

public class MsgConsumer
	extends NotificationBroadcasterSupport implements MsgConsumerMBean {
	 private int msgCount = 1;
	    private int dbInsertCount = 1;
	@Override
	public void incrReceiveMessageCount(int size) {
		msgCount+=size;
	}

	@Override
	public void incrDBInsertCount(int size) {
		dbInsertCount+=size;
	}

	@Override
	public int getReceiveMessageCount() {
		return msgCount;
	}

	@Override
	public int getDBInsertCount() {
		return dbInsertCount;
	}
   	
}
