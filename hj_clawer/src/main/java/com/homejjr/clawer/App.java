package com.homejjr.clawer;

import com.homejjr.clawer.constant.Constant;
import com.homejjr.clawer.service.ClawerService;
import com.homejjr.clawer.service.DBService;
import com.homejjr.clawer.util.HtmlUtil;

public class App 
{
	
	
	
    public static void main( String[] args ) throws Exception
    {
    	
    	Constant.getInitParameters();
    	
    	//获取总页数
    	//String pageCount = HtmlUtil.getPageCount(Constant.START_PAGE_URL);
    	
    	//int iPageCount = Integer.parseInt(pageCount);
    	//生成所有列表页面
    	//ClawerService.generatePageLinks2File(iPageCount);
    	
    	//获取每个列表页面中的详细房源页面
    	//ClawerService.generateResourceLinks2File(Constant.SLEEP_TIME);
    	
    	//获取每个房源详细信息转成Json格式存入文件
    	//ClawerService.generateResourceJson2File(Constant.SLEEP_TIME);
    	
    	//插入或者更新最新房源信息
    	ClawerService.mergeData();
    	
    }
}
