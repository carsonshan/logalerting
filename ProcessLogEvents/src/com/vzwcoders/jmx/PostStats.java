/*
 * Main.java - main class for the Hello MBean and QueueSampler MXBean example.
 * Create the Hello MBean and QueueSampler MXBean, register them in the platform
 * MBean server, then wait forever (or until the program is interrupted).
 */

package com.vzwcoders.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class PostStats {
    /* For simplicity, we declare "throws Exception".
       Real programs will usually want finer-grained exception handling. */
    public static void main(String[] args) throws Exception {
        // Get the Platform MBean Server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

	// Construct the ObjectName for the Hello MBean we will register
	ObjectName mbeanName = new ObjectName("com.vzwcoders.jmx:type=LogProcessor");

	// Create the LogProcessor World MBean
	final LogProcessor mbean = new LogProcessor();

	// Register the Hello World MBean
	mbs.registerMBean(mbean, mbeanName);
        System.out.println("Waiting for incoming requests...");
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
				mbean.incrMessageCount(1);
				mbean.incrQCount(2);
				System.out.println("Incrementing msg count "+mbean.getMessageCount());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
		}).start();
        Thread.sleep(Long.MAX_VALUE);
    }
}
