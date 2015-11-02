package com.homejjr.clawer.lianjia.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.lianjia.dict.Dictionary;
import com.homejjr.clawer.lianjia.domain.Resource;
import com.homejjr.clawer.lianjia.domain.WechatPost;
import com.homejjr.clawer.util.DateUtil;
import com.homejjr.clawer.util.RandomUtil;
import com.homejjr.clawer.util.StringUtil;

public class ETLService 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ETLService.class);
	
	public static void disableWechatPostList(Statement stmt,List<Resource> rawResourceList)
	{
		try
		{
			String sql = "UPDATE `wechat_post` SET `biz_state`='enable' WHERE `f_id` <> '' AND `f_id` IS NOT NULL AND `city_dict`='"+Constant.CITY_DICT+"' AND `district_dict`='"+Constant.DISTRICT_DICT+"' AND `sub_district_dict`='"+Constant.CIRCLE_DICT+"' AND `biz_state` <> 'enable' AND DATE(`updated_ts`) < '"+DateUtil.Date2String(Constant.REPORT_DATE, "yyyy-MM-dd")+"' ";
			if(!rawResourceList.isEmpty())
			{
				sql += " AND `f_id` NOT IN (";
				for(int i = 0; i < rawResourceList.size(); i++)
				{
					Resource res = rawResourceList.get(i);
					if(res == null || StringUtils.isEmpty(res.getResourceId()))
					{
						continue;
					}
					
					if(i >= 1)
					{
						sql += ",";
					}
					sql += "'"+res.getResourceId()+"'";
				}
				sql += ")";
			}
			LOGGER.info(sql);
			int rows = stmt.executeUpdate(sql);
			LOGGER.info("下架 {} rows",rows);
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
	}
	
	public static List<WechatPost> getNotExistsWechatPostList(List<WechatPost> rawWechatPostList, List<WechatPost> previousWechatPostList)
	{
		List<WechatPost> insertWechatPostList = new ArrayList<WechatPost>();
		
		Set<String> fidSet = new HashSet<String>();
		for(WechatPost wp : previousWechatPostList)
		{
			fidSet.add(wp.getfId());
		}
		for(WechatPost wp : rawWechatPostList)
		{
			if(!fidSet.contains(wp.getfId()))
			{
				insertWechatPostList.add(wp);
			}
		}
		
		return insertWechatPostList;
	}
	
	public static List<WechatPost> getExistsWechatPostList(List<WechatPost> rawWechatPostList, List<WechatPost> previousWechatPostList)
	{
		List<WechatPost> updateWechatPostList = new ArrayList<WechatPost>();
		
		Set<String> fidSet = new HashSet<String>();
		for(WechatPost wp : previousWechatPostList)
		{
			fidSet.add(wp.getfId());
		}
		for(WechatPost wp : rawWechatPostList)
		{
			if(fidSet.contains(wp.getfId()))
			{
				updateWechatPostList.add(wp);
			}
		}
		return updateWechatPostList;
	}
	
	public static List<WechatPost> getLianJiaWechatPostList(Statement stmt)
	{
		List<WechatPost> wechatPostList = new ArrayList<WechatPost>();
//		Statement stmt = null;
		ResultSet rs = null;
		try
		{
//			con = DBUtil.getConnection();
			String sql = "SELECT `id`,`f_id`,`user_id`,`post_detail_type`,`purpose_type`,`city_dict`,`district_dict`,`sub_district_dict`,`address`,`area`,`price`,`house_type`,`is_enabled`,`read_count`,`mobile_click_count`,`created_ts`,`updated_ts`,`commission_divide`,`performance_vest`,`performance_vest_new`,`anonymity`,`anonymity_nick_name`,`user_phone`,`phone_proxy_no`,`group_guid`,`is_speed_sell`,`remind_count`,`biz_state`,`garentee_fee`,`jump_claim`,`price_plan`,`house_village`,`house_orien`,`floor_current`,`floor_total` FROM `wechat_post` WHERE `f_id` <> '' AND `f_id` IS NOT NULL AND `district_dict`='"+Constant.DISTRICT_DICT+"' AND `sub_district_dict`='"+Constant.CIRCLE_DICT+"'";

//			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) 
			{
				WechatPost wp = new WechatPost();
				wp.setId(rs.getInt("id"));
				wp.setfId(rs.getString("f_id"));
				wp.setUserId(rs.getInt("user_id"));
				wp.setPostDetailType(rs.getString("post_detail_type"));
				wp.setPurposeType(rs.getString("purpose_type"));
				wp.setCityDict(rs.getString("city_dict"));
				wp.setDistrictDict(rs.getString("district_dict"));
				wp.setSubDistrictDict(rs.getString("sub_district_dict"));
				wp.setAddress(rs.getString("address"));
				wp.setArea(rs.getString("area"));
				wp.setPrice(rs.getString("price"));
				wp.setHouseType(rs.getString("house_type"));
				wp.setIsEnabled(rs.getString("is_enabled"));
				wp.setReadCount(rs.getInt("read_count"));
				wp.setMobileClickCount(rs.getInt("mobile_click_count"));
				wp.setCreatedTs(rs.getDate("created_ts"));
				wp.setUpdatedTs(rs.getDate("updated_ts"));
				wp.setCommissionDivide(rs.getString("commission_divide"));
				wp.setPerformanceVest(rs.getString("performance_vest"));
				wp.setPerformanceVestNew(rs.getString("performance_vest_new"));
				wp.setAnonymity(rs.getString("anonymity"));
				wp.setAnonymityNickName(rs.getString("anonymity_nick_name"));
				wp.setUserPhone(rs.getString("user_phone"));
				wp.setPhoneProxyNo(rs.getString("phone_proxy_no"));
				wp.setGroupGuid(rs.getString("group_guid"));
				wp.setIsSpeedSell(rs.getInt("is_speed_sell"));
				wp.setRemindCount(rs.getInt("remind_count"));
				wp.setBizState(rs.getString("biz_state"));
				wp.setGarenteeFee(rs.getDouble("garentee_fee"));
				wp.setJumpClaim(rs.getString("jump_claim"));
				wp.setPricePlan(rs.getString("price_plan"));
				wp.setHouseVillage(rs.getString("house_village"));
				wp.setHouseOrien(rs.getString("house_orien"));
				wp.setFloorCurrent(rs.getString("floor_current"));
				wp.setFloorTotal(rs.getString("floor_total"));
				wechatPostList.add(wp);
			}
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
		finally
		{
//			DBUtil.close(con, null, null);
		}
		return wechatPostList;
	}
	
	public static List<WechatPost> transferResourceData(List<Resource> resourceList)
	{
		Date now = new Date();
		List<WechatPost> wechatPostList = new ArrayList<WechatPost>();
		for(Resource resource : resourceList)
		{
			WechatPost wp = new WechatPost();
			wp.setfId(resource.getResourceId());
			wp.setUserId(Constant.DEFAULT_USER_ID);
			wp.setPostDetailType("SEARCH_OBJECTIVE_INFO_HOUSE");
			wp.setPurposeType("SEARCH_OBJECTIVE_SELL");
			wp.setCityDict(resource.getCityDict());
			wp.setDistrictDict(resource.getDistrictDict());
			wp.setSubDistrictDict(resource.getSubDistrictDict());
			wp.setDistrictDict(Constant.DISTRICT_DICT);
			wp.setSubDistrictDict(Constant.CIRCLE_DICT);
			
			//将链家的Title拼接上是否满五唯一存入业务表中的other字段中
			String other = resource.getTitle() + "" + resource.getTaxFee();
			if(!StringUtils.isEmpty(other))
			{
				other = other.replaceAll("&lt;", "");
				wp.setOther(other);
			}
			
			//将链家的小区存入业务表中的address 地址/小区字段中
			String address = resource.getNeigbour();
			
			if(!StringUtils.isEmpty(address)) 
			{
				wp.setAddress(address);
			}
			
			String area = resource.getSumArea();
			if(!StringUtils.isEmpty(area))
			{
				area = area.replaceAll("㎡", "");
				area = area.trim();
				
				//面积取整-取地板
				int iArea = StringUtil.getFloor(area);
				wp.setArea(String.valueOf(iArea));
			}
			
//			String price = resource.getUnitPrice();
//			if(!StringUtils.isEmpty(price))
//			{
//				price = price.replaceAll("元/平米", "");
//				price = price.trim();
//				wp.setPrice(price);
//			}
			
			//是否篡改价格
			String strSumPrice = resource.getSumPrice();
			if(Constant.IS_MODIFY_PRICE && StringUtils.isNumeric(strSumPrice)) 
			{ //篡改
				try
				{
					int price = Integer.parseInt(strSumPrice) + RandomUtil.getDetalOfPrice(wp.getfId());
					wp.setPrice(String.valueOf(price));
				} catch(Exception e) {
					LOGGER.error("error",e);
					wp.setPrice(strSumPrice);
				}
			}
			else
			{ //原价
				wp.setPrice(strSumPrice);				
			}
			
			
			wp.setHouseType(resource.getHouseType());
			
			wp.setIsEnabled("1");
			
			String strReadCount = resource.getViewCount();
			if(!StringUtils.isEmpty(strReadCount))
			{
				try 
				{
					wp.setReadCount(Integer.parseInt(strReadCount));
				}
				catch(Exception e)
				{
					wp.setReadCount(0);
				}
			}
			else
			{
				wp.setReadCount(0);
			}
			
			wp.setMobileClickCount(0);
			wp.setCreatedTs(now);
			wp.setUpdatedTs(now);
			wp.setCommissionDivide("5:5分成");
			wp.setPerformanceVest("蜜蜂社");
			wp.setPerformanceVestNew("蜜蜂社");
			wp.setAnonymity("1");
			wp.setAnonymityNickName(RandomUtil.getNextNickName());
			wp.setUserPhone(Constant.DEFAULT_PHONE_NO);
			
			wp.setIsSpeedSell(0);
			wp.setRemindCount(0);
			//wp.setBizState("enable"); // 不要显示则为enable
			wp.setBizState("saved"); //暂存状态
			
			wp.setGarenteeFee(0);
			wp.setJumpClaim("N");
			wp.setPricePlan(Dictionary.PRICE_PLAN_STATE.HAND_PRICE.name());
			wp.setHouseVillage(resource.getNeigbour());
			
			String houseOrien = resource.getDirection();
			
			if(!StringUtils.isEmpty(houseOrien))
			{
				houseOrien = houseOrien.replaceAll("-", "");
				houseOrien = houseOrien.trim();
				if(!StringUtils.isEmpty(houseOrien))
				{
					if(houseOrien.contains("南北")) { //南北通透
						wp.setHouseOrien(Dictionary.HOUSE_ORIEN.HOUSE_ORIENTATION_SOUTH_NORTH.name());
					} else if(houseOrien.contains("东南") 
						   || houseOrien.contains("东北") 
						   || houseOrien.contains("西北") 
						   || houseOrien.contains("东西") 
						   || houseOrien.contains("东")
						   || houseOrien.contains("南")
						   || houseOrien.contains("西")) { //朝南
						wp.setHouseOrien(Dictionary.HOUSE_ORIEN.HOUSE_ORIENTATION_SOUTH.name());
					} else if(houseOrien.contains("北")) { //朝北
						wp.setHouseOrien(Dictionary.HOUSE_ORIEN.HOUSE_ORIENTATION_NORTH.name());
					} else {
						wp.setHouseOrien(Dictionary.HOUSE_ORIEN.HOUSE_ORIENTATION_SOUTH.name());
					}
				}
			}
			
			String floor = resource.getFloor(); //中楼层(共14层),低楼层(共6层),高楼层(共7层)
			if(!StringUtils.isEmpty(floor))
			{
				if(floor.contains("中楼层")) {
					wp.setFloorCurrent(Dictionary.HOUSE_FLOOR.HOUSE_FLOOR_REQUIRE_MIDDLE.name());
				}
				else if(floor.contains("低楼层")) {
					wp.setFloorCurrent(Dictionary.HOUSE_FLOOR.HOUSE_FLOOR_REQUIRE_LOW.name());
				}
				else if(floor.contains("高楼层")) {
					wp.setFloorCurrent(Dictionary.HOUSE_FLOOR.HOUSE_FLOOR_REQUIRE_HIGHER.name());
				}
				wp.setFloorTotal(StringUtil.getNumber(floor));
			}
			wechatPostList.add(wp);
		}
		return wechatPostList;
	}
	
	public static List<Resource> getOnLineResourceList(Statement stmt)
	{
		List<Resource> resourceList = new ArrayList<Resource>();
		
		ResultSet rs = null;
		try
		{
			//con = DBUtil.getConnection();
			String sql = "SELECT * FROM `lianjia_resource` WHERE `city_dict`='"+Constant.CITY_DICT+"' AND `district_dict`='"+Constant.DISTRICT_DICT+"' AND `sub_district_dict`='"+Constant.CIRCLE_DICT+"' AND DATE(`updated_ts`)='"+DateUtil.Date2String(Constant.REPORT_DATE, "yyyy-MM-dd")+"'";
			LOGGER.debug("SQL=>{}",sql);
//			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				Resource resource = new Resource();
				resource.setCityDict(rs.getString("city_dict"));
				resource.setDistrictDict(rs.getString("district_dict"));
				resource.setSubDistrictDict(rs.getString("sub_district_dict"));
				resource.setResourceId(rs.getString("resource_id"));
				resource.setTitle(rs.getString("title"));
				resource.setTaxFee(rs.getString("tax_fee"));
				resource.setUnique(rs.getString("unique_desc"));
				resource.setSumPrice(rs.getString("sum_price"));
				resource.setSumArea(rs.getString("sum_area"));
				resource.setUnitPrice(rs.getString("unit_price"));
				resource.setFirstPay(rs.getString("first_pay"));
				resource.setMonthlyProvide(rs.getString("monthly_provide"));
				resource.setHouseType(rs.getString("house_type"));
				resource.setDirection(rs.getString("direction"));
				resource.setFloor(rs.getString("floor"));
				resource.setNeigbour(rs.getString("neigbour"));
				resource.setTelphone(rs.getString("telphone"));
				resource.setTxnCount(rs.getString("txn_count"));
				resource.setAgentCommentCount(rs.getString("agent_comment_count"));
				resource.setViewCount(rs.getString("view_count"));
				resource.setAgentName(rs.getString("agent_name"));
				resource.setAgentType(rs.getString("agent_type"));
				resource.setOccupationYear(rs.getString("occupation_year"));
				resource.setMonthlyViewCount(rs.getString("monthly_view_count"));
				resourceList.add(resource);
			}
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
		return resourceList;
	}
	
	public static void  saveOrUpdateWechatPostList(Statement stmt, List<WechatPost> wechatPostList, boolean isSave)
	{
//		Statement stmt = null;
		try
		{
//			con = DBUtil.getConnection();
			
//			stmt = con.createStatement();
			
			
			if(isSave) 
			{
				int insertedRows = 0;
				for(WechatPost wp : wechatPostList)
				{
					if(Constant.IS_BIND_PHONE_NO) {
						if(!PhoneProxyService.bind(wp)){
							LOGGER.info(" 400 binding fail => {} ", JSON.toJSONString(wp));
							Thread.sleep(Constant.SLEEP_TIME);
							continue;
						}
						LOGGER.info(" after 400 binding success => {} ", JSON.toJSONString(wp));
						LOGGER.info(" sleep {} ms",Constant.SLEEP_TIME);
						Thread.sleep(Constant.SLEEP_TIME);
					}
					wp.setCreatedTs(DateUtil.nowTime(Constant.REPORT_DATE));
					wp.setUpdatedTs(DateUtil.nowTime(Constant.REPORT_DATE));
					insertedRows += stmt.executeUpdate(wp.ToInsertSQL());
					LOGGER.debug("insert 1 row");
				}
				LOGGER.info("inserted {} rows",insertedRows);
			}
			else
			{
				int updatedRows = 0;
				for(WechatPost wp : wechatPostList)
				{
					if(StringUtils.isEmpty(wp.getPhoneProxyNo())) {
						LOGGER.info("proxy no is empty, skip update");
					}
					wp.setUpdatedTs(DateUtil.nowTime(Constant.REPORT_DATE));
					updatedRows += stmt.executeUpdate(wp.ToUpdateSQL());
					LOGGER.debug("update 1 row");
				}
				LOGGER.info("updated {} rows",updatedRows);
			}
			
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
		finally
		{
//			DBUtil.close(con,null,null);
		}
	}
}
