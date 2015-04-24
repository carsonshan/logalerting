package com.vzwcoders.processor;

import java.util.HashSet;
import java.util.Set;

import com.vzwcoders.mq.Receiver;

public class LogProcessor{
	public static Set<String> keywords=new HashSet<String>();
	public static long count=1;
	static{
		keywords.add("beautiful");
		keywords.add("kindness");
	}
	public static void main(String[] args) throws Exception{
		Receiver r=new Receiver();
		r.init();
		r.receiveMessage();
	}
}