package com.edge.seckill.service.impl;

import com.edge.seckill.dto.LoginDto;
import com.edge.seckill.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Override
    public R login(LoginDto dto) {
        return null;
    }

    @Override
    public R checkToken(String token) {
        return null;
    }

    @Override
    public R register(LoginDto dto) {
        return null;
    }

    @Override
    public R checkPhone(String phone) {
        return null;
    }
}
