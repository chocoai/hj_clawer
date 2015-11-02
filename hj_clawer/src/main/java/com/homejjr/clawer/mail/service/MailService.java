package com.homejjr.clawer.mail.service;

import java.io.File;
import java.util.List;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.lianjia.domain.Resource;
import com.homejjr.clawer.util.DateUtil;
import com.homejjr.clawer.util.PropertyUtil;

public class MailService 
{
	private static Logger LOGGER = LoggerFactory.getLogger(MailService.class);
	
	private static String HOST_NAME = PropertyUtil.getProperty("mail.properties", "MAIL_HOST_NAME", "smtp.163.com");
	private static String PASSWORD = PropertyUtil.getProperty("mail.properties", "MAIL_PASSWORD");
	private static String FROM = PropertyUtil.getProperty("mail.properties", "MAIL_FROM");
	private static List<?> TO_LIST = PropertyUtil.getList("mail.properties", "MAIL_TO");
	
	
	static
	{
		System.setProperty("mail.mime.charset","utf-8");
	}
	
	public static void sendMail(String subject, String message, List<?> toList, String filePath) throws Exception
	{
		HtmlEmail email = new HtmlEmail();
		email.setDebug(true);
		email.setHostName(HOST_NAME);
		email.setAuthentication(FROM, PASSWORD);
		email.setFrom(FROM);
		for(Object to : toList)
		{
			email.addTo((String)to);
		}
		
		File f = new File(filePath);
		if(f.exists())
		{
			EmailAttachment attr = new EmailAttachment();  
			attr.setPath(filePath);//附件路径
			attr.setDisposition(EmailAttachment.ATTACHMENT);
			//设置附件的中文名字，不设置会为乱码
			String fileName = filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
			attr.setName(MimeUtility.encodeText(new String(fileName.getBytes(),"GB2312"),"GB2312","B"));
			email.attach(attr);
		}
		
		email.setCharset("UTF-8");
		email.setSubject(subject);
		email.setHtmlMsg(message);
		email.send();
	}
	
	public static void sendMail(String subject, String message, String from, List<?> toList) throws Exception 
	{
		HtmlEmail email = new HtmlEmail();
		email.setDebug(true);
		email.setHostName(HOST_NAME);
		email.setAuthentication(from, PASSWORD);
		email.setFrom(from);
		for(Object to : toList)
		{
			email.addTo((String)to);
		}
		email.setCharset("UTF-8");
		email.setSubject(subject);
		email.setHtmlMsg(message);
		email.send();
	}
	
	public static void sendMail(String subject, String content)
	{
		try
		{
			MailService.sendMail(subject, content, TO_LIST, Constant.NEW_RESOURCE_PATH);
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
	}
	
	
	public static void sendAttrEmail(List<Resource> newResourceList, List<Resource> expiryResourceList, List<Resource> totalResourceList)
	{
		
		String subject = DateUtil.Date2String(Constant.REPORT_DATE, "yyyy-MM-dd") + " " + Constant.DISTRICT_DICT + " " + Constant.CIRCLE + " 房源信息状况";
		
		StringBuilder bodyBuf = new StringBuilder();
		
		bodyBuf.append("Hi @All:<br/>&nbsp;&nbsp;&nbsp;&nbsp;");
		bodyBuf.append(Constant.CITY_DICT).append("_").append(Constant.CIRCLE_DICT).append(" 新增房源数量: ").append(newResourceList.size()).append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;");
		bodyBuf.append("详细信息参考附件,或").append(Constant.NEW_RESOURCE_PATH).append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;");
		
		bodyBuf.append(Constant.CITY_DICT).append("_").append(Constant.CIRCLE_DICT).append(" 过期房源数量: ").append(expiryResourceList.size()).append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;");
		bodyBuf.append("房源ID如下：");
		for(Resource expiryResource : expiryResourceList) {
			bodyBuf.append(expiryResource.getResourceId()).append(" ");
		}
		bodyBuf.append("<br/>&nbsp;&nbsp;&nbsp;&nbsp;").append("总共房源数量: ").append(totalResourceList.size());
		String body = bodyBuf.toString();
		
		MailService.sendMail(subject, body);
		
	}
	
}
