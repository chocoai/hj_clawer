package com.homejjr.clawer.lianjia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.homejjr.clawer.lianjia.domain.WechatPost;
import com.homejjr.clawer.util.PropertyUtil;
import com.homejjr.clawer.util.REST_HttpUtil;

public class PhoneProxyService 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PhoneProxyService.class);
	
	public static boolean bind(WechatPost wechatPost)
	{
		try
		{
			String phoneNo = wechatPost.getUserPhone();
			String strPostUrl = PropertyUtil.getProperty("config.ini", "PROXY_PHONE_URL");
			String strRequest = "{\"groupGuid\":\"\",\"newMobile\":\""+phoneNo+"\",\"workGroupName\":\"\"}";
			LOGGER.debug("strRequest=> {}",strRequest);
			String strResponse = REST_HttpUtil.PostMsg(strPostUrl, strRequest);
			LOGGER.debug("strResponse=> {}",strResponse);
			
			JSONObject jsonObj = JSON.parseObject(strResponse);
			//{"result_code":"0","result_data":[],"result_desc":"21BD13F5028E65C6E050A8C0E701DEBD,4008106290-9250,14112153"}
			
			String resultCode = jsonObj.getString("result_code");
			if("0".equals(resultCode))
			{
				String resultDesc = jsonObj.getString("result_desc");
				String[] result = resultDesc.split(",");
				String groupGuid = result[0];
				String phoneProxyNo = result[1];
				phoneProxyNo = phoneProxyNo.replaceAll("-", ",");
				wechatPost.setGroupGuid(groupGuid);
				wechatPost.setPhoneProxyNo(phoneProxyNo);
				return true;
			}
			
		}
		catch(Exception e) {
			LOGGER.error("error",e);
		}
		return false;
	}
}
