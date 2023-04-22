CREATE TABLE `product` (
  `pro_id` varchar(32) NOT NULL COMMENT '商品编号',
  `pro_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `pro_title` varchar(255) DEFAULT NULL COMMENT '商品标题',
  `pro_type` varchar(255) DEFAULT NULL COMMENT '商品分类',
  `pro_desc` varchar(255) DEFAULT NULL COMMENT '商品简介',
  `pro_unit` varchar(255) DEFAULT NULL COMMENT '商品单位',
  `pro_img` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `pro_status` varchar(255) DEFAULT NULL COMMENT '商品状态',
  `pro_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `pro_promotion_price` double(10,2) DEFAULT NULL COMMENT '促销价',
  `pro_count` varchar(255) DEFAULT NULL COMMENT '商品数量',
  `pro_user_id` varchar(11) DEFAULT NULL COMMENT '所属用户',
  `pro_up_date` datetime DEFAULT NULL COMMENT '上架日期',
  `pro_down_date` datetime DEFAULT NULL COMMENT '下架日期',
  `pro_create` varchar(255) DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;