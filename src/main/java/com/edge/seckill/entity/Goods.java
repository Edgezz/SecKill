package com.edge.seckill.entity;

import lombok.Data;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */

@Data
public class Goods {
    private Integer gid;
    private String gname;
    private Double gprice;
    private int gstock;
    private String gimg;
}
