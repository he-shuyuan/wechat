package com.ower.dsyz.common.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 年月日
	 */
	  public static final String  FORMAT_Y_M_D = "yyyy-MM-dd";
	  /**
	   * 格式化日期
	   * @param date
	   * @param format
	   * @return
	   */
	  public static String formatDate(Date date,String format){
		  SimpleDateFormat df = new SimpleDateFormat(format);
		  return df.format(date);
	  }
}
