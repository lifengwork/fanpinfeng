CREATE TABLE `order` (
  `order_id` varchar(32) NOT NULL COMMENT '订单编码',
  `order_acount` double(10,0) DEFAULT NULL COMMENT '订单金额',
  `order_pro_id` varchar(0) DEFAULT NULL COMMENT '产品编码',
  `order_pro_count` varchar(5) DEFAULT NULL COMMENT '产品数量',
  `order_pro_title` varchar(255) DEFAULT NULL COMMENT '订单标题',
`order_status` INT ( 255 ) DEFAULT NULL COMMENT '1待付款2待发货3待收货5取消0交易成功99交易失败98交易取消21退款中20退货成功',
`order_type` INT ( 255 ) DEFAULT NULL COMMENT '0正常单1退货单',
  `order_created` datetime DEFAULT NULL COMMENT '提交日期',
  `order_updated` datetime DEFAULT NULL COMMENT '更新日期',
  `order_pro_user` varchar(32) DEFAULT NULL COMMENT '发布者',
  `order_pro_consumer` varchar(32) DEFAULT NULL COMMENT '商品消费者',
  `order_dispatch_no` varchar(32) DEFAULT NULL COMMENT '配送单号',
  `order_dispatch_phone` varchar(11) DEFAULT NULL COMMENT '配送员',
  `order_comment` int(2) DEFAULT NULL COMMENT '0待评价1已评价',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;