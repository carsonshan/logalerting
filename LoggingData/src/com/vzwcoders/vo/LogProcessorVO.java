package com.vzwcoders.vo;

public class LogProcessorVO {
	@Override
	public String toString() {
		return "LogProcessorVO [status=" + status + ", msgCount=" + msgCount
				+ ", qCount=" + qCount + "]";
	}
	public String status;
	public String startTime;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int msgCount;
	public int qCount;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMsgCount() {
		return msgCount;
	}
	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}
	public int getqCount() {
		return qCount;
	}
	public void setqCount(int qCount) {
		this.qCount = qCount;
	}
}
