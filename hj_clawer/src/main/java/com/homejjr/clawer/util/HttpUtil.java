package com.homejjr.clawer.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil 
{
	
	public static String HttpGet(String url) 
	{
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	
    	try
    	{
	        HttpGet httpGet = new HttpGet(url);
	        
	        System.out.println(httpGet.getRequestLine());
	        
	        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
	
				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if(status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity,"utf-8") : null;
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
    		e.printStackTrace();
    	}
    	finally 
    	{
    		try { httpClient.close(); } catch (IOException e) { }
    	}
        return null;
	}
	
}
