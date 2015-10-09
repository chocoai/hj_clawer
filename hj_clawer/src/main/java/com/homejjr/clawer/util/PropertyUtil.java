package com.homejjr.clawer.util;

import java.net.URLDecoder;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtil 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);
	
	public static void setProperty(String resource, String key, Object value) 
	{
		try 
		{
			PropertiesConfiguration config = getConfiguration(resource);
			config.setProperty(key, value);
			config.save();
			LOGGER.info("save key=>{}, value=>{}, into {} config file...", new Object[]{key, value, resource});
		} catch (ConfigurationException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	public static int getInt(String resource, String key)
	{
		PropertiesConfiguration config = getConfiguration(resource);
		return config.getInt(key);
	}
	
	public static long getLong(String resource, String key) 
	{
		PropertiesConfiguration config = getConfiguration(resource);
		return config.getLong(key);
	}
	
	public static String getProperty(String resource, String key) 
	{
		PropertiesConfiguration config = getConfiguration(resource);
		return config.getString(key);
	}
	
	public static long getLong(String resource, String key, long defaultValue) 
	{
		PropertiesConfiguration config = getConfiguration(resource);
		if(config == null)
			return defaultValue;
		return config.getLong(key,defaultValue);
	}
	
	public static String getProperty(String resource, String key, String defaultValue) 
	{
		PropertiesConfiguration config = getConfiguration(resource);
		if(config == null)
			return defaultValue;
		return config.getString(key,defaultValue);
	}
	
	private static PropertiesConfiguration getConfiguration(String resource) 
	{
		try 
		{
			PropertiesConfiguration config = new PropertiesConfiguration(URLDecoder.decode("./config/"+resource,"utf-8"));
			return config;
		} 
		catch (ConfigurationException e) 
		{
			LOGGER.error(e.getMessage());
		}
		catch (Exception e) 
		{
			LOGGER.error(e.getMessage());
		}
		return null;
	}


	
}
