package com.vzwcoders.batch;


import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import com.vzwcoders.local.processor.LogProcessor;

public class ExecuteBatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get the quartzFactory bean
		Scheduler scheduler = (Scheduler) ctx.getBean("scheduleFactory");

		//get the task to run or it could have been injected
		LogProcessor dpTask = (LogProcessor) ctx.getBean("logProcessorBeans");

		//this example uses a simple interval schedule, but could be done with a CRON schedule by using the correct Trigger Bean (CronTriggerBean)
		//create job
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
		//MethodInvokingJobDetailFactoryBean test = ne
		jobDetail.setTargetObject(dpTask);
		jobDetail.setTargetMethod("run");
		jobDetail.setName("logProcessorJob");
		jobDetail.setGroup("Test");
		jobDetail.setConcurrent(false);
		jobDetail.afterPropertiesSet();

		//create trigger

		CronTriggerBean cronTrigger = new CronTriggerBean();
		 cronTrigger.setBeanName("logCron");
		 String expression = "* * * * * ?";
		cronTrigger.setCronExpression(expression);
		 cronTrigger.afterPropertiesSet();

		//add to schedule
		scheduler.scheduleJob((JobDetail) jobDetail.getObject(), cronTrigger);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
