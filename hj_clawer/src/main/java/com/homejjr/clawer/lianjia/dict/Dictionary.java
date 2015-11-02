package com.homejjr.clawer.lianjia.dict;

public class Dictionary 
{
	public static enum MODE
	{
		CREATE_PAGE_LINK("获取所有列表页面的链接"),
		CREATE_RESOURCE_LINK("获取房源详情页面的链接"),
		FORMAT_RESOURCE("格式化房源信息成JSON格式"),
		SHUFFLE_RESOURCE("打乱房源信息列表的顺序"),
		MERGE_DATA("导入或者更新房源信息到数据库中"),
		BIZ_ETL("绑定400后更新和插入到业务表中"),
		REPORT("统计报告并发送");
		String desc;
		
		MODE(String desc)
		{
			this.desc = desc;
		}
		
		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		public String getDesc() {
			return this.desc;
		}
	}
	
	public static enum HOUSE_ORIEN {
		HOUSE_ORIENTATION_SOUTH("朝南"),
		HOUSE_ORIENTATION_NORTH("朝北"),
		HOUSE_ORIENTATION_D_SOUTH("双南"),
		HOUSE_ORIENTATION_SOUTH_NORTH("南北通透");
		
		private String desc;
		private HOUSE_ORIEN(String desc){
			this.desc = desc;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	public static enum HOUSE_FLOOR {
		HOUSE_FLOOR_REQUIRE_LOW("低区"),
		HOUSE_FLOOR_REQUIRE_MIDDLE("中区"),
		HOUSE_FLOOR_REQUIRE_HIGHER("高区");
		private String desc;
		private HOUSE_FLOOR(String desc){
			this.desc = desc;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
	public static enum PRICE_PLAN_STATE {
		PRICE("价格方案"),
		HAND_PRICE("到手价"),
		EACH_PRICE("各付价"),
		COMM_NO_PRICE("包税不包佣"),
		TAX_NO_PRICE("包佣不包税");
		private String desc;
		private PRICE_PLAN_STATE(String desc){
			this.desc = desc;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
}
