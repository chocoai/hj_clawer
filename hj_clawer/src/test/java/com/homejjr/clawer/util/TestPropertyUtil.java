package com.homejjr.clawer.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.homejjr.clawer.constant.Constant;

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
		String value = PropertyUtil.getProperty("config.ini", "hello");
		System.out.println(value);
	}

}
