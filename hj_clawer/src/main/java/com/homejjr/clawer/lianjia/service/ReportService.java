package com.homejjr.clawer.lianjia.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.lianjia.domain.Resource;
import com.homejjr.clawer.util.ExcelUtil;

public class ReportService 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);
	
	public static void generateReport(List<Resource> newResourceList)
	{
		File f = new File(Constant.NEW_RESOURCE_PATH);
		
		if(f.exists())
		{
			f.delete();
		}
		
		try
		{
			ExcelUtil.exportResource(newResourceList, Constant.NEW_RESOURCE_PATH);
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
	}
}
