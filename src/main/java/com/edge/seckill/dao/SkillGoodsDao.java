package com.edge.seckill.dao;

import com.edge.seckill.entity.SkillGoods;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
public interface SkillGoodsDao {

    @Select("select * from t_seckill where seckill_id=#{skid}")
    SkillGoods selectById(int skid);

    //排他锁
    @Update("update t_seckill set seckill_stock=#{count} where seckill_id=#{skid} and seckill_stock>=#{count}")
    int update(int skid,int count);
}
