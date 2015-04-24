package com.vzwcoders.vo;

import java.sql.Timestamp;
import java.util.Date;

public class LogEvent {

	@Override
	public String toString() {
		return "LogEvent [id=" + id + ", data=" + data + ", keyWords="
				+ keyWords + ", createdTimeStamp=" + createdTimeStamp + "]";
	}
	public long id;
	public String data;
	public String keyWords;
	public Timestamp createdTimeStamp;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LogEvent(long id, String data, String keyWords, Timestamp createdTimeStamp) {
		super();
		this.id = id;
		this.data = data;
		this.keyWords = keyWords;
		this.createdTimeStamp = createdTimeStamp;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	
	
	
}
