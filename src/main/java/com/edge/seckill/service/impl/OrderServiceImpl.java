package com.edge.seckill.service.impl;

import com.edge.seckill.dto.SkillGoodsDto;
import com.edge.seckill.execption.OrderException;
import com.edge.seckill.service.OrderService;
import com.edge.seckill.vo.R;
import org.springframework.stereotype.Service;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public R save(SkillGoodsDto goodsDto) throws OrderException {
        return null;
    }

    @Override
    public R saveV2(SkillGoodsDto goodsDto) throws OrderException {
        return null;
    }
}
