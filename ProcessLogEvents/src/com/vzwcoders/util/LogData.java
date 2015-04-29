package com.vzwcoders.util;

import java.util.Random;

import org.apache.log4j.Logger;

public class LogData {
	final static Logger logger = Logger.getLogger(LogData.class);
	private static Random r=new Random();
	public static void main(String[] args) throws Exception{
		 
		LogData obj = new LogData();
		while(true)
		{
		obj.runMe(msgs[(r.nextInt(18)%18)]+"-msgid");
		Thread.sleep(50);
		}
 
	}
 
	private void runMe(String parameter){
		logger.info( parameter);
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
