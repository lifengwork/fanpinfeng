CREATE TABLE `tb_coupon` (
  `coupon_id` varchar(32) NOT NULL COMMENT '券索引',
  `coupon_title` varchar(32) DEFAULT NULL COMMENT '券标题',
  `coupon_no` varchar(0) DEFAULT NULL COMMENT '券编码',
  `coupon_price` double(10,2) DEFAULT NULL COMMENT '券金额',
  `coupon_status` int(2) DEFAULT NULL COMMENT '1有效0失效',
  `coupon_start_date` datetime DEFAULT NULL COMMENT '开始日期',
  `coupon_end_date` datetime DEFAULT NULL COMMENT '结束日期',
  `coupon_created` datetime DEFAULT NULL COMMENT '发布日期',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;