package com.homejjr.clawer.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.proxy.domain.IPProxy;
import com.homejjr.clawer.proxy.service.IPProxyService;

public class HttpUtil 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
	
	public static String HttpGet(String url,HttpHost host)  
	{
    	HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    	
    	if(host!=null)
    	{    	
    		httpClientBuilder.setProxy(host);
    	}
    	//new HttpHost("122.226.142.52",3128)
    	CloseableHttpClient httpClient = httpClientBuilder.build();
    	try
    	{
	        HttpGet httpGet = new HttpGet(url);
	        LOGGER.debug(httpGet.getRequestLine().toString());
//	        System.out.println(httpGet.getRequestLine());
	        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if(status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity,Constant.CHARSET) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
	        };
	        String responseBody = httpClient.execute(httpGet, responseHandler);
	        return responseBody;
    	}
    	catch(Exception e) 
    	{
    		LOGGER.error("error",e);
    	}
    	finally 
    	{
    		try { httpClient.close(); } catch (IOException e) { }
    	}
    	return "";
	}
	
	public static String HttpGet(String url) throws Exception
	{
		return HttpGet(url,Constant.IS_IP_ADDRESS_PROXY);
	}
	
	public static String HttpGet(String url, boolean useProxy) 
	{
		if(useProxy) 
		{
			IPProxy proxy = IPProxyService.getUsefulIPProxy();
			return HttpGet(url,new HttpHost(proxy.getIp(),Integer.parseInt(proxy.getPort())));
		}
		
		return HttpGet(url,null);
	}
	
	public static void main(String[] args) throws Exception {
		String str = HttpUtil.HttpGet("http://sh.lianjia.com/ershoufang/SH0000785313.html",null);
		System.out.println(str);
	}
}
