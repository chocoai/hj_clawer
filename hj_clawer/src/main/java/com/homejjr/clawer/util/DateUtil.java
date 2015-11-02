package com.homejjr.clawer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil 
{
	private static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
	
	public static String Date2String(Date date, String pattern)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} 
		catch(Exception e)
		{
			//LOGGER.error("error",e);
		}
		return "";
	}
	
	public static Date nowTime(Date date) 
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar cal2 = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE,cal2.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND,cal2.get(Calendar.SECOND));
		return cal.getTime();
	}
	
	public static Date String2Date(String strDate, String pattern) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try 
		{
			return sdf.parse(strDate);
		}
		catch(Exception e) 
		{
			LOGGER.error("error",e);
		}
		return null;
	}
	public static void main(String[] args) {
		Date d = String2Date("2015-2-3","yyyy-MM-dd");
		System.out.println(nowTime(d));
	}
}
