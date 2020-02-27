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
public class SkillGoods {
    private int skid; //秒杀商品的id
    private Date stime; //开始时间
    private Date etime;//结束时间
    private int sstock;//秒杀商品的库存量
    private int gid;
    private Double sprice;
    private String surl;
    private Long version;
}
