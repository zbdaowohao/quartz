package firstDemo;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import quartz.utils.CommonUtil;

public class CronlTriggerSchedule {

	public static void main(String[] args) {
		Date startDate = new Date();
		startDate.setTime(startDate.getTime() + 4000);
		Date endDate = new Date();
		endDate.setTime(endDate.getTime() + 6000);
		
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("myJob", "group1").build();

		/**
		 * CronTrigger：基于日历的调度器，而不是像SimpleTrigger那样精确的指定间隔时间。
		 * cron表达式：[秒][分][小时][日][月][周][年]
		 * 每秒执行一次（,代表或，-代表至，*代表每，/代表每隔，？代表忽略，年可以不填）
		 * 2017年内每天10点15分触发----- 0 15 10 * * * 2017
		 * 每天的14点整至14:59:55，以及18点整至18：59：55----- 0/5 ？ 14,18 ？ ？ ？ 
		 */
		// 每1s执行一次，无限重复下去
		CronTrigger trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("* * * * * ? *")).build();

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
