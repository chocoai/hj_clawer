package com.homejjr.clawer.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.homejjr.clawer.domain.Resource;
import com.homejjr.clawer.util.HtmlUtil;
import com.homejjr.clawer.util.JSONUtil;

public class ClawerService 
{
	public static void outputResourceLinks() {
		try 
		{
			File file = new File("./output/20151008.txt");
			
			List<String> lines = new ArrayList<String>();
			for(int i = 1; i <= 30; i++) {
				System.out.println(i + "th page start");
				String resourceListURL = "http://sh.lianjia.com/ershoufang/huangxinggongyuan/pg"+i+"/";
				lines.addAll(HtmlUtil.getResourceLinks(resourceListURL));
				Thread.sleep(3000);
	    	}
			FileUtils.writeLines(file, lines, true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void outputJsonResources() {
		try
		{
			File file = new File("./output/20151008_data.txt");
			InputStream is = new FileInputStream("./output/20151008.txt");
			List<String> links = IOUtils.readLines(is, "utf-8");
			List<String> jsonResourceList = new ArrayList<String>();
			for(String link : links) {
				System.out.println("vist=>" + link);
				Resource resource = HtmlUtil.getResourceInfo(link);
				jsonResourceList.add(JSONUtil.Resource2JsonString(resource));
				Thread.sleep(3000);
			}
			FileUtils.writeLines(file, jsonResourceList, true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void generateSQL() {
		List<Resource> resources = new ArrayList<Resource>();
		List<String> sqls = new ArrayList<String>();
		try {
			List<String> jsonList = FileUtils.readLines(new File("./output/20151008_data.txt"), "utf-8");
			
			for(String json : jsonList) {
				Resource resource = JSONUtil.JsonStr2Resource(json);
				resources.add(resource);
			}
			
			
			for(Resource resource : resources) {
				sqls.add(resource.ToSqlString());
			}
			
			FileUtils.writeLines(new File("./output/20151008_insert.sql"), sqls, true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
