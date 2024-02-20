package com.example.SpringBootMybatisPlus.service;

import com.example.SpringBootMybatisPlus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SYW
 * @since 2023-06-30
 */
public interface IUserService extends IService<User> {
    //转账
    void updateMoney(double money);
}
