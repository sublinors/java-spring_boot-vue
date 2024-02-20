package com.example.SpringBootMybatisPlus.service.impl;

import com.example.SpringBootMybatisPlus.entity.User;
import com.example.SpringBootMybatisPlus.mapper.UserMapper;
import com.example.SpringBootMybatisPlus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SYW
 * @since 2023-06-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;//等同于 this.baseMapper
    @Override
    @Transactional//事务
    public void updateMoney(double money) {
        userMapper.updateMoneyByAdmin01Mapper(1,money);
        userMapper.updateMoneyByAdmin02Mapper(2,money);
    }
}
