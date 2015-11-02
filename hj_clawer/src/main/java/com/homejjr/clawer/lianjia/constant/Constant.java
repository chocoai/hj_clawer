package com.homejjr.clawer.lianjia.constant;

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
	
	public static final String CHARSET = "utf-8";
	
	public static Date REPORT_DATE = new Date();
	public static String CITY_DICT;
	public static String DISTRICT_DICT;
	public static String CIRCLE_DICT;
	public static String CIRCLE;
	public static String INDEX_PAGE_URL;
	public static String START_PAGE_URL;
	public static String PAGE_LINK_FILE_PATH;
	public static String RESOURCE_LINK_FILE_PATH;
	public static String RESOURCE_JSON_FILE_PATH;
	public static String RESOURCE_SHUFFLE_JSON_FILE_PATH;
	public static String WECHAT_JSON_FILE_PATH;
	public static String WECHAT_JSON_INSERT_FILE_PATH;
	public static String WECHAT_JSON_UPDATE_FILE_PATH;
	
	public static int SLEEP_TIME;
	public static String[] NICK_NAME_LIST;
	
	public static int DEFAULT_USER_ID;
	public static String DEFAULT_PHONE_NO;
	public static boolean IS_BIND_PHONE_NO;
	
	public static boolean IS_MODIFY_PRICE;
	public static boolean IS_SHUFFLE;
	public static boolean IS_IP_ADDRESS_PROXY;
	
	public static String NEW_RESOURCE_PATH;
	
	public static String INFO_FROM;
	
	public static void getInitParameters() 
	{
		loadLogBackConfig();
		loadProxoolConfig();
		
		INDEX_PAGE_URL = PropertyUtil.getProperty("config.ini", "INDEX_PAGE_URL");
		START_PAGE_URL = PropertyUtil.getProperty("config.ini", "START_PAGE_URL");
		CITY_DICT = PropertyUtil.getProperty("config.ini", "CITY_DICT");
		DISTRICT_DICT = PropertyUtil.getProperty("config.ini", "DISTRICT_DICT");
		CIRCLE_DICT = PropertyUtil.getProperty("config.ini", "CIRCLE_DICT");
		CIRCLE = PropertyUtil.getProperty("config.ini", "CIRCLE");
		PAGE_LINK_FILE_PATH = PropertyUtil.getProperty("config.ini", "PAGE_LINK_FILE_PATH");
		RESOURCE_LINK_FILE_PATH = PropertyUtil.getProperty("config.ini", "RESOURCE_LINK_FILE_PATH");
		RESOURCE_JSON_FILE_PATH = PropertyUtil.getProperty("config.ini", "RESOURCE_JSON_FILE_PATH");
		RESOURCE_SHUFFLE_JSON_FILE_PATH = PropertyUtil.getProperty("config.ini", "RESOURCE_SHUFFLE_JSON_FILE_PATH");
		WECHAT_JSON_FILE_PATH = PropertyUtil.getProperty("config.ini", "WECHAT_JSON_FILE_PATH");
		NEW_RESOURCE_PATH = PropertyUtil.getProperty("config.ini", "NEW_RESOURCE_PATH");
		
		PAGE_LINK_FILE_PATH = PAGE_LINK_FILE_PATH.replaceAll("#FILE_NAME#", DateUtil.Date2String(REPORT_DATE, "yyyy-MM-dd")+"_page_link.txt");
		RESOURCE_LINK_FILE_PATH = RESOURCE_LINK_FILE_PATH.replaceAll("#FILE_NAME#", DateUtil.Date2String(REPORT_DATE, "yyyy-MM-dd")+"_resource_link.txt");
		RESOURCE_JSON_FILE_PATH = RESOURCE_JSON_FILE_PATH.replaceAll("#FILE_NAME#", DateUtil.Date2String(REPORT_DATE, "yyyy-MM-dd") + "_resource_json.txt");
		RESOURCE_SHUFFLE_JSON_FILE_PATH = RESOURCE_SHUFFLE_JSON_FILE_PATH.replaceAll("#FILE_NAME#", DateUtil.Date2String(REPORT_DATE, "yyyy-MM-dd") + "_resource_shuffle_json.txt");
		WECHAT_JSON_FILE_PATH = WECHAT_JSON_FILE_PATH.replaceAll("#FILE_NAME#", DateUtil.Date2String(REPORT_DATE, "yyyy-MM-dd")+"_wechat_post_json");
		NEW_RESOURCE_PATH = NEW_RESOURCE_PATH.replaceAll("#FILE_NAME#", DateUtil.Date2String(REPORT_DATE, "yyyy-MM-dd")+"_new_"+Constant.DISTRICT_DICT + "_" + Constant.CIRCLE_DICT + ".xls");
		
		WECHAT_JSON_INSERT_FILE_PATH = WECHAT_JSON_FILE_PATH + "_insert.txt";
		WECHAT_JSON_UPDATE_FILE_PATH = WECHAT_JSON_FILE_PATH + "_update.txt";
		
		SLEEP_TIME = PropertyUtil.getInt("config.ini", "SLEEP_TIME");
		
		NICK_NAME_LIST = PropertyUtil.getStrArray("config.ini", "NICK_NAME_LIST");
		DEFAULT_USER_ID = PropertyUtil.getInt("config.ini", "DEFAULT_USER_ID");
		DEFAULT_PHONE_NO = PropertyUtil.getProperty("config.ini", "DEFAULT_PHONE_NO");
		IS_BIND_PHONE_NO = PropertyUtil.getBoolean("config.ini", "IS_BIND_PHONE_NO");
		IS_MODIFY_PRICE = PropertyUtil.getBoolean("config.ini", "IS_MODIFY_PRICE");
		IS_SHUFFLE = PropertyUtil.getBoolean("config.ini", "IS_SHUFFLE");
		IS_IP_ADDRESS_PROXY = PropertyUtil.getBoolean("config.ini", "IS_IP_ADDRESS_PROXY");
		INFO_FROM = PropertyUtil.getProperty("config.ini", "INFO_FROM","0");
		
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
