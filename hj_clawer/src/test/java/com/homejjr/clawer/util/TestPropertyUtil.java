package com.homejjr.clawer.util;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.homejjr.clawer.lianjia.constant.Constant;

public class TestPropertyUtil {

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
		
//		String[] array = PropertyUtil.getStrArray("config.ini", "NICK_NAME_LIST");
//		for(int i = 1; i <= 100; i++)
//		{
//			String nickName = RandomUtil.getNextNickName(array);
//			System.out.println(nickName);
//		}
	}

}
