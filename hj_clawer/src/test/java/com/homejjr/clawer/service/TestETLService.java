package com.homejjr.clawer.service;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.lianjia.domain.WechatPost;

public class TestETLService {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Constant.getInitParameters();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
//		List<Resource> resourceList = ETLService.getOnLineResourceList();
//		List<WechatPost> wpl = ETLService.transferResourceData(resourceList);
//		ETLService.wechatPostDataSave2Json(wpl);
//		List<WechatPost> wpl = ETLService.getLianJiaWechatPostList();
//		for(WechatPost wp : wpl) {
//			System.out.println(JSON.toJSONString(wp));
//		}
//		
//		ETLService.wechatPostDataSave2Json(wpl);
//		
//		for(WechatPost wp : wpl) {
//			System.out.println(JSON.toJSONString(wp));
//		}
		
		
	}

}
