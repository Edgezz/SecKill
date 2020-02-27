package com.edge.seckill.dto;

import lombok.Data;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
@Data
public class SkillGoodsDto {
    private String token;//令牌 前后端分离
    private int gid;//描述商品的id
    private int count;//数量 内部做上限控制
    private String type;//渠道类型 pc app small
}
