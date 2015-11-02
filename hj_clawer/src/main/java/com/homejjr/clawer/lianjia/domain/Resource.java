package com.homejjr.clawer.lianjia.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Resource {
	
	private String id;
	private String resourceId;
	private String cityDict;
	private String districtDict;
	private String subDistrictDict;
	private String title;
	private String taxFee;
	private String unique;
	private String sumPrice;
	private String sumArea;
	private String unitPrice;
	private String firstPay;
	private String monthlyProvide;
	private String houseType;
	private String direction;
	private String floor;
	private String neigbour;
	private String telphone;
	private String txnCount;
	private String agentCommentCount;
	private String viewCount;
	private String agentName;
	private String agentType;
	private String occupationYear;
	private String monthlyViewCount;
	
	private String createdTs;
	private String updatedTs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getCityDict() {
		return cityDict;
	}
	public void setCityDict(String cityDict) {
		this.cityDict = cityDict;
	}
	public String getDistrictDict() {
		return districtDict;
	}
	public void setDistrictDict(String districtDict) {
		this.districtDict = districtDict;
	}
	public String getSubDistrictDict() {
		return subDistrictDict;
	}
	public void setSubDistrictDict(String subDistrictDict) {
		this.subDistrictDict = subDistrictDict;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTaxFee() {
		return taxFee;
	}
	public void setTaxFee(String taxFee) {
		this.taxFee = taxFee;
	}
	public String getUnique() {
		return unique;
	}
	public void setUnique(String unique) {
		this.unique = unique;
	}
	public String getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getSumArea() {
		return sumArea;
	}
	public void setSumArea(String sumArea) {
		this.sumArea = sumArea;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getFirstPay() {
		return firstPay;
	}
	public void setFirstPay(String firstPay) {
		this.firstPay = firstPay;
	}
	public String getMonthlyProvide() {
		return monthlyProvide;
	}
	public void setMonthlyProvide(String monthlyProvide) {
		this.monthlyProvide = monthlyProvide;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getNeigbour() {
		return neigbour;
	}
	public void setNeigbour(String neigbour) {
		this.neigbour = neigbour;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getTxnCount() {
		return txnCount;
	}
	public void setTxnCount(String txnCount) {
		this.txnCount = txnCount;
	}
	public String getAgentCommentCount() {
		return agentCommentCount;
	}
	public void setAgentCommentCount(String agentCommentCount) {
		this.agentCommentCount = agentCommentCount;
	}
	public String getViewCount() {
		return viewCount;
	}
	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentType() {
		return agentType;
	}
	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	public String getOccupationYear() {
		return occupationYear;
	}
	public void setOccupationYear(String occupationYear) {
		this.occupationYear = occupationYear;
	}
	public String getMonthlyViewCount() {
		return monthlyViewCount;
	}
	public void setMonthlyViewCount(String monthlyViewCount) {
		this.monthlyViewCount = monthlyViewCount;
	}
	
	public String getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(String createdTs) {
		this.createdTs = createdTs;
	}
	public String getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(String updatedTs) {
		this.updatedTs = updatedTs;
	}
	public String ToInsertSQL() {
		StringBuilder buf = new StringBuilder();
		buf.append("INSERT INTO `lianjia_resource`(`resource_id`,`city_dict`,`district_dict`,`sub_district_dict`,`title`,`tax_fee`,`unique_desc`,`sum_price`,`sum_area`,`unit_price`,`first_pay`,`monthly_provide`,`house_type`,`direction`,`floor`,`neigbour`,`telphone`,`txn_count`,`agent_comment_count`,`view_count`,`agent_name`,`agent_type`,`occupation_year`,`monthly_view_count`,`created_ts`,`updated_ts`) VALUES ");
		buf.append("('").append(this.resourceId).append("','").append(this.cityDict).append("','").append(this.districtDict).append("','").append(this.subDistrictDict).append("','").append(this.title).append("','").append(this.taxFee).append("','").append(this.unique).append("','");
		buf.append(this.sumPrice).append("','").append(this.sumArea).append("','").append(this.unitPrice).append("','").append(this.firstPay).append("','").append(this.monthlyProvide).append("','").append(this.houseType).append("','").append(this.direction).append("','").append(this.floor).append("','").append(this.neigbour).append("','").append(this.telphone).append("','");
		buf.append(this.txnCount).append("','").append(this.agentCommentCount).append("','").append(this.viewCount).append("','");
		buf.append(this.agentName).append("','").append(this.agentType).append("','").append(this.occupationYear).append("','").append(this.monthlyViewCount).append("',now(),now())");
		return buf.toString();
	}
	
	public String ToUpdateSQL() {
		StringBuilder buf = new StringBuilder();
		buf.append("UPDATE `lianjia_resource` SET ");
//		buf.append("`title`='").append(this.title).append("'");
//		buf.append(",`tax_fee`='").append(this.taxFee).append("'");
//		buf.append(",`unique_desc`='").append(this.unique).append("'");
//		buf.append(",`sum_price`='").append(this.sumPrice).append("'");
//		buf.append(",`sum_area`='").append(this.sumArea).append("'");
//		buf.append(",`unit_price`='").append(this.unitPrice).append("'");
//		buf.append(",`first_pay`='").append(this.firstPay).append("'");
//		buf.append(",`monthly_provide`='").append(this.monthlyProvide).append("'");
//		buf.append(",`house_type`='").append(this.houseType).append("'");
//		buf.append(",`direction`='").append(this.direction).append("'");
//		buf.append(",`floor`='").append(this.floor).append("'");
//		buf.append(",`neigbour`='").append(this.neigbour).append("'");
//		buf.append(",`telphone`='").append(this.telphone).append("'");
//		buf.append(",`txn_count`='").append(this.txnCount).append("'");
//		buf.append(",`agent_comment_count`='").append(this.agentCommentCount).append("'");
//		buf.append(",`view_count`='").append(this.viewCount).append("'");
//		buf.append(",`agent_name`='").append(this.agentName).append("'");
//		buf.append(",`agent_type`='").append(this.agentType).append("'");
//		buf.append(",`occupation_year`='").append(this.occupationYear).append("'");
//		buf.append(",`monthly_view_count`='").append(this.monthlyViewCount).append("'");
//		buf.append(",`updated_ts`=now() ");
		
		buf.append(" `updated_ts`=now() ");
		buf.append(" WHERE `resource_id`='").append(this.resourceId).append("'");
		return buf.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Resource)) {
			return false;
		}
		
		Resource castObj = (Resource) obj;
		
		return new EqualsBuilder().append(this.getResourceId(), castObj.getResourceId()).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getResourceId()).toHashCode();
	}
}
