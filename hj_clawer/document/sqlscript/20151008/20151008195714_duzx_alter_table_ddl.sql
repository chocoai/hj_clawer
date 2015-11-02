USE `agent_app`;

ALTER TABLE `lianjia_resource` ADD COLUMN `city_dict` VARCHAR(50) DEFAULT '' AFTER `resource_id`;
ALTER TABLE `lianjia_resource` ADD COLUMN `district_dict` VARCHAR(50) DEFAULT '' AFTER `city_dict`;
ALTER TABLE `lianjia_resource` ADD COLUMN `sub_district_dict` VARCHAR(50) DEFAULT '' AFTER `district_dict`;

