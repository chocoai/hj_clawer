package com.homejjr.clawer.util;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.homejjr.clawer.lianjia.constant.Constant;

public class RandomUtil 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RandomUtil.class);
	
	public static String getNextNickName(String[] array) 
	{
		Random random = new Random();
		int randomIndex = random.nextInt(array.length);
		return array[randomIndex];
	}
	
	public static String getNextNickName()
	{
		Random random = new Random();
		int randomIndex = random.nextInt(Constant.NICK_NAME_LIST.length);
		return Constant.NICK_NAME_LIST[randomIndex];
	}
	
	public static int getRandomInt(int min, int max) 
	{
		int len = max-min+1;
		int[] seed = new int[len];
		for(int i = 0; i < len;i++)
		{
			seed[i] = i+1;
		}
		Random random = new Random();
		int pos = random.nextInt(len);
		return seed[pos];
	}
	
	public static String getFixNickName(String fId)
	{
		int mod = 0;
		String[] nickNameArray = Constant.NICK_NAME_LIST;
		int len = nickNameArray.length;
		try
		{
			String strFid = fId.replaceAll("SH", "");
			if(StringUtils.isNumeric(strFid))
			{
				mod = Integer.parseInt(strFid) % len;
			}
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
		return nickNameArray[mod];
	}
	
	public static int getDetalOfPrice(String fId)
	{
		int mod = 1;
		try
		{
			String strFid = fId.replaceAll("SH", "");
			if(StringUtils.isNumeric(strFid))
			{
				mod = Integer.parseInt(strFid) % 3 + 1;
			}
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
		return mod;
	}
	
	public static void main(String[] args) {
		Constant.getInitParameters();
		String fId = "SH0000683732";
		String nickName = RandomUtil.getFixNickName(fId);
		System.out.println(nickName);
	}
	
}
