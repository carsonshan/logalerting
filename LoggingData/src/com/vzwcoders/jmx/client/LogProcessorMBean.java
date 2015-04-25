package com.vzwcoders.jmx.client;
public interface LogProcessorMBean {
	public void incrMessageCount(int size);
	public void incrQCount(int size);
	public int getMessageCount();
	public int getQCount();
}
