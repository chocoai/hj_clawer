package com.homejjr.clawer.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil 
{
	
	public static String getNumber(String input) 
	{
		Pattern p = Pattern.compile("[^0-9]");
		Matcher m = p.matcher(input);
		return m.replaceAll("").trim().toString();
	}
	
	public static int getFloor(String input)
	{
		Double dInput = Double.parseDouble(input);
		Double ret = Math.floor(dInput);
		return ret.intValue();
	}
	
	public static void main(String[] args) {
		String str = "中楼层(共14层)";
		System.out.println(getNumber(str));
		
		int ret = StringUtil.getFloor("80.97");
		System.out.println(ret);
	}
}
