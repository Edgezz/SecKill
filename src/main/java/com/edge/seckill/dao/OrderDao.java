package com.edge.seckill.dao;

import com.edge.seckill.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
public interface OrderDao {

    @Select("select * from t_order where uid = #{uid} and goods_id = #{gid}")
    Order selectByUid(@Param("uid") int uid, @Param("gid") int gid);

    @Insert("insert into t_order (goods_id, total_price, create_time, status, type, uid) values(#{gid},#{tprice},#{ctime},#{status},#{type},#{uid})")
    int insert(Order order);
}
