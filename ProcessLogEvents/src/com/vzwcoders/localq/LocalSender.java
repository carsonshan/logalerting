package com.vzwcoders.localq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.jms.JMSException;

import com.vzwcoders.local.processor.LogProcessor;

public class LocalSender extends Thread {

	public static BlockingQueue<String> bq=new ArrayBlockingQueue<String>(1000);
	private  Random r=new Random();
	private long counter;
	private static int WAIT_TIME=100;
	private String fileName;
	public LocalSender(String fileName) {
		this.fileName=fileName;
	}
	
	public void sendMessage(String msg) {
			try {
				bq.put(msg);
				LogProcessor.TOT_MSG_COUNT++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("Sent: " + msg);
	}

	public void init() throws JMSException {
		System.out.println("Staring the Local Sender");
	}

	public static void main(String[] args) throws Exception {
		String c=args[0];
		BufferedReader br=new BufferedReader(new FileReader(new File(c)));
		LocalSender sender = new LocalSender(c);
		sender.init();
		while(true){
			try {
				String msg=br.readLine();
				if(msg==null || msg.length()==0)Thread.sleep(WAIT_TIME);
				else sender.sendMessage(msg);
				
			} catch (Exception e) {
				e.printStackTrace();
				Thread.sleep(WAIT_TIME);
			}
		}

	}
	
	public  void run()  {
		try {
			System.out.println("Started Sender");
			BufferedReader br=new BufferedReader(new FileReader(new File(fileName)));
			while(true){
				try {
					String msg=br.readLine();
					if(msg==null || msg.length()==0)Thread.sleep(WAIT_TIME);
					else sendMessage(msg);
					//Thread.sleep(WAIT_TIME);
				} catch (Exception e) {
					e.printStackTrace();
					Thread.sleep(WAIT_TIME);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
