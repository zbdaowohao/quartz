package firstDemo;

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

	public static void main(String[] args) {
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
				.withIdentity("myJob", "group1").build();
		System.out.println("jobDetail`s name: " + jobDetail.getKey().getName());
		System.out.println("jobDetail`s group: " + jobDetail.getKey().getGroup());
		System.out.println("jobDetail`s jobClass: " + jobDetail.getJobClass().getName());
		
		// 创建一个Trigger实例，定义该Job执行规则(立即执行，其每隔2s重复执行)
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
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
			System.out.println("Start time is: "
					+ CommonUtil.getCurrentTime());
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
