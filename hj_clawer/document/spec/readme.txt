1. 获取所有列表页面的链接并且保存到到output/lianjia/yyyy-MM-dd_page_link.txt文件中

需要执行如下命令：
java -DMODE=CREATE_PAGE_LINK －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
其中 REPORT_DATE=yyyy-MM-dd 默认可以不写，不写就是当天的日期
	MODE=CREATE_PAGE_LINK表示获取所有列表页面的链接
执行当天可以用如下命令：
java -DMODE=CREATE_PAGE_LINK -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 


2. 获取房源详情页面的链接并且保存到文件中yyyy-MM-dd_resource.link.txt
#java -DMODE=CREATE_RESOURCE_LINK －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
java -DMODE=CREATE_RESOURCE_LINK -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

3. 格式化所有房源信息为JSON格式并存入文件中yyyy-MM-dd_resource_json.txt
#java -DMODE=FORMAT_RESOURCE －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
java -DMODE=FORMAT_RESOURCE -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

4. 导入或者更新房源信息到数据库中,存入表agent_app.lianjia_resource中
#java -DMODE=MERGE_DATA －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
java -DMODE=MERGE_DATA -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

5. 处理数据后绑定400导入业务表wechat_post中
#java -DMODE=BIZ_ETL －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
java -DMODE=BIZ_ETL -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 


