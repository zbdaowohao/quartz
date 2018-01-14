package firstDemo;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import quartz.utils.CommonUtil;

public class SimplTriggerSchedule {

	public static void main(String[] args) {
		Date startDate = new Date();
		startDate.setTime(startDate.getTime() + 4000);
		Date endDate = new Date();
		endDate.setTime(endDate.getTime() + 6000);
		
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("myJob", "group1").build();

		/**
		 * SimpleTrigger：在一个指定时间段内，执行一次或多次任务
		 */
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "group1")
				// 距离当前时间4s后执行一次任务，之后每隔2s重复执行(SimpleTrigger.REPEAT_INDEFINITELY=repeatForever（）)，重复3次，6s后结束
				.startAt(startDate)
				.endAt(endDate)
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(2).withRepeatCount(3)).build();

		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			Scheduler scheduler = sf.getScheduler();
			scheduler.start();
			System.out.println("Start scheduler is: "
					+ CommonUtil.getCurrentTime());
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
