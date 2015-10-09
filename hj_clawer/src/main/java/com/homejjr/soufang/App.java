package com.homejjr.soufang;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.homejjr.clawer.util.HttpUtil;

public class App 
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
	
	public static void outputAgent(String agentLink) throws Exception {
		
		String html = HttpUtil.HttpGet(agentLink);
		//System.out.println(html);
		Document doc = Jsoup.parse(html);
		//Document doc = Jsoup.parse(new File("./input/html.txt"), "utf-8");
		StringBuilder buf = new StringBuilder();
		String agentName = "";
		try {
			agentName = doc.select("DIV[class=rzname floatl]").get(0).text();
		} catch(Exception e) {}
		System.out.println(agentName);
		buf.append(agentName).append(",");
		String agentPhone = "";
		try {
			agentPhone = doc.select("SPAN[class=phonenum]").get(0).text();
		} catch(Exception e) {}
		System.out.println(agentPhone);
		buf.append(agentPhone).append(",");
		
		String company = null;
		try {
			company = doc.select("UL[class=cont02 mb10]").get(0).text();
		} catch(Exception e) {}
		System.out.println(company);
		buf.append(company).append("\n");
		FileUtils.write(new File("./output/soufang/soufang_agent.csv"), buf.toString(), true);
	}
	
	public static void main(String[] args) throws Exception {
		List<String> agentLinks = new ArrayList<String>();
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
		FileUtils.writeLines(new File("./output/soufang/sofang_agent_new.csv"), agentLinks, true);
	}
}
