package quartz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static String getCurrentTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	public static String formatTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

}
