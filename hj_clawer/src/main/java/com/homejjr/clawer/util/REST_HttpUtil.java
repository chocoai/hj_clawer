package com.homejjr.clawer.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class REST_HttpUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(REST_HttpUtil.class);
	private static final String HTTP_CONNECTION_TIMEOUT = "3000";
	private static final String HTTP_READ_TIMEOUT = "3000";
	private static final String HTTP_METHOD_GET = "GET";
	private static final String HTTP_METHOD_POST = "POST";
	
	private static String DoProcess(String strUrl, String strContent, String httpMethod) throws Exception {
        URL postUrl = new URL(strUrl);
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        System.setProperty("sun.net.client.defaultConnectTimeout", HTTP_CONNECTION_TIMEOUT);  
        System.setProperty("sun.net.client.defaultReadTimeout", HTTP_READ_TIMEOUT);
        connection.setConnectTimeout(Integer.parseInt(HTTP_CONNECTION_TIMEOUT));
        connection.setReadTimeout(Integer.parseInt(HTTP_READ_TIMEOUT));
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod(httpMethod);
        connection.setUseCaches(false);
        
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
        
        connection.connect();
        
        if(strContent != null && !"".equals(strContent)) {
	        OutputStream out = connection.getOutputStream();
	        out.write(strContent.getBytes("utf-8"));
	        out.close();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
        String line="";
        StringBuilder strBuf=new StringBuilder();
        LOGGER.info("{} URL=>{}", new Object[]{httpMethod,strUrl});
        while ((line = reader.readLine()) != null){
        	strBuf.append(line);
        }
        reader.close();
        LOGGER.debug("responseCode => {} | responseMessage => {}", new Object[]{connection.getResponseCode(),connection.getResponseMessage()});
        connection.disconnect();
        return strBuf.toString();
    }
	
	public static String PostMsg(String strPostUrl, String strContent) throws Exception
	{
		return DoProcess(strPostUrl, strContent, HTTP_METHOD_POST);
	}
	
	public static String GetMsg(String strGetUrl) throws Exception
	{
		return DoProcess(strGetUrl, null, HTTP_METHOD_GET);
    }
	
}
