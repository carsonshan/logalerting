package com.vzwcoders.jmx;

public interface MsgConsumerMBean {
	public void incrReceiveMessageCount(int size);
	public void incrDBInsertCount(int size);
	public int getReceiveMessageCount();
	public int getDBInsertCount();
	public String getStartTime();
}
