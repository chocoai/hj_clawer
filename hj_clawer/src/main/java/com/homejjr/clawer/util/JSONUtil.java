package com.homejjr.clawer.util;

import com.alibaba.fastjson.JSON;
import com.homejjr.clawer.lianjia.domain.Resource;

public class JSONUtil {
	
	public static String Resource2JsonString(Resource res) {
		return JSON.toJSONString(res);
	}
	
	public static Resource JsonStr2Resource(String strJson) {
		return JSON.parseObject(strJson, Resource.class);
	}
}
