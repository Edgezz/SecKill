package com.edge.seckill.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */

@Data
public class Order {
    private Integer oid;
    private int sgid; //秒杀商品id
    private Double tprice;
    private Date ctime;
    private Integer status;//订单状态
    private int uid;
    private int type;//订单类型 1：普通 2：秒杀
}
