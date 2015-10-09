package com.homejjr.clawer.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.homejjr.clawer.constant.Constant;
import com.homejjr.clawer.domain.Resource;
import com.homejjr.clawer.util.HtmlUtil;
import com.homejjr.clawer.util.JSONUtil;

public class ClawerService 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ClawerService.class);
	
	public static void generatePageLinks2File(int totalPage) {
		try 
		{
			LOGGER.debug("generate page link ");
			File file = new File(Constant.PAGE_LINK_FILE_PATH);
			List<String> lines = new ArrayList<String>();
			String pageLinkBaseUrl = "http://sh.lianjia.com/ershoufang/huangxinggongyuan/pg";
			for(int i = 1; i <= totalPage; i++) 
			{
				String pageLinkURL = pageLinkBaseUrl+i+"/";
				LOGGER.debug("The {} th PageLink => {}",new Object[]{i,pageLinkURL});
				lines.add(pageLinkURL);
	    	}
			FileUtils.writeLines(file, lines, false);
		} 
		catch(Exception e) 
		{
			LOGGER.error("error",e);
		}
	}
	
	public static void generateResourceLinks2File(int sleepTime) {
		try 
		{
			File resourceLinkFile = new File(Constant.RESOURCE_LINK_FILE_PATH);
			File pageLinkFile = new File(Constant.PAGE_LINK_FILE_PATH);
			
			if(resourceLinkFile.isFile() && resourceLinkFile.exists()) {
				resourceLinkFile.delete();
			}
			
			List<String> pageLinkList = FileUtils.readLines(pageLinkFile, "UTF-8");
			int i = 1;
			for(String pageLink : pageLinkList) 
			{
				LOGGER.debug("{}th pageLink => {}", new Object[]{i,pageLink});
				List<String> subResourceLinkList = HtmlUtil.getResourceLinks(pageLink);
				FileUtils.writeLines(resourceLinkFile, subResourceLinkList, true);
				i++;
				Thread.sleep(sleepTime);
			}
			
		} 
		catch(Exception e) 
		{
			LOGGER.error("error",e);
		}
	}
	
	
	public static void generateResourceJson2File(int sleepTime) {
		try
		{
			File jsonFilePath = new File(Constant.RESOURCE_JSON_FILE_PATH);
			File resourceLinkFilePath = new File(Constant.RESOURCE_LINK_FILE_PATH);
			
			if(jsonFilePath.isFile() && jsonFilePath.exists()) 
			{
				jsonFilePath.delete();
			}
			
			List<String> links = FileUtils.readLines(resourceLinkFilePath , "utf-8");
			List<String> jsonResourceList = new ArrayList<String>();
			int i = 1;
			for(String link : links) {
				LOGGER.debug("{}th visit=>{}",new Object[]{i,link});
				Resource resource = HtmlUtil.getResourceInfo(link);
				jsonResourceList.add(JSONUtil.Resource2JsonString(resource));
				if(i % 10 == 0) 
				{
					FileUtils.writeLines(jsonFilePath, jsonResourceList, true);
					jsonResourceList.clear();
				}
				i++;
				Thread.sleep(sleepTime);
			}
			if(jsonResourceList != null && !jsonResourceList.isEmpty()) 
			{
				FileUtils.writeLines(jsonFilePath, jsonResourceList, true);
			}
		}
		catch(Exception e) {
			LOGGER.error("error",e);
		}
	}
	
	public static void mergeData() 
	{
		try
		{
			File jsonFilePath = new File(Constant.RESOURCE_JSON_FILE_PATH);
			List<String> lines = FileUtils.readLines(jsonFilePath, "utf-8");
			List<Resource> resourceList = new ArrayList<Resource>();
			for(String line : lines) {
				resourceList.add(JSONUtil.JsonStr2Resource(line));
			}
			DBService.mergeData(resourceList);
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
		
	}
}
