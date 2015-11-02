package com.homejjr.soufang;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.homejjr.clawer.util.HttpUtil;

public class AnJuApp 
{
	
	public static void generatePageLinks() throws Exception {
		List<String> pageLinks = new ArrayList<String>();
		for(int i = 1; i <= 100; i++) {
			String url = "http://esf.sh.fang.com/house-a026/i3" + i + "/";
			pageLinks.add(url);
		}
		FileUtils.writeLines(new File("./output/soufang/pagelink.txt"), pageLinks, true);
	}
	
	public static List<String> getPageLinks() throws Exception {
		return FileUtils.readLines(new File("./output/soufang/pagelink.txt"), "utf-8");
	}
	
	public static void saveAgentLinks(String pageLink) throws Exception {
		
		
		String html = HttpUtil.HttpGet(pageLink);
		//System.out.println(html);
		Document doc = Jsoup.parse(html);
		//Document doc = Jsoup.parse(new File("./input/html.txt"), "utf-8");
		List<String> agentLinks = new ArrayList<String>();
		
		Elements elements = doc.select("A[target=_blank]");
		
		for(Element element : elements) {
			String href = element.attr("href");
			if(href.startsWith("/a/")) {
				String url = "http://esf.sh.fang.com"+href;
				System.out.println(url);
				agentLinks.add(url);
			}
		}
		
		FileUtils.writeLines(new File("./output/soufang/agentlink.txt"), agentLinks, true);
	}
	
	public static List<String> getAgentLinks() throws Exception {
		return FileUtils.readLines(new File("./output/soufang/agentlink.txt"), "utf-8");
	}
	
	public static void outputAgent() throws Exception {
		
		
		
		
		try {
			
			StringBuilder buf = new StringBuilder();
			
			for(int i = 1; i <= 11; i++) {
				
				Thread.sleep(6000);	
				
				
				//东外滩
				String url = "http://shanghai.anjuke.com/tycoon/anshan/p" + i + "-st1";
			
			    System.out.println("page NO="+i);
			    
				String html = HttpUtil.HttpGet(url,new HttpHost("122.226.142.52",3128));
				//System.out.println(html);
				Document doc = Jsoup.parse(html);
				//Document doc = Jsoup.parse(new File("./input/html.txt"), "utf-8");
				
				
			
			//agentName = doc.select("DIV[class=rzname floatl]").get(0).text();
			List<Element> AgentList = doc.select("a[class=name]");
			
			System.out.println("houseList.size()="+AgentList.size());
			 
			for(int j=0;j<AgentList.size();j++){			
				
				
				String agentName = "";				
				agentName=doc.select("a[class=name]").get(j).text();				
				System.out.print(agentName+",");
				buf.append(agentName).append(",");
				
				
				String mobile = "";						
				mobile = doc.select("span[class=mobile_number]").get(j).text();				
				System.out.print(mobile+",");
				buf.append(mobile).append(",");
				
				String company = "";				
				company = doc.select("a[class=job]").get(j).text();				
				System.out.println(company);
				buf.append(company).append("\n");
			}
			}
			
			FileUtils.write(new File("./output/anjuke/anjuke_agent_anshan_10_12.csv"), new String(buf.toString().getBytes("utf-8")), true);
		} catch(Exception e) {
			throw e;
		}
		
	
	}
	
	public static void main(String[] args) throws Exception {
		
		//List<String> urls = IOUtils.readLines(input, "utf-8");
		
		/*for() {
			
			Thread.sleep(3000);
		}*/
		
		/*List<String> agentLinks = new ArrayList<String>();
		List<String> lines = FileUtils.readLines(new File("./output/soufang/soufang_agent.csv"), "utf-8");
		Set<String> phone = new HashSet<String>();
		int lineNo = 1;
		for(String line : lines) {
			String[] array = line.split(",");
			String no = array[1];
			System.out.println("line"+lineNo + ":" + no);
			lineNo++;
			
			if(phone.add(no)) {
				agentLinks.add(line);
			}
			
		}
		FileUtils.writeLines(new File("./output/soufang/sofang_agent_new.csv"), agentLinks, true);*/
		
		outputAgent();
	}
}
