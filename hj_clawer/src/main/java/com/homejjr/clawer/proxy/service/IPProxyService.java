package com.homejjr.clawer.proxy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.proxy.domain.IPProxy;
import com.homejjr.clawer.util.HttpUtil;
import com.homejjr.clawer.util.PropertyUtil;

public class IPProxyService {
	
	private static String URL = PropertyUtil.getProperty("ip_proxy_config.ini", "PROXY_URL");
	
	private static int IP_PROXY_COUNT = 50;
	
	private static int PROXY_ID_INDEX = PropertyUtil.getInt("ip_proxy_config.ini", "PROXY_ID_INDEX");
	
	public static List<IPProxy> getIPProxyList() {
		List<IPProxy> ipList = new ArrayList<IPProxy>();
		for(int i = 0; i < IP_PROXY_COUNT; i++) {
			String address = PropertyUtil.getProperty("ip_proxy_config.ini", "PROXY_IP_"+i);
			String[] array = address.split(":");
			IPProxy ip = new IPProxy();
			ip.setIp(array[0]);
			ip.setPort(array[1]);
			ipList.add(ip);
		}
		return ipList;
	}
	
	public static IPProxy getUsefulIPProxy() {
		List<IPProxy> ipList = IPProxyService.getIPProxyList();
		IPProxy returnIPProxy = null;
		while(true) {
			if(PROXY_ID_INDEX >= IP_PROXY_COUNT) {
				IPProxyService.getIPProxyAddress();
				PROXY_ID_INDEX = 0;
				//break;
			}
			IPProxy proxy = ipList.get(PROXY_ID_INDEX);
			PropertyUtil.setProperty("ip_proxy_config.ini", "PROXY_ID_INDEX", PROXY_ID_INDEX);
			PROXY_ID_INDEX++;
			String strResp = null;
			try {
				strResp = HttpUtil.HttpGet(Constant.INDEX_PAGE_URL, new HttpHost(proxy.getIp(),Integer.parseInt(proxy.getPort())));
			}
			catch(Exception e) {
			}
			
			if(StringUtils.isEmpty(strResp)) {
				continue;
			}
			returnIPProxy = proxy;
			break;
		}
		return returnIPProxy;
	}
	
	public static void getIPProxyAddress() {
		//{"msg": "", "code": 0, "data": {"count": 10, "proxy_list": ["39.169.3.10:8123", "41.188.149.42:8080", "187.60.38.136:80", "121.31.101.26:8123", "203.195.162.96:8080", "39.182.164.50:8123", "111.12.117.78:8080", "131.221.53.50:8080", "39.188.148.245:8123", "122.96.59.102:82"]}}
		String strJsonResp = HttpUtil.HttpGet(URL, false);
		JSONObject jsonObj = JSON.parseObject(strJsonResp);
		int code = jsonObj.getIntValue("code");
		JSONObject data = jsonObj.getJSONObject("data");
		if(code == 0) {
			int count = data.getIntValue("count");
			JSONArray proxyList = data.getJSONArray("proxy_list");
			for(int i = 0; i < count; i++) {
				String address = proxyList.getString(i);
				PropertyUtil.setProperty("ip_proxy_config.ini", "PROXY_IP_"+i, address);
			}
		}
	}
	
	public static void main(String[] args) {
		
		Constant.getInitParameters();
//		System.out.println(Constant.INDEX_PAGE_URL);
//		
//		String resp = HttpUtil.HttpGet(Constant.INDEX_PAGE_URL, true);
//		
//		System.out.println(resp);
		getIPProxyAddress();
//		for(IPProxy proxy : list)
//		System.out.println(proxy.getIp() + ":" + proxy.getPort());
	}
	
}
