package com.vzwcoders.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class JMXUtil {
	public static LogProcessor logStatsmbean;
	public static MsgConsumer msgConsumerStatsmbean;
	public static void init(){
		 try {
			 System.out.println("Starting  JMX....");
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName logStatsmbeanName = new ObjectName("com.vzwcoders.jmx:type=LogProcessor");
			logStatsmbean = new LogProcessor();
			mbs.registerMBean(logStatsmbean, logStatsmbeanName);
			System.out.println("Successfully Started JMX.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while JMX Init!!!!!!!!!!!!!!!!!!!!!!!");
		}

	}
	public static void initMsgConsumer(){
		 try {
			 System.out.println("Message consumerStarting  JMX....");
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName msgConsumerStatsmbeanName = new ObjectName("com.vzwcoders.jmx:type=MsgConsumerMBean");
			msgConsumerStatsmbean = new MsgConsumer();
			mbs.registerMBean(msgConsumerStatsmbean, msgConsumerStatsmbeanName);
			System.out.println("Successfully Started JMX.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while JMX Init!!!!!!!!!!!!!!!!!!!!!!!");
		}

	}
}
