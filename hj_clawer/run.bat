
rem 获取所有列表页面的链接并且保存文件中
rem java -DMODE=CREATE_PAGE_LINK －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
java -DMODE=CREATE_PAGE_LINK -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

rem 获取房源详情页面的链接并且保存到文件中
rem java -DMODE=CREATE_RESOURCE_LINK －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
java -DMODE=CREATE_RESOURCE_LINK -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

rem 格式化所有房源信息为JSON格式并存入文件中
rem java -DMODE=FORMAT_RESOURCE －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
java -DMODE=FORMAT_RESOURCE -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

rem 将所有JSON房源信息列表打乱顺序后以JSON格式继续存入文件中
rem java -DMODE=SHUFFLE_RESOURCE －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
java -DMODE=SHUFFLE_RESOURCE -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

rem 导入或者更新房源信息到数据库中
rem java -DMODE=MERGE_DATA －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
java -DMODE=MERGE_DATA -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

rem 处理数据后绑定400导入业务表
rem java -DMODE=BIZ_ETL －DREPORT_DATE=2015-10-10 -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
java -DMODE=BIZ_ETL -jar hj_clawer-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

