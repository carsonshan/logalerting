package com.vzwcoders.jmx;

public interface LogProcessorMBean {
	public void incrMessageCount(int size);
	public void incrQCount(int size);
	public int getMessageCount();
	public int getQCount();
	public String getStartTime();
}
