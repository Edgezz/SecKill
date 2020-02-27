package com.edge.seckill.service;

import com.edge.seckill.dto.SkillGoodsDto;
import com.edge.seckill.execption.OrderException;
import com.edge.seckill.vo.R;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
public interface OrderService {
    //需要什么参数 ：令牌、商品id、数量
    R save(SkillGoodsDto goodsDto) throws OrderException;

    //V2.0接口
    R saveV2(SkillGoodsDto goodsDto) throws OrderException;
}
