USE `agent_app`;

DROP TABLE IF EXISTS `lianjia_resource`;
CREATE TABLE `lianjia_resource`
(
  `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `resource_id` VARCHAR(60) NOT NULL DEFAULT '',
  `title` VARCHAR(60) NOT NULL DEFAULT '',
  `tax_fee` VARCHAR(60) NOT NULL DEFAULT '',
  `unique_desc` VARCHAR(60) NOT NULL DEFAULT '',
  `sum_price` VARCHAR(60) NOT NULL DEFAULT '',
  `sum_area` VARCHAR(60) NOT NULL DEFAULT '',
  `unit_price` VARCHAR(60) NOT NULL DEFAULT '',
  `first_pay` VARCHAR(60) NOT NULL DEFAULT '',
  `monthly_provide` VARCHAR(60) NOT NULL DEFAULT '',
  `house_type` VARCHAR(60) NOT NULL DEFAULT '',
  `direction` VARCHAR(60) NOT NULL DEFAULT '',
  `floor` VARCHAR(60) NOT NULL DEFAULT '',
  `neigbour` VARCHAR(60) NOT NULL DEFAULT '',
  `telphone` VARCHAR(60) NOT NULL DEFAULT '',
  `txn_count` VARCHAR(60) NOT NULL DEFAULT '',
  `agent_comment_count` VARCHAR(60) NOT NULL DEFAULT '',
  `view_count` VARCHAR(60) NOT NULL DEFAULT '',
  `agent_name` VARCHAR(60) NOT NULL DEFAULT '',
  `agent_type` VARCHAR(60) NOT NULL DEFAULT '',
  `occupation_year` VARCHAR(60) NOT NULL DEFAULT '',
  `monthly_view_count` VARCHAR(60) NOT NULL DEFAULT '',
  `created_ts` DATETIME, -- 创建数据时间戳
  `updated_ts` DATETIME  -- 更新数据时间戳
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `lianjia_resource` ADD UNIQUE(`resource_id`);
