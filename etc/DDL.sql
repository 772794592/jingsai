CREATE DATABASE IF NOT EXISTS ferryway DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
USE ferryway;

DROP TABLE IF EXISTS `service_process` CASCADE;
DROP TABLE IF EXISTS `process_info` CASCADE;
DROP TABLE IF EXISTS `l_service_log` CASCADE;
DROP TABLE IF EXISTS `l_message` CASCADE;


CREATE  TABLE  `service_process` (
    `id` INT AUTO_INCREMENT COMMENT 'ID',
    `service_name` varchar(64) COMMENT '服务名称',
    `service_status` INT COMMENT '服务状态',
    `record_time` bigint COMMENT '记录时间',
     PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `process_info` (
    `id` INT AUTO_INCREMENT COMMENT 'ID',
    `service_name` varchar(64) COMMENT '服务名称',
    `pid`INT  COMMENT '进程ID',
    `user` VARCHAR(64) COMMENT '进程所有者用户',
    `pr` INT COMMENT '进程优先级，值越小优先级越高',
    `ni` INT COMMENT '负值表示高优先级，正值表示低优先级',
    `virt` INT COMMENT '进程使用的虚拟内存总量，单位kb',
    `res` varchar(64) COMMENT '进程使用的，未被换出的物理内存大小，单位kb',
    `shr` INT COMMENT '共享内存大小',
    `s` CHAR(1) COMMENT '进程状态(D=不可中断的睡眠状态,R=运行,S=睡眠,T=跟踪/停止,Z=僵尸进程)',
    `cpu` DOUBLE COMMENT 'CPU占用百分比',
    `mem` DOUBLE COMMENT '进程使用的物理内存百分比',
    `time` VARCHAR(64) COMMENT '进程使用的CPU时间总计,单位1/100秒',
    `command` VARCHAR(64) COMMENT '命令名/命令行',
    `record_time` bigint COMMENT '记录的时间',
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


CREATE TABLE `l_service_log`  (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `service_pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `tcp_count` int NULL DEFAULT NULL,
    `tcp_port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `insert_time` bigint NULL DEFAULT NULL,
    `service_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `l_message`  (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `local_address` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地地址',
    `foreign_address` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '远程地址',
    `state` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tcp状态',
    `pid` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务pid',
    `program` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tcp参数',
    `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tcp连接名字',
    `insert_time` bigint NULL DEFAULT NULL COMMENT '插入时间',
    `service_log_id` bigint NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;