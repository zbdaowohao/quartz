package firstDemo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import quartz.utils.CommonUtil;

/**
 * 每次Schedule执行job时，他在调用execute方法前会创建一个新的job实例，
 * 将JobExecutionContext传递给Job的execute()方法，Job能够通过
 * JobExecutionContext对象访问到Quartz运行时的环境以及Job本身的数据明细，
 * 当调用完成后，关联到job对象实例会被释放，释放实例会被垃圾回收机制回收。
 */
public class MyJob implements Job {

	// Context Job执行的上下文
	public void execute(JobExecutionContext Context)
			throws JobExecutionException {
		// 编写具体的业务逻辑
		System.out.println("Current time is: " + CommonUtil.getCurrentTime());
		System.out.println("Hello World");
	}

}
