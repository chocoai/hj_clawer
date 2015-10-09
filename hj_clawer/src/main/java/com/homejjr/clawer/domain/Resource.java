package com.homejjr.clawer.domain;

public class Resource {
	
	private String resourceId;
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
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
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
	
	public String ToSqlString() {
		StringBuilder buf = new StringBuilder();
		buf.append("INSERT INTO `lianjia_resource`(`resource_id`,`title`,`tax_fee`,`unique_desc`,`sum_price`,`sum_area`,`unit_price`,`first_pay`,`monthly_provide`,`house_type`,`direction`,`floor`,`neigbour`,`telphone`,`txn_count`,`agent_comment_count`,`view_count`,`agent_name`,`agent_type`,`occupation_year`,`monthly_view_count`,`created_ts`,`updated_ts`) VALUES ");
		buf.append("('").append(this.resourceId).append("','").append(this.title).append("','").append(this.taxFee).append("','").append(this.unique).append("','");
		buf.append(this.sumPrice).append("','").append(this.sumArea).append("','").append(this.unitPrice).append("','").append(this.firstPay).append("','").append(this.monthlyProvide).append("','").append(this.houseType).append("','").append(this.direction).append("','").append(this.floor).append("','").append(this.neigbour).append("','").append(this.telphone).append("','");
		buf.append(this.txnCount).append("','").append(this.agentCommentCount).append("','").append(this.viewCount).append("','");
		buf.append(this.agentName).append("','").append(this.agentType).append("','").append(this.occupationYear).append("','").append(this.monthlyViewCount).append("',now(),now());");
		return buf.toString();
	}
}
