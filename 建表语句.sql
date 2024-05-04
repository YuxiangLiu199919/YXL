CREATE TABLE `master_order` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'UID',
  `order_id` varchar(64) NOT NULL COMMENT '订单ID',
  `buyer_id` varchar(64) NOT NULL COMMENT '买家ID',
  `seller_id` varchar(64) DEFAULT NULL COMMENT '卖家ID',
  `sku_id` varchar(64) NOT NULL COMMENT 'SKU ID',
  `total_price` decimal(10,2) NOT NULL COMMENT '总价',
  `order_status` varchar(20) NOT NULL COMMENT '订单状态',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '订单创建时间',
  PRIMARY KEY (`uid`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_buyer_id` (`buyer_id`),
  KEY `idx_seller_id` (`seller_id`),
  KEY `idx_buyer_id_create_time` (`buyer_id`,`create_time`),
  KEY `idx_seller_id_create_time` (`seller_id`,`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='订单表'


CREATE TABLE `order_item` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'UID',
  `order_item_id` varchar(64) NOT NULL COMMENT '订单明细ID',
  `order_id` varchar(64) NOT NULL COMMENT '订单ID',
  `product_id` varchar(64) NOT NULL COMMENT '商品ID',
  `quantity` int(11) NOT NULL COMMENT '商品数量',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `create_time` datetime NOT NULL COMMENT '明细创建时间',
  PRIMARY KEY (`uid`),
  KEY `idx_order_item_id` (`order_item_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表'

-- 回退语句
-- DROP TABLE `master_order`;
-- DROP TABLE `order_item`;