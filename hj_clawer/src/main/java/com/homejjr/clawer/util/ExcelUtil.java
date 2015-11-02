package com.homejjr.clawer.util;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.homejjr.clawer.lianjia.domain.Resource;
import com.homejjr.clawer.wuba.domain.Agent;

public class ExcelUtil 
{
	
	public static void exportResource(List<Resource> resourceList, String outputFilePath) throws Exception
	{
		Workbook wb = new HSSFWorkbook();
		
		Sheet sh = wb.createSheet();
		
		String[] title = {"id","resource_id","city_dict","district_dict","sub_district_dict","title","tax_fee","unique_desc","sum_price","sum_area","unit_price","first_pay","monthly_provide","house_type","direction","floor","neigbour","telphone","txn_count","agent_comment_count","view_count","agent_name","agent_type","occupation_year","monthly_view_count","created_ts","updated_ts"};
		
		//生成表头
		Row row = sh.createRow(0);
		Cell cell = null;
		for(int i = 0; i < title.length; i++)
		{
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		
		int i = 1;
		for(Resource resource : resourceList)
		{
			row = sh.createRow(i);
			for(int j = 0; j < title.length; j++)
			{
				cell = row.createCell(j);
				//
				switch(j)
				{
				case 0://id
					cell.setCellValue(resource.getId());
					break;
				case 1://resource_id
					cell.setCellValue(resource.getResourceId());
					break;
				case 2://city_dict
					cell.setCellValue(resource.getCityDict());
					break;
				case 3://district_dict
					cell.setCellValue(resource.getDistrictDict());
					break;
				case 4://sub_district_dict
					cell.setCellValue(resource.getSubDistrictDict());
					break;
				case 5: //title
					cell.setCellValue(resource.getTitle());
					break;
				case 6: //tax_fee
					cell.setCellValue(resource.getTaxFee());
					break;
				case 7: //unique_desc
					cell.setCellValue(resource.getUnique());
					break;
				case 8: //sum_price
					cell.setCellValue(resource.getSumPrice());
					break;
				case 9: //sum_area
					cell.setCellValue(resource.getSumArea());
					break;
				case 10://unit_price
					cell.setCellValue(resource.getUnitPrice());
					break;
				case 11://first_pay
					cell.setCellValue(resource.getFirstPay());
					break;
				case 12://monthly_provide
					cell.setCellValue(resource.getMonthlyProvide());
					break;
				case 13://house_type
					cell.setCellValue(resource.getHouseType());
					break;
				case 14://direction
					cell.setCellValue(resource.getDirection());
					break;
				case 15://floor
					cell.setCellValue(resource.getFloor());
					break;
				case 16://neigbour
					cell.setCellValue(resource.getNeigbour());
					break;
				case 17://telphone	
					cell.setCellValue(resource.getTelphone());
					break;
				case 18://txn_count
					cell.setCellValue(resource.getTxnCount());
					break;
				case 19://agent_comment_count
					cell.setCellValue(resource.getAgentCommentCount());
					break;
				case 20://view_count
					cell.setCellValue(resource.getViewCount());
					break;
				case 21://agent_name
					cell.setCellValue(resource.getAgentName());
					break;
				case 22://agent_type
					cell.setCellValue(resource.getAgentType());
					break;
				case 23://occupation_year
					cell.setCellValue(resource.getOccupationYear());
					break;
				case 24://monthly_view_count
					cell.setCellValue(resource.getMonthlyViewCount());
					break;
				case 25://created_ts
					cell.setCellValue(resource.getCreatedTs());
					break;
				case 26://updated_ts
					cell.setCellValue(resource.getUpdatedTs());
				}
			}
			i++;
		}
		
		FileOutputStream fileOut = new FileOutputStream(outputFilePath);
	    wb.write(fileOut);
	    fileOut.close();
	    wb.close();
	}
	
	
	public static void export(List<Agent> agentList, String outputFilePath) throws Exception
	{
		Workbook wb = new HSSFWorkbook();
		Sheet sh = wb.createSheet();
		
		Row row1 = sh.createRow(0);
		Cell cell1 = row1.createCell(0);
		cell1.setCellValue("AgentName");
		
		Cell cell2 = row1.createCell(1);
		cell2.setCellValue("TelPhone");
		
		Cell cell3 = row1.createCell(2);
		cell3.setCellValue("Company");
		
		Row row = null;
		for(int i = 0; i < agentList.size(); i++)
		{
			Agent agent = agentList.get(i);
			row = sh.createRow(i+1);
			Cell cell = row.createCell(0);
			cell.setCellValue(agent.getName());
			
			cell = row.createCell(1);
			cell.setCellValue(agent.getPhone());
			
			cell = row.createCell(2);
			cell.setCellValue(agent.getCompany());
		}
		
		FileOutputStream fileOut = new FileOutputStream(outputFilePath);
	    wb.write(fileOut);
	    fileOut.close();
	    wb.close();
	}
	
}
