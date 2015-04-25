package com.vzwcoders.jmx.client;

import java.io.IOException;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.vzwcoders.vo.LogProcessorVO;

public class StatsClient {
	 private static MBeanServerConnection mbsc;
	 private static JMXServiceURL url;
	 private static JMXConnector jmxc;
     public static void main(String[] args) throws Exception {
    	StatsClient s=new StatsClient();
        s.getBeanData();
    }
     public static void init(){
    	 try {
    		 echo("Getting connection");
			getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			StatsClient.close();
		}
     }
	public static  LogProcessorVO getBeanData() throws MalformedObjectNameException {
		if(jmxc==null) return null;
		echo("Getting bean");
		LogProcessorVO v=null;
		try {
			LogProcessorMBean mbeanProxy = getProxyBean();
			v = new LogProcessorVO();
			v.setMsgCount(mbeanProxy.getMessageCount());
			v.setqCount(mbeanProxy.getQCount());
			v.setStatus("Running");
		} catch (Exception e) {
			e.printStackTrace();
			StatsClient.close();
			v=null;
		}
       
        return v;
	}

	public static  LogProcessorMBean getProxyBean()
			throws MalformedObjectNameException {
        ObjectName mbeanName = new ObjectName("com.vzwcoders.jmx:type=LogProcessor");
        LogProcessorMBean mbeanProxy =
            JMX.newMBeanProxy(mbsc, mbeanName, LogProcessorMBean.class, true);
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
    		echo("Getting service url");
        url =       new JMXServiceURL("service:jmx:rmi:///jndi/rmi://192.168.0.4:9999/jmxrmi");
         jmxc = JMXConnectorFactory.connect(url, null);
        echo("\nGet an MBeanServerConnection");
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
