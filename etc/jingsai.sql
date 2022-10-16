USE jingsai;

DROP TABLE IF EXISTS `process_info` CASCADE;
DROP TABLE IF EXISTS `process_net` CASCADE;
DROP TABLE IF EXISTS `netcard_traffic` CASCADE;

CREATE TABLE `process_info` (
    `id` INT AUTO_INCREMENT COMMENT 'ID',
    `pid`INT COMMENT '进程ID',
    `user` VARCHAR(64) COMMENT '进程所有者用户',
    `pr` INT COMMENT '进程优先级，值越小优先级越高',
    `ni` INT COMMENT '负值表示高优先级，正值表示低优先级',
    `virt` INT COMMENT '进程使用的虚拟内存总量，单位kb',
    `res` INT COMMENT '进程使用的，未被换出的物理内存大小，单位kb',
    `shr` INT COMMENT '共享内存大小',
    `s` CHAR(1) COMMENT '进程状态(D=不可中断的睡眠状态,R=运行,S=睡眠,T=跟踪/停止,Z=僵尸进程)',
    `cpu` DOUBLE COMMENT 'CPU占用百分比',
    `mem` DOUBLE COMMENT '进程使用的物理内存百分比',
    `time` VARCHAR(64) COMMENT '进程使用的CPU时间总计,单位1/100秒',
    `command` VARCHAR(64) COMMENT '命令名/命令行',
    `create_time` datetime COMMENT '记录的时间',
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `process_net` (
    `id` INT AUTO_INCREMENT COMMENT 'ID',
    `pid` INT COMMENT '进程ID',
    `proto` VARCHAR(64) COMMENT '协议',
    `local_address` VARCHAR(256) COMMENT '本地地址',
    `foreign_address` VARCHAR(256) COMMENT '外部地址',
    `state` VARCHAR(64) COMMENT '连接地址',
    `pid_program_name` VARCHAR(256) COMMENT '进程ID和程序名称',
    `create_time` datetime COMMENT '记录的时间',
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `netcard_traffic` (
    `id` INT AUTO_INCREMENT COMMENT 'ID',
    `nic` INT COMMENT '网卡名称',
    `status` VARCHAR(64) COMMENT '网卡状态',
    `rx` BIGINT COMMENT '接收流速',
    `tx` BIGINT COMMENT '发送流速',
    `rx_bytes` BIGINT COMMENT '接收总流量',
    `tx_bytes` BIGINT COMMENT '发送总流量',
    `create_time` datetime COMMENT '记录的时间',
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


