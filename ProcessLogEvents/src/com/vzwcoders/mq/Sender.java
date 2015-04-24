package com.vzwcoders.mq;

import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	private ConnectionFactory factory = null;

	private Connection connection = null;

	private Session session = null;

	private Destination destination = null;

	private MessageProducer producer = null;
	private  Random r=new Random();
	private long counter;

	public Sender() {

	}

	public void sendMessage() {

		try {

			TextMessage message = session.createTextMessage();

			message.setText(msgs[(r.nextInt(18)%18)]+"-msgid"+(++counter));

			producer.send(message);

			System.out.println("Sent: " + message.getText());

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	public void init() throws JMSException {
		factory = new ActiveMQConnectionFactory(

		ActiveMQConnection.DEFAULT_BROKER_URL);

		connection = factory.createConnection();

		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		destination = session.createQueue("logeventq");

		producer = session.createProducer(destination);
	}

	public static void main(String[] args) throws Exception {

		Sender sender = new Sender();
		sender.init();
		while(true){
			sender.sendMessage();
			Thread.sleep(2000);
		}

	}

	public static String msgs []={"Beauty is not in the face; beauty is a light in the heart. ~Khalil Gibran",
		"Against Him those women sin who torment their skin with potions, stain their cheeks with rouge and extend the line of their eyes with black coloring. Doubtless they are dissatisfied with God’s plastic skill. In their own persons they convict and censure the Artificer of all things. ~Tertullian",
		"That which is striking and beautiful is not always good, but that which is good is always beautiful. ~Ninon de L’Enclos",
		"Had the price of looking been blindness, I would have looked. ~Ralph Ellison, Battle Royal",
		"Some people, no matter how old they get, never lose their beauty — they merely move it from their faces into their hearts. ~Martin Buxbaum",
		"Tell them dear, that if eyes were made for seeing,",
		"Then beauty is its own excuse for being",
		"Beauty and folly are generally companions. ~Baltasar Gracián, translated from Spanish",
		"May she be granted beauty and yet not",
		"Beauty to make a stranger’s eye distraught,",
		"Or hers before a looking-glass, for such,",
		"Being made beautiful overmuch,",
		"Consider beauty a sufficient end,",
		"Lose natural kindness and maybe",
		"The heart-revealing intimacy",
		"That chooses right, and never find a friend....",
		"Beauty always promises, but never gives anything. ~Simone Weil",
		"What humbugs we are, who pretend to live for Beauty, and never see the Dawn! ~Logan Pearsall Smith",
		"The most beautiful view is the one I share with you. ~Author Unknown"};
}
