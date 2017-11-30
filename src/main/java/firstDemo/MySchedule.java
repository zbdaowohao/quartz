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
		 * JobDetailΪJobʵ���ṩ�����������ԣ��Լ�JobDataMap��Ա�������ԣ�
		 * �����������ض�Jobʵ����״̬��Ϣ����������Ҫ����JobDetail���������Jobʵ����
		 * name:���������
		 * group:�������ڵ��飨������Ĭ��DEFAULT�� 
		 * jobClass:�����ʵ���� 
		 * jobDataMap:��������ʹ��
		 */
		// ����һ��JovDetailʵ��������ʵ����Job���а�
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("myJob", "group1").build();
		System.out.println("jobDetail`s name: " + jobDetail.getKey().getName());
		System.out.println("jobDetail`s group: " + jobDetail.getKey().getGroup());
		System.out.println("jobDetail`s jobClass: " + jobDetail.getJobClass().getName());
		
		// ����һ��Triggerʵ���������Jobִ�й���(����ִ�У���ÿ��2s�ظ�ִ��)
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(2).repeatForever())
				.build();
		// ����Scheduleʵ��
		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			// ͨ���ṩ��һ��������ȡSchedulerʵ��
			Scheduler scheduler = sf.getScheduler();
			// ��ʼִ��
			scheduler.start();
			System.out.println("Start time is: "
					+ CommonUtil.getCurrentTime());
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
