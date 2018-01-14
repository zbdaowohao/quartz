package firstDemo;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import quartz.utils.CommonUtil;

public class MySchedule {

	public static void main(String[] args) throws InterruptedException {
		Date startDate = new Date();
		startDate.setTime(startDate.getTime() + 3000);
		Date endDate = new Date();
		endDate.setTime(endDate.getTime() + 6000);
		/**
		 * JobDetail为Job实例提供多种设置属性，以及JobDataMap成员变量属性，
		 * 它用来存在特定Job实例的状态信息，调度器需要借助JobDetail对象来添加Job实例。
		 * name:任务的名称
		 * group:任务所在的组（不传入默认DEFAULT） 
		 * jobClass:任务的实现类 
		 * jobDataMap:用来传参使用
		 */
		// 创建一个JovDetail实例，将该实例与Job进行绑定
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("myJob", "group1")
				.usingJobData("myJobDataMap1", "myJobDataMap1")
				.usingJobData("myJobDataMap2", 2).build();
		System.out.println("jobDetail`s name: " + jobDetail.getKey().getName());
		System.out.println("jobDetail`s group: " + jobDetail.getKey().getGroup());
		System.out.println("jobDetail`s jobClass: " + jobDetail.getJobClass().getName());
		
		/**
		 * Quartz中的触发器用来告诉调度程序作业什么时候触发，即Trigger对象是用来触发执行Job的。
		 * JobKey：job实例的标识，触发器被触发时，该指定的job实例会被执行；
		 * StartTime：触发器的时间表首次被触发的时间；
		 * EndTime：指定触发器的不再被触发的时间。
		 */
		// 创建一个Trigger实例，定义该Job执行规则
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "group1")
				.usingJobData("myJobDataMap3", 3.14F)
				.usingJobData("myJobDataMap4", 1000L)
				// 立即执行
				/*.startNow()*/
				// 当前时间3s后执行
				.startAt(startDate)
				// 当前时间6s后停止
				.endAt(endDate)
				// 其每隔2s重复执行
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(2).repeatForever())
				.build();
		
		// 创建Schedule实例
		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			// 通过提供的一个工厂获取Scheduler实例
			Scheduler scheduler = sf.getScheduler();
			// 开始执行
			scheduler.start();
			System.out.println("Start scheduler is: " + CommonUtil.getCurrentTime());
			// 返回最近一要次执行的时间
			scheduler.scheduleJob(jobDetail, trigger);
			// scheduler执行2s后挂起，可通过start()停止挂起继续执行
			Thread.sleep(2000);
			scheduler.standby();
			// 区别于standby，一旦shutdown()则无法被再次开启，若开启会抛异常，shutdown(true)表示等待所有正在执行的job执行完毕再停止；shutdown(true)=shutdown()直接停止
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
