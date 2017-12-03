package firstDemo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

import quartz.utils.CommonUtil;

/**
 * 每次Schedule执行job时，他在调用execute方法前会创建一个新的job实例，
 * 将JobExecutionContext传递给Job的execute()方法，Job能够通过
 * JobExecutionContext对象访问到Quartz运行时的环境以及Job本身的数据明细，
 * 当调用完成后，关联到job对象实例会被释放，释放实例会被垃圾回收机制回收。
 */
public class MyJob implements Job {
	
	/**
	 * JobDataMap可以通过get、set方法进行设置,
	 * 属性值=map的key即可
	 */
	private String myJobDataMap1;
	
	private int myJobDataMap2;
	
	private Float myJobDataMap3;
	
	private Long myJobDataMap4;

	// Context Job执行的上下文
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		/**
		 * JobDataMap:在任务调度时JobDataMap存储在JobExecutionContext中,
		 * JobDataMap可以用来装载任何可序列化的数据对象,当job实例对象被执行时这些参数对象会传递给他,
		 * JobDataMap实现了JDK的Map接口，并且添加一些非常方便的方用存取基本数据类型。
		 */
		
		// 编写具体的业务逻辑
		System.out.println("Current time is: " + CommonUtil.getCurrentTime());
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("My job name is : " + jobKey.getName() + " , my group is : " + jobKey.getGroup());
		TriggerKey triggerKey = context.getTrigger().getKey();
		System.out.println("My trigger name is : " + triggerKey.getName() + " , my group is : " + triggerKey.getGroup());
		
		/**
		 * getMergedJobDataMap是将二者的map进行合并,
		 * 可通过key获取JobDetail、Trigger中的value,
		 * 如果二者的key一样则Trigger的value会覆盖JobDetail的value
		 */
		/*JobDataMap dataMap = context.getMergedJobDataMap();
		System.out.println("dataMapInt: "+dataMap.getLong("myJobDataMap2"));
		System.out.println("dataMapLong: "+dataMap.getLong("myJobDataMap4"));
		
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String myJobDataMap1 = jobDataMap.getString("myJobDataMap1");
		int myJobDataMap2 = jobDataMap.getInt("myJobDataMap2");
		
		JobDataMap triggerDataMap = context.getTrigger().getJobDataMap();
		Float myJobDataMap3 = triggerDataMap.getFloat("myJobDataMap3");
		Long myJobDataMap4 = triggerDataMap.getLong("myJobDataMap4");
		
		System.out.println(myJobDataMap1 + myJobDataMap2 + myJobDataMap3 + myJobDataMap4);*/
		
		System.out.println(myJobDataMap1 + myJobDataMap2 + myJobDataMap3 + myJobDataMap4);
	}

	public String getMyJobDataMap1() {
		return myJobDataMap1;
	}

	public void setMyJobDataMap1(String myJobDataMap1) {
		this.myJobDataMap1 = myJobDataMap1;
	}

	public int getMyJobDataMap2() {
		return myJobDataMap2;
	}

	public void setMyJobDataMap2(int myJobDataMap2) {
		this.myJobDataMap2 = myJobDataMap2;
	}

	public Float getMyJobDataMap3() {
		return myJobDataMap3;
	}

	public void setMyJobDataMap3(Float myJobDataMap3) {
		this.myJobDataMap3 = myJobDataMap3;
	}

	public Long getMyJobDataMap4() {
		return myJobDataMap4;
	}

	public void setMyJobDataMap4(Long myJobDataMap4) {
		this.myJobDataMap4 = myJobDataMap4;
	}
	
}
