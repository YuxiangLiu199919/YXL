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
public class User implements Serializable { //user 实体类，orm 类表关系映射
    private Integer uid; //主键
    private String userName;
    private String userPhone;
    private String userPassword;
    private Integer sex;
    private String userEmail;
    private String userAddress;

    public User(String userName, String userPhone, String userPassword, Integer sex, String userEmail, String userAddress) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.sex = sex;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
    }
}
