package com.homejjr.clawer.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.lianjia.domain.Resource;

public class HtmlUtil 
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUtil.class);
	
	public static String getPageCount(String indexPageURL) 
	{
		try 
		{
			String html = HttpUtil.HttpGet(indexPageURL);
			if(StringUtils.isEmpty(html))
			{
				throw new RuntimeException("http get respons is empty!!");
			}
			Document doc = Jsoup.parse(html);
			Element element = doc.select("DIV[class=page-box house-lst-page-box]").get(0);
			String jsonPageInfo = element.attr("page-data");
			LOGGER.debug(jsonPageInfo);
			JSONObject jsonObj = JSON.parseObject(jsonPageInfo);
			return jsonObj.getString("totalPage");
		} 
		catch(Exception e) 
		{
			LOGGER.error("error",e);
		} 
		return "";
	}
	
	public static List<String> getResourceLinks(String resourceListURL) {
		Set<String> set = new HashSet<String>();
		List<String> returnList = new ArrayList<String>();
		try
		{
			String html = HttpUtil.HttpGet(resourceListURL);
			
			if(StringUtils.isEmpty(html)) 
			{
				throw new RuntimeException("http get respons is empty!!");
			}
			
			Document doc = Jsoup.parse(html);
			Element contents = doc.getElementById("matchid");
			Elements links = contents.select("A[target=_blank]");
			for(Element link : links) 
			{
				String strLink = Constant.INDEX_PAGE_URL + link.attr("href");
				if(set.add(strLink)) 
				{
					returnList.add(strLink);
					System.out.println(strLink);
					LOGGER.debug(strLink);
				}
			}
		}
		catch(Exception e) 
		{
			LOGGER.error("error",e);
		} 
		return returnList;
	}
	
	public static Resource getResourceInfo(String resourceURL) 
	{
		try
		{
			String html = HttpUtil.HttpGet(resourceURL);
			if(StringUtils.isEmpty(html)) 
			{
				throw new RuntimeException("http get respons is empty!!");
			}
			Document doc = Jsoup.parse(html);
			Resource resource = Doc2JsonResource(doc);
			return resource;
		}
		catch(Exception e) 
		{
			LOGGER.error("error",e);
		}
		return null;
	}
	
	
	public static Resource Doc2JsonResource(Document doc) {
		Resource res = new Resource();
		Elements elements = doc.select("H1");
		String title = elements.get(0).html();
		System.out.println("title => " + title);
		res.setTitle(title);
		
		String resourceId = doc.select("DIV[class=iinfo right]").select("P").select("SPAN").get(1).html();
		System.out.println("resourceId => " + resourceId);
		res.setResourceId(resourceId);
		
		String taxFee = "";
		try {
			taxFee = doc.select("SPAN[class=taxfree-ex]").select("SPAN").get(0).text();
		}catch(Exception e) {
			
		}
		System.out.println("taxFee => " + taxFee);
		res.setTaxFee(taxFee);
		
		String unique = "";
		try {
			unique = doc.select("SPAN[class=unique-ex]").select("SPAN").get(0).text();
		}
		catch(Exception e) {}
		System.out.println("unique => " + unique);
		res.setUnique(unique);
		
		String sumPrice = doc.select("STRONG[class=ft-num]").get(0).text();
		System.out.println("售价sumPrice => " + sumPrice);
		res.setSumPrice(sumPrice);
		
		String sumArea = doc.select("SPAN[class=em-text]").select("I").get(0).text();
//		sumArea = sumArea.replaceAll("㎡", "");
		sumArea = sumArea.replaceAll("/", "");
		sumArea = sumArea.trim();
		System.out.println("面积sumArea => " + sumArea);
		res.setSumArea(sumArea);
		
		String unitPrice = doc.select("DD[class=short]").get(0).text();
//		unitPrice = unitPrice.replaceAll("元/平米", "");
		unitPrice = unitPrice.trim();
		System.out.println("单价unitPrice => " + unitPrice);
		res.setUnitPrice(unitPrice);
		
		String firstPay = doc.select("DD[class=short]").get(1).text();
//		firstPay = firstPay.replaceAll("万", "");
		firstPay = firstPay.trim();
		System.out.println("首付firstPay => " + firstPay);
		res.setFirstPay(firstPay);
		
		String monthlyProvide = doc.select("DD[class=short]").get(2).text();
		System.out.println("月供monthlyProvide => " + monthlyProvide);
		res.setMonthlyProvide(monthlyProvide);
		
		
		String houseType = doc.select("DIV[class=desc-text clear]").select("DL").get(4).select("DD").text();
		System.out.println("户型houseType => " + houseType);
		res.setHouseType(houseType);
		
		
		String direction = doc.select("DIV[class=desc-text clear]").select("DL").get(5).select("DD").text();
		System.out.println("朝向direction => " + direction);
		res.setDirection(direction);
		
		String floor = doc.select("DIV[class=desc-text clear]").select("DL").get(6).select("DD").text();
		System.out.println("楼层floor => " + floor);
		res.setFloor(floor);
		
		
		String neigbour = doc.select("DIV[class=desc-text clear]").select("DL").get(7).select("DD").get(0).select("A").get(0).text();
		System.out.println("小区neigbour => " + neigbour);
		res.setNeigbour(neigbour);
		
		String telphone = doc.select("SPAN[class=ft-num]").get(0).text();
		System.out.println("400电话telphone => " + telphone);
		res.setTelphone(telphone);
		
		String txnCount = doc.select("DIV[class=house-uni]").get(0).select("SPAN[class=num]").get(0).text();
		System.out.println("小区成交记录txnCount => " + txnCount);
		res.setTxnCount(txnCount);
		
		String agentCommentCount = doc.select("DIV[class=house-uni]").get(1).select("SPAN[class=num]").get(0).text();
		System.out.println("经纪人房评agentCommentCount => " + agentCommentCount);
		res.setAgentCommentCount(agentCommentCount);
		
		String viewCount = doc.select("DIV[class=house-uni]").get(2).select("SPAN[class=num]").get(0).text();
		System.out.println("客户看房数viewCount => " + viewCount);
		res.setViewCount(viewCount);
		
		String agentName = "";
		try
		{
			agentName = doc.select("DIV[class=p-del right]").get(0).select("A[class=laishanghai]").get(0).text();
		} catch(Exception e) {}
		System.out.println("经纪人agentName => " + agentName);
		res.setAgentName(agentName);
		
		String agentType = "";
		try {
			agentType = doc.select("DIV[class=p-del right]").get(0).select("SPAN").get(0).text();
		} catch(Exception e) {}
		System.out.println("经纪人类型agentType => " + agentType);
		res.setAgentType(agentType);
		
		String occupationYear = "";
		try {
			occupationYear = doc.select("DIV[class=p-del right]").get(0).select("SPAN").get(2).text();
		} catch(Exception e) {}
		System.out.println("从业年限occupationYear => " + occupationYear);
		res.setOccupationYear(occupationYear);
		
		String monthlyViewCount = "";
		try {
			monthlyViewCount = doc.select("DIV[class=p-del right]").get(0).select("SPAN").get(4).text();
		} catch(Exception e){}
		System.out.println("月总带看monthlyViewCount => " + monthlyViewCount);
		res.setMonthlyViewCount(monthlyViewCount);
		
		res.setCityDict(Constant.CITY_DICT);
		res.setDistrictDict(Constant.DISTRICT_DICT);
		res.setSubDistrictDict(Constant.CIRCLE_DICT);
		
		return res;
	}
}
