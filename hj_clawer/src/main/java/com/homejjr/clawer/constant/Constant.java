package com.homejjr.clawer.constant;

import org.apache.log4j.PropertyConfigurator;
import org.logicalcobwebs.proxool.ProxoolException;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class Constant 
{
	public static void getInitParameters() 
	{
		loadLogBackConfig();
		loadProxoolConfig();
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
