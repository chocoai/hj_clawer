package com.homejjr.clawer.lianjia.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.lianjia.domain.Resource;
import com.homejjr.clawer.util.DBUtil;
import com.homejjr.clawer.util.DateUtil;

public class DBService 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DBService.class);
	
	public static List<Resource> getExpiryResource()
	{
		String district = Constant.DISTRICT_DICT;
		String circle = Constant.CIRCLE_DICT;
		String strReportDate = DateUtil.Date2String(Constant.REPORT_DATE, "yyyy-MM-dd");
		String sql = "SELECT * FROM `lianjia_resource` WHERE `district_dict`='"+district+"' AND `sub_district_dict` = '"+circle+"' AND DATE(`updated_ts`) < '"+strReportDate+"' AND DATE(`created_ts`) < '"+strReportDate+"'";
		return getResource(sql);
	}
	
	public static List<Resource> getNewResource()
	{
		String district = Constant.DISTRICT_DICT;
		String circle = Constant.CIRCLE_DICT;
		String strReportDate = DateUtil.Date2String(Constant.REPORT_DATE, "yyyy-MM-dd");
		String sql = "SELECT * FROM `lianjia_resource` WHERE `district_dict`='"+district+"' AND `sub_district_dict` = '"+circle+"' AND DATE(`updated_ts`) = '"+strReportDate+"' AND DATE(`created_ts`) = '"+strReportDate+"'";
		return getResource(sql);
	}
	
	public static List<Resource> getTotalResource()
	{
		String district = Constant.DISTRICT_DICT;
		String circle = Constant.CIRCLE_DICT;
		String sql = "SELECT * FROM `lianjia_resource` WHERE `district_dict`='"+district+"' AND `sub_district_dict` = '"+circle+"'";
		return getResource(sql);
	}
	
	public static List<Resource> getResource(String sql)
	{
		List<Resource> resourceList = new ArrayList<Resource>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try
		{
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			Resource res = null;
			while(rs.next())
			{
				res = new Resource();
				res.setId(rs.getString("id"));
				res.setResourceId(rs.getString("resource_id"));
				res.setCityDict(rs.getString("city_dict"));
				res.setDistrictDict(rs.getString("district_dict"));
				res.setSubDistrictDict(rs.getString("sub_district_dict"));
				res.setTitle(rs.getString("title"));
				res.setTaxFee(rs.getString("tax_fee"));
				res.setUnique(rs.getString("unique_desc"));
				res.setSumPrice(rs.getString("sum_price"));
				res.setSumArea(rs.getString("sum_area"));
				res.setUnitPrice(rs.getString("unit_price"));
				res.setFirstPay(rs.getString("first_pay"));
				res.setMonthlyProvide(rs.getString("monthly_provide"));
				res.setHouseType(rs.getString("house_type"));
				res.setDirection(rs.getString("direction"));
				res.setFloor(rs.getString("floor"));
				res.setNeigbour(rs.getString("neigbour"));
				res.setTelphone(rs.getString("telphone"));
				res.setTxnCount(rs.getString("txn_count"));
				res.setAgentCommentCount(rs.getString("agent_comment_count"));
				res.setViewCount(rs.getString("view_count"));
				res.setAgentName(rs.getString("agent_name"));
				res.setAgentType(rs.getString("agent_type"));
				res.setOccupationYear(rs.getString("occupation_year"));
				res.setMonthlyViewCount(rs.getString("monthly_view_count"));
				res.setCreatedTs(DateUtil.Date2String(rs.getTimestamp("created_ts"),"yyyy-MM-dd HH:mm:ss"));
				res.setUpdatedTs(DateUtil.Date2String(rs.getTimestamp("updated_ts"),"yyyy-MM-dd HH:mm:ss"));
				resourceList.add(res);
			}
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
		return resourceList;
	}
	
	
	public static void mergeData(List<Resource> resourceList)
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			con = DBUtil.getConnection();
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT `resource_id` FROM `lianjia_resource`");
			
			Set<String> resourceIdSet = new HashSet<String>();
			
			while(rs.next())
			{
				resourceIdSet.add(rs.getString("resource_id"));
			}
			
			int updatedRows=0,insertedRows=0;
			
			for(Resource resource : resourceList) 
			{
				if(resourceIdSet.contains(resource.getResourceId())) 
				{
					LOGGER.debug(resource.ToUpdateSQL());
					updatedRows += stmt.executeUpdate(resource.ToUpdateSQL());
				}
				else
				{
					LOGGER.debug(resource.ToInsertSQL());
					insertedRows += stmt.executeUpdate(resource.ToInsertSQL());
				}
			}
			LOGGER.debug("totolly {} datas, updated {} rows, inserted {} rows ", new Object[]{resourceList.size(),updatedRows,insertedRows});
		}
		catch(Exception e)
		{
			LOGGER.error("error",e);
		}
		finally
		{
			DBUtil.close(con, null, null);
		}
	}
}
