package com.edge.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.edge.seckill.config.RedisKeyConfig;
import com.edge.seckill.dao.UserDao;
import com.edge.seckill.dto.LoginDto;
import com.edge.seckill.dto.TokenDto;
import com.edge.seckill.entity.User;
import com.edge.seckill.service.UserService;
import com.edge.seckill.utils.EncryptUtil;
import com.edge.seckill.utils.JwtUtil;
import com.edge.seckill.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author： Edge
 * @date： 2020/2/26
 * @description： TODO
 * @modifiedBy：
 * @version: 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    //自定义配置
    @Value("${skill.passkey}")
    private String passkey;
    //模板对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //唯一登录
    @Override
    public R login(LoginDto dto) {
        //1.校验账户是否冻结
        if (stringRedisTemplate.hasKey(RedisKeyConfig.USER_FREEZE + dto.getPhone())) {
            //该账号冻结
            return R.fail("亲，账户已被冻结,剩余解封时间" + stringRedisTemplate.
                    getExpire(RedisKeyConfig.USER_FREEZE + dto.getPhone()) + "秒");
        } else {
            //2.检查账号密码
            User user = userDao.selectByPhone(dto.getPhone());
            if (user != null) {
                //3.检查密码，密码是密文
                if (user.getPassword().equals(EncryptUtil.aesenc(passkey, dto.getPwd()))) {
                    //账号密码正确
                    //4.校验是不是登录，如果登录，本次将登录的令牌干掉
                    if (stringRedisTemplate.hasKey(RedisKeyConfig.USER_TOKEN + dto.getPhone())) {
                        //该账号之前登陆过
                        String str = stringRedisTemplate.opsForValue().get(RedisKeyConfig.USER_TOKEN + dto.getPhone());
                        //记录到挤掉信息中
                        stringRedisTemplate.opsForSet().add(RedisKeyConfig.TOKEN_SWAP, str);
                        //同时删除原来的令牌
                        stringRedisTemplate.delete(RedisKeyConfig.USER_TOKEN + str);
                    }
                    //5.生成令牌
                    TokenDto tokenDto = new TokenDto();
                    tokenDto.setPhone(dto.getPhone());
                    tokenDto.setSdate(new Date());
                    String token = JwtUtil.createToken(JSON.toJSONString(tokenDto));
                    //6.记录令牌到Redis中
                    stringRedisTemplate.opsForValue().set(RedisKeyConfig.TOKEN_USER + token,
                            JSON.toJSONString(user), RedisKeyConfig.TOKEN_HOURS, TimeUnit.HOURS);
                    //记录账号对应的令牌
                    stringRedisTemplate.opsForValue().set(RedisKeyConfig.USER_TOKEN,
                            token, RedisKeyConfig.TOKEN_HOURS, TimeUnit.HOURS);
                    //7.令牌返回
                    return R.ok(token);
                } else {
                    //密码错误，5分钟错误3次，冻结30分钟
                    int r = 0;
                    //8.校验错误次数，冻结账号
                    Set set = stringRedisTemplate.keys(RedisKeyConfig.USER_PASSFAIL + dto.getPhone() + ":*");
                    if (set != null && set.size() > 1) {
                        r = set.size();
                        //需要冻结 之前2次 + 这次  = 3次
                        stringRedisTemplate.opsForValue().set(RedisKeyConfig.USER_PASSFAIL + dto.getPhone(),
                                System.currentTimeMillis() / 1000 + "", RedisKeyConfig.USER_FREEZE_TIME, TimeUnit.MINUTES);
                    }
                    //9.记录本次的失败
                    stringRedisTemplate.opsForValue().set(RedisKeyConfig.USER_PASSFAIL + dto.getPhone() +
                            ":" + System.currentTimeMillis(), "", RedisKeyConfig.USER_FREEZE_TIME, TimeUnit.MINUTES);
                    r += 1;
                    return R.fail("亲，你已经失败" + r + "次数，小心账号被冻结哟！");
                }
            } else {
                return R.fail("用户名或密码错误！");
                }
        }

    }

    @Override
    public R checkToken(String token) {
        if(stringRedisTemplate.hasKey(RedisKeyConfig.TOKEN_SWAP + token)) {
            //账号被挤掉
            return R.fail("亲，你的账号已经在其他设备上进行登录，您可以重新登录");
        } else {
            if(stringRedisTemplate.hasKey(RedisKeyConfig.TOKEN_USER + token)) {
                //令牌有效
                return R.ok();
            } else {
                //无效 1.有限期结束  2.修改密码
                return R.fail("亲，登录已失效，请重新登录");
            }
        }
    }

    @Override
    public R register(LoginDto dto) {
        if(userDao.selectByPhone(dto.getPhone()) == null) {
            dto.setPwd(EncryptUtil.aesenc(passkey, dto.getPwd()));
            if(userDao.insert(dto) > 0) {
                return R.ok();
            } else {
                return R.fail("网络出故障");
            }
        } else {
            return R.fail("该账号已经被注册！");
        }
    }

    @Override
    public R checkPhone(String phone) {
        if(userDao.selectByPhone(phone) == null) {
            return R.ok();
        } else {
            return R.fail("该账号已经被注册！");
        }
    }
}
