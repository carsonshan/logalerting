package com.vzwcoders.jmx.client;

import java.io.IOException;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.vzwcoders.vo.MsgConsumerVO;

public class MsgConsumerStatsClient {
	 private static MBeanServerConnection mbsc;
	 private static JMXServiceURL url;
	 private static JMXConnector jmxc;
     public static void main(String[] args) throws Exception {
    	MsgConsumerStatsClient s=new MsgConsumerStatsClient();
        s.getBeanData();
    }
     public static void init(){
    	 try {
    		 echo(" MsgConsumerStatsClient Getting connection");
			getConnection();
			echo("MsgConsumerStatsClient JMX Client successfully connected");
			
		} catch (Exception e) {
			e.printStackTrace();
			MsgConsumerStatsClient.close();
		}
     }
	public static  MsgConsumerVO getBeanData() throws MalformedObjectNameException {
		if(jmxc==null) return null;
		echo("MsgConsumerStatsClient Getting bean");
		MsgConsumerVO v=null;
		try {
			MsgConsumerMBean mbeanProxy = getProxyBean();
			v = new MsgConsumerVO();
			v.setMsgReceiveCount(mbeanProxy.getReceiveMessageCount());
			v.setDbInsertCount(mbeanProxy.getDBInsertCount());
			v.setStatus("Running");
		} catch (Exception e) {
			e.printStackTrace();
			MsgConsumerStatsClient.close();
			v=null;
		}
       
        return v;
	}

	public static  MsgConsumerMBean getProxyBean()
			throws MalformedObjectNameException {
        ObjectName mbeanName = new ObjectName("com.vzwcoders.jmx:type=MsgConsumerMBean");
        MsgConsumerMBean mbeanProxy =
            JMX.newMBeanProxy(mbsc, mbeanName, MsgConsumerMBean.class, true);
		return mbeanProxy;
	}

    private static void echo(String msg) {
        System.out.println(msg);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void  getConnection() throws Exception{
    	if(url==null){
    		echo("MsgConsumerMBean Getting service url");
        url =       new JMXServiceURL("service:jmx:rmi:///jndi/rmi://113.128.162.72:9998/jmxrmi");
         jmxc = JMXConnectorFactory.connect(url, null);
        echo("\nMsgConsumerMBean Get an MBeanServerConnection");
         mbsc = jmxc.getMBeanServerConnection();
    	}
    }
    public static void close(){
    	url=null;
    	try {
			if(jmxc!=null)jmxc.close();
			jmxc=null;
		} catch (IOException e) {
			e.printStackTrace();
		}
    	mbsc=null;
    }
}
