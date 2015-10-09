package com.homejjr.clawer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil 
{
	private static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
	
	public static String Date2String(Date date, String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
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
}
