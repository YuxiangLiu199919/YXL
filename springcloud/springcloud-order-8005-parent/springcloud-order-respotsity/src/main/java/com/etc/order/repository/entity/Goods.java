package com.etc.order.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data //get,set,toString
@NoArgsConstructor //无参构造
@AllArgsConstructor //全参
@Accessors(chain = true) //支持链式写法
public class Goods implements Serializable { // 实体类，orm 类表关系映射
   private Integer gid;
   private Integer type_id;
   private String image;
   private String name;
   private Double price;
   private Integer stock;
   private String details;
   private String typeName;
   private Double discount;

}
