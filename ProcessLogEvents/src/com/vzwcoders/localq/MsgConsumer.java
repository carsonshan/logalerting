package com.vzwcoders.localq;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.vzwcoders.dao.EventDAO;
import com.vzwcoders.processor.LogProcessor;
import com.vzwcoders.vo.LogEvent;

public class MsgConsumer {

	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;
	private int count=0;
	
	public MsgConsumer() {

	}

	public void receiveMessage() {

		try {
			int count=0;
			while (true) {
				System.out.println("Waiting for message");
				Message message = consumer.receive();
				if (message instanceof TextMessage) {
					TextMessage text = (TextMessage) message;
					String m=text.getText();
					if(m==null || m.length()==0) continue;
						String k[]=m.split("#KEY#");
						if(k!=null && k.length!=2){
							System.out.println("Message format is not proper "+m);
							continue;
						}
					Set<String> matchs=new HashSet<String>();
					new EventDAO().insertEventLog(new LogEvent(count++,k[0] ,k[1], new Timestamp(new Date().getTime())));
						System.out.println("inserted in to db");
				}
			}

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void init() throws JMSException {
		factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		connection = factory.createConnection();
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue("logeventq");
		consumer = session.createConsumer(destination);
	}

	public static void main(String[] args) throws Exception {
		MsgConsumer receiver = new MsgConsumer();
		receiver.init();
		receiver.receiveMessage();
	}
}
