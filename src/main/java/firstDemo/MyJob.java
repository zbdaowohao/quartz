package firstDemo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import quartz.utils.CommonUtil;

/**
 * ÿ��Scheduleִ��jobʱ�����ڵ���execute����ǰ�ᴴ��һ���µ�jobʵ����
 * ��JobExecutionContext���ݸ�Job��execute()������Job�ܹ�ͨ��
 * JobExecutionContext������ʵ�Quartz����ʱ�Ļ����Լ�Job�����������ϸ��
 * ��������ɺ󣬹�����job����ʵ���ᱻ�ͷţ��ͷ�ʵ���ᱻ�������ջ��ƻ��ա�
 */
public class MyJob implements Job {

	// Context Jobִ�е�������
	public void execute(JobExecutionContext Context)
			throws JobExecutionException {
		// ��д�����ҵ���߼�
		System.out.println("Current time is: " + CommonUtil.getCurrentTime());
		System.out.println("Hello World");
	}

}
