package com.homejjr.clawer.constant;

import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.logicalcobwebs.proxool.ProxoolException;
import org.slf4j.LoggerFactory;

import com.homejjr.clawer.util.DateUtil;
import com.homejjr.clawer.util.PropertyUtil;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class Constant 
{
	public static Date REPORT_DATE = new Date();
	
	public static String INDEX_PAGE_URL;
	public static String START_PAGE_URL;
	public static String PAGE_LINK_FILE_PATH;
	public static String RESOURCE_LINK_FILE_PATH;
	public static String RESOURCE_JSON_FILE_PATH;
	public static int SLEEP_TIME;
	public static void getInitParameters() 
	{
		loadLogBackConfig();
		loadProxoolConfig();
		
		INDEX_PAGE_URL = PropertyUtil.getProperty("config.ini", "INDEX_PAGE_URL");
		START_PAGE_URL = PropertyUtil.getProperty("config.ini", "START_PAGE_URL");
		PAGE_LINK_FILE_PATH = PropertyUtil.getProperty("config.ini", "PAGE_LINK_FILE_PATH");
		RESOURCE_LINK_FILE_PATH = PropertyUtil.getProperty("config.ini", "RESOURCE_LINK_FILE_PATH");
		RESOURCE_JSON_FILE_PATH = PropertyUtil.getProperty("config.ini", "RESOURCE_JSON_FILE_PATH");
		
		PAGE_LINK_FILE_PATH = PAGE_LINK_FILE_PATH.replaceAll("#FILE_NAME#", DateUtil.Date2String(REPORT_DATE, "yyyy-MM-dd")+"_page_link.txt");
		RESOURCE_LINK_FILE_PATH = RESOURCE_LINK_FILE_PATH.replaceAll("#FILE_NAME#", DateUtil.Date2String(REPORT_DATE, "yyyy-MM-dd")+"_resource_link.txt");
		RESOURCE_JSON_FILE_PATH = RESOURCE_JSON_FILE_PATH.replaceAll("#FILE_NAME#", DateUtil.Date2String(REPORT_DATE, "yyyy-MM-dd") + "_resource_json.txt");
		SLEEP_TIME = PropertyUtil.getInt("config.ini", "SLEEP_TIME");
	}
	
	public static void loadLog4jConfig()
	{
		PropertyConfigurator.configure("./config/log4j.properties");
	}
	
	public static void loadLogBackConfig() 
	{
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		JoranConfigurator configurator = new JoranConfigurator();
		configurator.setContext(lc);
		lc.reset();

		try 
		{
			configurator.doConfigure("./config/logback.xml");
		} 
		catch (JoranException e) {}
	}
	
	public static void loadProxoolConfig()
	{
		try 
		{
			org.logicalcobwebs.proxool.configuration.PropertyConfigurator.configure("./config/proxool.properties");
			System.out.println("proxool.properties load success");
		} 
		catch (ProxoolException e) 
		{
			System.err.println("load proxool.properties fail, then the system cannot startup, caused by " + e.getMessage());
			//System.exit(-1);
		}
	}
}
