package com.homejjr.clawer.wuba;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.util.ExcelUtil;
import com.homejjr.clawer.wuba.domain.Agent;

public class App 
{
	
	public static void createPageLinks() throws Exception
	{
		List<String> lines = new ArrayList<String>();
		for(int i = 1; i <= 70; i++)
		{
			String pageLink = "http://sh.58.com/yangpu/ershoufang/pn"+i+"/?utm_source=market&spm=b-31580022738699-me-f-824.bdpz_biaoti";
			lines.add(pageLink);
		}
		FileUtils.writeLines(new File("./output/wuba/2015-10-13_page_link.txt"), "utf-8", lines);
	}
	
	public static void createResourceLink() throws Exception
	{
		List<String> baseUriList = FileUtils.readLines(new File("./output/wuba/2015-10-13_page_link.txt"), "utf-8");
		
		List<String> resourceList = new ArrayList<String>();
		
		int i = 1;
		for(String baseUri : baseUriList)
		{
			InputStream in = new URL(baseUri).openStream();
			
			Document doc = Jsoup.parse(in, "utf-8", baseUri);
			
			Elements elements = doc.select("TABLE[class=tbimg]").get(0).select("A[target=_blank]");
			System.out.println("The "+(i++)+"th uri=>" + baseUri);
			for(Element element : elements)
			{
				String resourceLink = element.attr("href");
				if(resourceLink.contains(".shtml"))
				{
					System.out.println(resourceLink);
					resourceList.add(resourceLink);
				}
			}
			Thread.sleep(4000);
		}
		
		FileUtils.writeLines(new File("./output/wuba/2015-10-13_resource_link.txt"), "utf-8", resourceList);
	}
	
	public static void getAgentInfo() throws Exception
	{
		List<String> baseUriList = FileUtils.readLines(new File("./output/wuba/2015-10-13_resource_link.txt"), "utf-8");
		
		File file = new File("./output/wuba/2015-10-13_agent_json.txt");
		
//		if(file.exists())
//		{
//			file.delete();
//		}
		int i = 1;
		for(String baseUri : baseUriList)
		{
			System.out.println("The "+(i++)+"th page=>"+baseUri);
			
			
			
			
			int method = 1;
			
			try
			{
				InputStream in = new URL(baseUri).openStream();
				Document doc = Jsoup.parse(in, "utf-8", baseUri);
				Element nameElement = doc.select("LI[class=jjreninfo_des_jjr]").get(0).select("SPAN").get(0).select("A").get(0);
				String name = nameElement.text();
				System.out.println(name);
				Element phoneElement = doc.select("SPAN[class=t_phone arial c_e22]").get(0);
				String phone = phoneElement.text();
				phone = phone.replaceAll(" ", "");
				System.out.println(phone);
				Element companyElement = doc.select("LI[class=jjreninfo_des_com]").get(0).select("H3").get(0);
				String company = companyElement.text();
				System.out.println(company);
				Agent agent = new Agent();
				agent.setName(name);
				agent.setPhone(phone);
				agent.setCompany(company);
				String json = JSON.toJSONString(agent);
				System.out.println(json);
				FileUtils.write(file, json+"\n", Constant.CHARSET, true);
				Thread.sleep(2000);
			}
			catch(Exception e) {
				method = 2;
			}
			
			try
			{
				if(method == 2)
				{
					InputStream in = new URL(baseUri).openStream();
					Document doc = Jsoup.parse(in, "utf-8", baseUri);
					Element nameElement = doc.select("DIV[class=col_sub sumary]").get(0).select("SPAN[style=float:left;margin-right:10px;]").get(0).select("A").get(0);
					String name = nameElement.text();
					System.out.println(name);
					Element phoneElement = doc.getElementById("t_phone");
					String phone = phoneElement.text();
					phone = phone.replaceAll(" ", "");
					System.out.println(phone);
					
					Agent agent = new Agent();
					agent.setName(name);
					agent.setPhone(phone);
					agent.setCompany("");
					String json = JSON.toJSONString(agent);
					System.out.println(json);
					FileUtils.write(file, json+"\n", Constant.CHARSET, true);
					Thread.sleep(2000);
				}
			}catch(Exception e) {
				method = 3;
			}
			
			try
			{
				if(method == 3)
				{
					InputStream in = new URL(baseUri).openStream();
					Document doc = Jsoup.parse(in, "utf-8", baseUri);
					Element nameElement = doc.select("LI[class=jjreninfo_des_jjr]").get(0).select("SPAN").get(0);
					String name = nameElement.text();
					System.out.println(name);
					Element phoneElement = doc.select("LI[class=jjreninfo_des_jjr]").get(0).select("SPAN").get(2);
					String phone = phoneElement.text();
					phone = phone.replaceAll(" ", "");
					System.out.println(phone);
					
					Agent agent = new Agent();
					agent.setName(name);
					agent.setPhone(phone);
					agent.setCompany("");
					String json = JSON.toJSONString(agent);
					System.out.println(json);
					FileUtils.write(file, json+"\n", Constant.CHARSET, true);
					Thread.sleep(2000);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void exportExl() throws Exception
	{
		List<Agent> agentList = new ArrayList<Agent>();
		Set<String> mobileList = new HashSet<String>();
		List<String> jsonStrList = FileUtils.readLines(new File("./output/wuba/2015-10-13_agent_json.txt"), "utf-8");
		for(String jsonStr : jsonStrList)
		{
			Agent agent = JSON.parseObject(jsonStr, Agent.class);
			if(mobileList.add(agent.getPhone()))
			{
				agentList.add(agent);
			}
		}
		
		ExcelUtil.export(agentList, "./output/wuba/2015-10-13_agent_datas.xls");
	}
	
	public static void main(String[] args) throws Exception
	{
		//createPageLinks();
		//createResourceLink();
		//getAgentInfo();
		exportExl();
	}
}
