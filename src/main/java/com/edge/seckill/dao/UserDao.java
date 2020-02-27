package com.edge.seckill.dao;

import com.edge.seckill.dto.LoginDto;
import com.edge.seckill.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
public interface UserDao {
    @Insert("insert into t_user(phone, password) values (#{phone}, #{password})")
    int insert(LoginDto dto);

    @Select("select * from t_user where phone = #{phone}")
    User selectByPhone(String phone);
}
