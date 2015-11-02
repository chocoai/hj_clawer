package com.homejjr.clawer.lianjia;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.lianjia.dict.Dictionary;
import com.homejjr.clawer.lianjia.domain.Resource;
import com.homejjr.clawer.lianjia.service.ClawerService;
import com.homejjr.clawer.lianjia.service.DBService;
import com.homejjr.clawer.lianjia.service.ReportService;
import com.homejjr.clawer.mail.service.MailService;
import com.homejjr.clawer.util.DateUtil;
import com.homejjr.clawer.util.HtmlUtil;

public class App 
{
	
    public static void main( String[] args ) throws Exception
    {
    	String strReportDate = System.getProperty("REPORT_DATE");
    	
    	String mode = System.getProperty("MODE");
    	
    	if(!StringUtils.isEmpty(strReportDate)) 
    	{
        	Date reportDate = DateUtil.String2Date(strReportDate, "yyyy-MM-dd");
        	
        	Constant.REPORT_DATE = reportDate;
    	}
    	
    	Constant.getInitParameters();
    	
    	Logger logger = LoggerFactory.getLogger(App.class);
    	
    	logger.info("mode={},reportDate=>{}",new Object[]{mode,Constant.REPORT_DATE});
    	
    	if(Dictionary.MODE.CREATE_PAGE_LINK.name().equalsIgnoreCase(mode))
    	{
    		logger.info(Dictionary.MODE.CREATE_PAGE_LINK.getDesc()+"...............");
    		//获取总页数
        	String pageCount = HtmlUtil.getPageCount(Constant.START_PAGE_URL);
        	
        	int iPageCount = Integer.parseInt(pageCount);
        	//生成所有列表页面
        	ClawerService.generatePageLinks2File(iPageCount);
        	logger.info("总共{}个页面,生成结束",pageCount);
    	}
    	
    	if(Dictionary.MODE.CREATE_RESOURCE_LINK.name().equalsIgnoreCase(mode))
    	{
    		logger.info(Dictionary.MODE.CREATE_RESOURCE_LINK.getDesc()+" start...............");
    		
    		//获取每个列表页面中的详细房源页面
        	ClawerService.generateResourceLinks2File(Constant.SLEEP_TIME);
        	logger.info(Dictionary.MODE.CREATE_RESOURCE_LINK.getDesc()+" end...............");
    	}
    	
    	if(Dictionary.MODE.FORMAT_RESOURCE.name().equalsIgnoreCase(mode))
    	{
    		logger.info(Dictionary.MODE.FORMAT_RESOURCE.getDesc()+" start...............");
    		
    		//获取每个房源详细信息转成Json格式存入文件
        	ClawerService.generateResourceJson2File(Constant.SLEEP_TIME);
        	logger.info(Dictionary.MODE.FORMAT_RESOURCE.getDesc()+" end...............");
    	}
    	
    	if(Dictionary.MODE.SHUFFLE_RESOURCE.name().equalsIgnoreCase(mode))
    	{
    		logger.info(Dictionary.MODE.SHUFFLE_RESOURCE.getDesc()+" start...............");
    		
    		//打乱房源列表的顺序
    		ClawerService.shuffleData();
    		logger.info(Dictionary.MODE.SHUFFLE_RESOURCE.getDesc()+" end...............");
    	}
    	
    	if(Dictionary.MODE.MERGE_DATA.name().equalsIgnoreCase(mode))
    	{
    		logger.info(Dictionary.MODE.MERGE_DATA.getDesc()+" start...............");
    		
    		//插入或者更新最新房源信息
        	ClawerService.mergeData();
        	logger.info(Dictionary.MODE.MERGE_DATA.getDesc()+" end...............");
    	}
    	
    	if(Dictionary.MODE.BIZ_ETL.name().equalsIgnoreCase(mode))
    	{
    		logger.info(Dictionary.MODE.BIZ_ETL.getDesc() + " start...........");
    		//1从DB中获取最新的房源信息(未格式化的数据)
    		//2将原始房源信息映射成wechatPost的数据格式(未绑定400电话)
    		//3将房源信息中的电话绑定400后格式化成Json后保存在文件中,并返回处理过的数据集
    		//4获取wechat_post表中已经爬取持久化的房源信息
    		ClawerService.doETL();
    		logger.info(Dictionary.MODE.BIZ_ETL.getDesc() + " end...........");
    	}
    	
    	if(Dictionary.MODE.REPORT.name().equalsIgnoreCase(mode))
    	{
    		logger.info(Dictionary.MODE.REPORT.getDesc() + " start...........");
    		
    		//获取新导入的房源
    		List<Resource> newResourceList = DBService.getNewResource();
    		
    		//生成新房源报表
    		ReportService.generateReport(newResourceList);
    		
    		List<Resource> expiryResourceList = DBService.getExpiryResource();
    		
    		List<Resource> totalResourceList = DBService.getTotalResource();
    		
    		MailService.sendAttrEmail(newResourceList, expiryResourceList,totalResourceList);
    		
    		logger.info(Dictionary.MODE.REPORT.getDesc() + " end...........");
    	}
    	
    }
}
