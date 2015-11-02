package com.homejjr.clawer.lianjia.domain;

import java.util.Date;

import com.homejjr.clawer.lianjia.constant.Constant;
import com.homejjr.clawer.lianjia.dict.Dictionary;
import com.homejjr.clawer.util.DateUtil;

public class WechatPost {

	private int id;
	private String fId = "";
	private String address = "";
	private String anonymity = "1";
	private String anonymityNickName;
	private String anonymous = "";
	private String area = "";
	private String bizState = "";
	private String cityDict = Constant.CITY_DICT;
	private String commissionDivide = "5:5分成";
	private String contacts = "";
	private String contactsPhone = "";
	private String createMan = "";
	private Date createdTs;
	private String districtDict = Constant.DISTRICT_DICT;
	private double garenteeFee = 0;
	private String groupGuid = "";
	private String historyStateDesc = "";
	private String houseType = "";
	private String isEnabled = "1";
	private int isSpeedSell = 0;
	private String jumpClaim = "N";
	private int mobileClickCount;
	private String other = "";
	private String performanceVest = "蜜蜂社";
	private String performanceVestNew = "蜜蜂社";
	private String phoneProxyNo = "";
	private String postDetailType = "SEARCH_OBJECTIVE_INFO_HOUSE";
	private String price = "";
	private String pricePlan = Dictionary.PRICE_PLAN_STATE.HAND_PRICE.name();
	private String purposeType = "SEARCH_OBJECTIVE_SELL";
	private int readCount;
	private String referrer = "";
	private int remindCount = 0;
	private Date remindDate;
	private String stateDesc = "";
	private String subDistrictDict = Constant.CIRCLE_DICT;
	private String title = "";
	private Date updatedTs;
	private int userId = Constant.DEFAULT_USER_ID;
	private String userPhone = Constant.DEFAULT_PHONE_NO;
	private String visible = "";
	private String houseVillage = "";
	private String houseOrien = "";
	private String interiorFinish = "";
	private String floorCurrent = "";
	private String floorTotal = "";
	private String infoFrom = Constant.INFO_FROM;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAnonymity() {
		return this.anonymity;
	}

	public void setAnonymity(String anonymity) {
		this.anonymity = anonymity;
	}

	public String getAnonymityNickName() {
		return this.anonymityNickName;
	}

	public void setAnonymityNickName(String anonymityNickName) {
		this.anonymityNickName = anonymityNickName;
	}

	public String getAnonymous() {
		return this.anonymous;
	}

	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBizState() {
		return this.bizState;
	}

	public void setBizState(String bizState) {
		this.bizState = bizState;
	}

	public String getCityDict() {
		return this.cityDict;
	}

	public void setCityDict(String cityDict) {
		this.cityDict = cityDict;
	}

	public String getCommissionDivide() {
		return this.commissionDivide;
	}

	public void setCommissionDivide(String commissionDivide) {
		this.commissionDivide = commissionDivide;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsPhone() {
		return this.contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getCreateMan() {
		return this.createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getDistrictDict() {
		return this.districtDict;
	}

	public void setDistrictDict(String districtDict) {
		this.districtDict = districtDict;
	}

	public double getGarenteeFee() {
		return this.garenteeFee;
	}

	public void setGarenteeFee(double garenteeFee) {
		this.garenteeFee = garenteeFee;
	}

	public String getGroupGuid() {
		return this.groupGuid;
	}

	public void setGroupGuid(String groupGuid) {
		this.groupGuid = groupGuid;
	}

	public String getHistoryStateDesc() {
		return this.historyStateDesc;
	}

	public void setHistoryStateDesc(String historyStateDesc) {
		this.historyStateDesc = historyStateDesc;
	}

	public String getHouseType() {
		return this.houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public int getIsSpeedSell() {
		return this.isSpeedSell;
	}

	public void setIsSpeedSell(int isSpeedSell) {
		this.isSpeedSell = isSpeedSell;
	}

	public String getJumpClaim() {
		return this.jumpClaim;
	}

	public void setJumpClaim(String jumpClaim) {
		this.jumpClaim = jumpClaim;
	}

	public int getMobileClickCount() {
		return this.mobileClickCount;
	}

	public void setMobileClickCount(int mobileClickCount) {
		this.mobileClickCount = mobileClickCount;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	public String getPerformanceVestNew() {
		return performanceVestNew;
	}

	public void setPerformanceVestNew(String performanceVestNew) {
		this.performanceVestNew = performanceVestNew;
	}

	public String getPerformanceVest() {
		return this.performanceVest;
	}

	public void setPerformanceVest(String performanceVest) {
		this.performanceVest = performanceVest;
	}

	public String getPhoneProxyNo() {
		return this.phoneProxyNo;
	}

	public void setPhoneProxyNo(String phoneProxyNo) {
		this.phoneProxyNo = phoneProxyNo;
	}

	public String getPostDetailType() {
		return this.postDetailType;
	}

	public void setPostDetailType(String postDetailType) {
		this.postDetailType = postDetailType;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPricePlan() {
		return pricePlan;
	}

	public void setPricePlan(String pricePlan) {
		this.pricePlan = pricePlan;
	}

	public String getPurposeType() {
		return this.purposeType;
	}

	public void setPurposeType(String purposeType) {
		this.purposeType = purposeType;
	}

	public int getReadCount() {
		return this.readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getReferrer() {
		return this.referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public int getRemindCount() {
		return this.remindCount;
	}

	public void setRemindCount(int remindCount) {
		this.remindCount = remindCount;
	}

	public Date getRemindDate() {
		return this.remindDate;
	}

	public void setRemindDate(Date remindDate) {
		this.remindDate = remindDate;
	}

	public String getStateDesc() {
		return this.stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public String getSubDistrictDict() {
		return this.subDistrictDict;
	}

	public void setSubDistrictDict(String subDistrictDict) {
		this.subDistrictDict = subDistrictDict;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUpdatedTs() {
		return this.updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getVisible() {
		return this.visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getHouseVillage() {
		return houseVillage;
	}

	public void setHouseVillage(String houseVillage) {
		this.houseVillage = houseVillage;
	}

	public String getHouseOrien() {
		return houseOrien;
	}

	public void setHouseOrien(String houseOrien) {
		this.houseOrien = houseOrien;
	}

	public String getInteriorFinish() {
		return interiorFinish;
	}

	public void setInteriorFinish(String interiorFinish) {
		this.interiorFinish = interiorFinish;
	}

	public String getFloorCurrent() {
		return floorCurrent;
	}

	public void setFloorCurrent(String floorCurrent) {
		this.floorCurrent = floorCurrent;
	}

	public String getFloorTotal() {
		return floorTotal;
	}

	public void setFloorTotal(String floorTotal) {
		this.floorTotal = floorTotal;
	}
	
	public String getInfoFrom() {
		return infoFrom;
	}

	public void setInfoFrom(String infoFrom) {
		this.infoFrom = infoFrom;
	}

	public String ToUpdateSQL() {
		StringBuilder buf = new StringBuilder();
		buf.append("UPDATE `wechat_post` SET ");
		buf.append("`user_id`='").append(this.userId).append("',");
		buf.append("`post_detail_type`='").append(this.postDetailType).append("',");
		buf.append("`purpose_type`='").append(this.purposeType).append("',");
		buf.append("`city_dict`='").append(this.cityDict).append("',");
		buf.append("`district_dict`='").append(this.districtDict).append("',");
		buf.append("`sub_district_dict`='").append(this.subDistrictDict).append("',");
		buf.append("`address`='").append(this.address).append("',");
		buf.append("`area`='").append(this.area).append("',");
		buf.append("`price`='").append(this.price).append("',");
		buf.append("`house_type`='").append(this.houseType).append("',");
		buf.append("`is_enabled`='").append(this.isEnabled).append("',");
		buf.append("`read_count`='").append(this.readCount).append("',");
		buf.append("`mobile_click_count`='").append(this.mobileClickCount).append("',");
		buf.append("`updated_ts`=now(),");
		buf.append("`commission_divide`='").append(this.commissionDivide).append("',");
		buf.append("`performance_vest`='").append(this.performanceVest).append("',");
		buf.append("`performance_vest_new`='").append(this.performanceVestNew).append("',");
		buf.append("`anonymity`='").append(this.anonymity).append("',");
//		buf.append("`anonymity_nick_name`='").append(this.anonymityNickName).append("',");
//		buf.append("`user_phone`='").append(this.userPhone).append("',");
//		buf.append("`phone_proxy_no`='").append(this.phoneProxyNo).append("',");
//		buf.append("`group_guid`='").append(this.groupGuid).append("',");
		buf.append("`is_speed_sell`='").append(this.isSpeedSell).append("',");
		buf.append("`remind_count`='").append(this.remindCount).append("',");
		buf.append("`biz_state`='").append(this.bizState).append("',");
		buf.append("`garentee_fee`='").append(this.garenteeFee).append("',");
		buf.append("`jump_claim`='").append(this.jumpClaim).append("',");
		buf.append("`price_plan`='").append(this.pricePlan).append("',");
		buf.append("`house_village`='").append(this.houseVillage).append("',");
		buf.append("`house_orien`='").append(this.houseOrien).append("',");
		buf.append("`floor_current`='").append(this.floorCurrent).append("',");
		buf.append("`floor_total`='").append(this.floorTotal).append("' ");
		buf.append(" WHERE `f_id`='").append(this.fId).append("'");
		return buf.toString();
	}
	
	
	public String ToInsertSQL() {
		StringBuilder buf = new StringBuilder();
		buf.append("INSERT INTO `wechat_post`(`f_id`,`user_id`,`title`,`post_detail_type`,`purpose_type`,`city_dict`,`district_dict`,`sub_district_dict`,`address`,`area`,`price`,`house_type`,`other`,`anonymous`,`visible`,`is_enabled`,`read_count`,`mobile_click_count`,`created_ts`,`updated_ts`,`commission_divide`,`performance_vest`,`performance_vest_new`,`contacts`,`contacts_phone`,`create_man`,`referrer`,`anonymity`,`anonymity_nick_name`,`user_phone`,`phone_proxy_no`,`group_guid`,`is_speed_sell`,`state_desc`,`history_state_desc`,`remind_count`,`biz_state`,`garentee_fee`,`jump_claim`,`price_plan`,`house_village`,`house_orien`,`interior_finish`,`floor_current`,`floor_total`,`info_from`) ");
		buf.append(" VALUES (");
		buf.append("'").append(this.fId).append("',");
		buf.append("'").append(this.userId).append("',");
		buf.append("'").append(this.title).append("',");
		buf.append("'").append(this.postDetailType).append("',");
		buf.append("'").append(this.purposeType).append("',");
		buf.append("'").append(this.cityDict).append("',");
		buf.append("'").append(this.districtDict).append("',");
		buf.append("'").append(this.subDistrictDict).append("',");
		buf.append("'").append(this.address).append("',");
		buf.append("'").append(this.area).append("',");
		buf.append("'").append(this.price).append("',");
		buf.append("'").append(this.houseType).append("',");
		buf.append("'").append(this.other).append("',");
		buf.append("'").append(this.anonymous).append("',");
		buf.append("'").append(this.visible).append("',");
		buf.append("'").append(this.isEnabled).append("',");
		buf.append("'").append(this.readCount).append("',");
		buf.append("'").append(this.mobileClickCount).append("',");
		buf.append("'").append(DateUtil.Date2String(this.createdTs,"yyyy-MM-dd HH:mm:ss")).append("',");
		buf.append("'").append(DateUtil.Date2String(this.updatedTs, "yyyy-MM-dd HH:mm:ss")).append("',");
		buf.append("'").append(this.commissionDivide).append("',");		
		buf.append("'").append(this.performanceVest).append("',");
		buf.append("'").append(this.performanceVestNew).append("',");
		buf.append("'").append(this.contacts).append("',");
		buf.append("'").append(this.contactsPhone).append("',");
		buf.append("'").append(this.createMan).append("',");
		buf.append("'").append(this.referrer).append("',");
		buf.append("'").append(this.anonymity).append("',");
		buf.append("'").append(this.anonymityNickName).append("',");
		buf.append("'").append(this.userPhone).append("',");
		buf.append("'").append(this.phoneProxyNo).append("',");
		buf.append("'").append(this.groupGuid).append("',");
		buf.append("'").append(this.isSpeedSell).append("',");
		buf.append("'").append(this.stateDesc).append("',");
		buf.append("'").append(this.historyStateDesc).append("',");
		buf.append("'").append(this.remindCount).append("',");
		//buf.append("'").append(DateUtil.Date2String(this.remindDate,"yyyy-MM-dd HH:mm:ss")).append("',");
		buf.append("'").append(this.bizState).append("',");
		buf.append("'").append(this.garenteeFee).append("',");
		buf.append("'").append(this.jumpClaim).append("',");
		buf.append("'").append(this.pricePlan).append("',");
		buf.append("'").append(this.houseVillage).append("',");
		buf.append("'").append(this.houseOrien).append("',");
		buf.append("'").append(this.interiorFinish).append("',");
		buf.append("'").append(this.floorCurrent).append("',");
		buf.append("'").append(this.floorTotal).append("',");
		buf.append("'").append(this.infoFrom).append("'");
		buf.append(")");
		return buf.toString();
	}
}
