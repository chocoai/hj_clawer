package com.homejjr.clawer.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.homejjr.clawer.domain.Resource;
import com.homejjr.clawer.util.DBUtil;

public class DBService 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DBService.class);
	
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
					updatedRows += stmt.executeUpdate(resource.ToUpdateSQL());
				}
				else
				{
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
			DBUtil.close(con, stmt, rs);
		}
	}
}
