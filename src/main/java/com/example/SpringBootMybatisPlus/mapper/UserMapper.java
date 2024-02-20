package com.example.SpringBootMybatisPlus.mapper;

import com.example.SpringBootMybatisPlus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SYW
 * @since 2023-06-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    void updateMoneyByAdmin01Mapper(@Param("id") int id,@Param("money") double money);

    void updateMoneyByAdmin02Mapper(@Param("id") int id,@Param("money") double money);
}
