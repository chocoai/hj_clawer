package com.homejjr.clawer.lianjia.service;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.lianjia.domain.Resource;
import com.homejjr.clawer.lianjia.domain.WechatPost;
import com.homejjr.clawer.util.DBUtil;
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
			String pageLinkBaseUrl = "http://sh.lianjia.com/ershoufang/"+Constant.CIRCLE+"/pg";
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
			
			if(resourceLinkFile.isFile() && resourceLinkFile.exists()) 
			{
				resourceLinkFile.delete();
			}
			
			List<String> pageLinkList = FileUtils.readLines(pageLinkFile, "UTF-8");
			Set<String> hash = new HashSet<String>();
			int i = 1;
			for(String pageLink : pageLinkList) 
			{
				if(!hash.add(pageLink)) {
					LOGGER.debug("{}th pageLink => {} already existed skip", new Object[]{i,pageLink});
					continue;
				}
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
			
			if(jsonFilePath.exists()) 
			{
				jsonFilePath.delete();
			}
			
			List<String> links = FileUtils.readLines(resourceLinkFilePath , Constant.CHARSET);
			List<String> jsonResourceList = new ArrayList<String>();
			int i = 1;
			for(String link : links) {
				LOGGER.debug("{}th visit=>{}",new Object[]{i,link});
				Resource resource = HtmlUtil.getResourceInfo(link);
				jsonResourceList.add(JSONUtil.Resource2JsonString(resource));
				FileUtils.write(jsonFilePath, JSONUtil.Resource2JsonString(resource)+"\n", Constant.CHARSET, true);
				i++;
				Thread.sleep(sleepTime);
			}
		}
		catch(Exception e) {
			LOGGER.error("error",e);
		}
	}
	
	public static void shuffleData()
	{
		try
		{
			File jsonFilePath = new File(Constant.RESOURCE_JSON_FILE_PATH);
			File jsonShuffleFilePath = new File(Constant.RESOURCE_SHUFFLE_JSON_FILE_PATH);
			
			if(jsonShuffleFilePath.exists())
			{
				jsonShuffleFilePath.delete();
			}
			
			List<String> lines = FileUtils.readLines(jsonFilePath, Constant.CHARSET);
			
			Collections.shuffle(lines);
			for(String data : lines)
			{
				FileUtils.write(jsonShuffleFilePath, data+"\n", Constant.CHARSET, true);
			}
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
	}
	
	public static void mergeData() 
	{
		try
		{
			File jsonFilePath = new File(Constant.RESOURCE_JSON_FILE_PATH);
			
			if(Constant.IS_SHUFFLE)
			{
				jsonFilePath = new File(Constant.RESOURCE_SHUFFLE_JSON_FILE_PATH);
			}
			
			List<String> lines = FileUtils.readLines(jsonFilePath, Constant.CHARSET);
			List<Resource> resourceList = new ArrayList<Resource>();
			for(String line : lines) {
				Resource res = JSONUtil.JsonStr2Resource(line);
				if(res != null) {
					resourceList.add(JSONUtil.JsonStr2Resource(line));
				}
			}
			DBService.mergeData(resourceList);
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
		
	}
	
	public static void doETL()
	{
		
		Connection con = null;
		Statement stmt = null;
		
		try
		{
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			
			//从DB中获取最新的房源信息(未格式化的数据)
			List<Resource> rawResourceList = ETLService.getOnLineResourceList(stmt);
			
			//将原始房源信息映射成wechatPost的数据格式(未绑定400电话)
			List<WechatPost> rawWechatPostList = ETLService.transferResourceData(rawResourceList);
			
			//获取wechat_post表中已经爬取持久化的房源信息
			List<WechatPost> previousWechatPostList = ETLService.getLianJiaWechatPostList(stmt);
			
			//过滤存在的房源信息
			//List<WechatPost> updateWechatPostList = ETLService.getExistsWechatPostList(rawWechatPostList,previousWechatPostList);
			
			List<WechatPost> insertWechatPostList = ETLService.getNotExistsWechatPostList(rawWechatPostList,previousWechatPostList);
			
			//更新业务表中的数据
			//ETLService.saveOrUpdateWechatPostList(stmt,updateWechatPostList, false);
			
			//绑定400并且逐条插入数据库中
			ETLService.saveOrUpdateWechatPostList(stmt,insertWechatPostList, true);
			
			//将历史未下架的数据更新未下架
			ETLService.disableWechatPostList(stmt,rawResourceList);
			
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
		finally
		{
			DBUtil.close(con,stmt,null);			
		}
	}
}
