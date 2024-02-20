package com.example.SpringBootMybatisPlus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SpringBootMybatisPlus.common.JwtUtil;
import com.example.SpringBootMybatisPlus.entity.User;
import com.example.SpringBootMybatisPlus.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootMybatisPlusApplicationTests {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private IUserService userService;

    @Test
    void contextLoads() {
        String pwd = "123456";
        String encode = passwordEncoder.encode(pwd);
        System.out.println(encode);//$2a$10$mYMO4wTRK0wuhYAndgVXSe/o2Uf4.6VzUswN4JtQBEG0UKFPYpv0.
    }
    //测试密码
    @Test
    void contextLoads1() {
        String pwd = "123456";
        String encode = "$2a$10$mYMO4wTRK0wuhYAndgVXSe/o2Uf4.6VzUswN4JtQBEG0UKFPYpv0.";
        boolean b = passwordEncoder.matches(pwd,encode);
        if (b){
            System.out.println("密码对");
        } else {
            System.out.println("密码错");
        }
    }

    @Test
    void contextLoads2() {
        //测试Token令牌
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name","admin");
        userQueryWrapper.eq("password","123456");
        User one = userService.getOne(userQueryWrapper);
        if (one == null){
            System.out.println("账密错误");
        } else {//签发令牌
            String token = jwtUtil.createToken(one);
            System.out.println(token);
            //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NzI4NGFiNy1jZDBjLTQ0MjItOWJmYy0wNGIxY2FjZjg2YmMiLCJzdWIiOiJ7XCJlbWFpbFwiOlwiMTIzNDQ0XCIsXCJpZFwiOjEsXCJuYW1lXCI6XCJhZG1pblwiLFwicGFzc3dvcmRcIjpcIjEyMzQ1NlwiLFwicGhvbmVcIjpcIjExMTExXCJ9IiwiaXNzIjoic3lzdGVtIiwiaWF0IjoxNjg4Mzc0MDk5LCJleHAiOjE2ODgzNzU4OTl9.IxP2pCnfC3kIc3wu55BNBCTr_KZicKc66MUmolih4rU
        }
    }

    //解析令牌
    @Test
    void contextLoads3(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NzI4NGFiNy1jZDBjLTQ0MjItOWJmYy0wNGIxY2FjZjg2YmMiLCJzdWIiOiJ7XCJlbWFpbFwiOlwiMTIzNDQ0XCIsXCJpZFwiOjEsXCJuYW1lXCI6XCJhZG1pblwiLFwicGFzc3dvcmRcIjpcIjEyMzQ1NlwiLFwicGhvbmVcIjpcIjExMTExXCJ9IiwiaXNzIjoic3lzdGVtIiwiaWF0IjoxNjg4Mzc0MDk5LCJleHAiOjE2ODgzNzU4OTl9.IxP2pCnfC3kIc3wu55BNBCTr_KZicKc66MUmolih4rU";
        User user = jwtUtil.parseToken(token, User.class);
        System.out.println(user);
    }

    //转账
    @Test
    void contextLoads4(){
        userService.updateMoney(1000);
    }
}