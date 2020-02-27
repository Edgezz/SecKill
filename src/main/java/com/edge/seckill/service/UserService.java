package com.edge.seckill.service;

import com.edge.seckill.dto.LoginDto;
import com.edge.seckill.vo.R;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */
public interface UserService {
    //唯一登录
    R login(LoginDto dto);
    //校验令牌 的有效性
    R checkToken(String token);
    //注册
    R register(LoginDto dto);
    //校验手机号
    R checkPhone(String phone);
}
