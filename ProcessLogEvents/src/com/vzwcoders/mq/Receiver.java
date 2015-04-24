package com.vzwcoders.mq;

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

import com.vzwcoders.processor.ProcessLog;

public class Receiver {

	private ConnectionFactory factory = null;

	private Connection connection = null;

	private Session session = null;

	private Destination destination = null;

	private MessageConsumer consumer = null;
	
	private String alertMsgs[]=new String[5];

	public Receiver() {

	}

	public void receiveMessage() {

		try {
			int count=0;
			while (true) {
				System.out.println("Waiting for message ");
				Message message = consumer.receive();
				if (message instanceof TextMessage) {
					TextMessage text = (TextMessage) message;
					String m=text.getText();
					System.out.println("Message is : " +m );
					if(count>=5){
						System.out.println("Started the threds");
						new ProcessLog(alertMsgs).start();
						count=0;
					}
					alertMsgs[count++]=m;
				}
				Thread.sleep(100);
			}

		} catch (Exception e) {
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
		Receiver receiver = new Receiver();
		receiver.init();
		receiver.receiveMessage();
	}
}
