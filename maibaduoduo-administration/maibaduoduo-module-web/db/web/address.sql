CREATE TABLE `address` (
  `address_id` varchar(32) NOT NULL COMMENT '地址编码',
  `address_user` varchar(32) DEFAULT NULL COMMENT '所属用户',
  `address_details` varchar(50) DEFAULT NULL COMMENT '地址详情',
  `address_created` datetime DEFAULT NULL COMMENT '创建日期',
  `address_status` int(2) DEFAULT NULL COMMENT '0失效1有效',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;