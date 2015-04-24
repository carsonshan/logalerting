package com.vo;

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
	public Date createdTimeStamp;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	
	
	
}
