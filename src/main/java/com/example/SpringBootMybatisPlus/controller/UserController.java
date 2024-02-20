package com.example.SpringBootMybatisPlus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.SpringBootMybatisPlus.common.Result;
import com.example.SpringBootMybatisPlus.entity.User;
import com.example.SpringBootMybatisPlus.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SYW
 * @since 2023-06-30
 */
@Api(tags = "用户数据接口")
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;

/*
    @ExceptionHandler
    public String HandlerException(Exception e, HttpServletRequest request){
        String message=e.getMessage();
        request.setAttribute("errorMessage",message);
        return "error";
    }
*/

    /*
        @ExceptionHandler
        @ResponseBody
        public String HandlerException(Exception e){
            String message=e.getMessage();
            return message;
        }
    */
    @ExceptionHandler
    @ResponseBody
    public Result<Object> HandlerException(Exception e) {
        String message = e.getMessage();
        return Result.fail(message);
    }

    @GetMapping("/index")
    public String index() {
        return "success";
    }

    @GetMapping("/loginHTML")
    @ApiOperation("用户登录页面")
    public String loginHTML() {
        return "login2";
    }

    @GetMapping("/login")
    @ApiOperation("用户登录")
    @ResponseBody
    public Result<User> login(@ApiParam("提交用户数据") User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", user.getName());
        User one = userService.getOne(wrapper);
        if (passwordEncoder.matches(user.getPassword(), one.getPassword())) {
            return Result.success(one);
        } else {
            throw new RuntimeException("账号或密码错误");
        }
    }

    @GetMapping("/registerHTML")
    @ApiOperation("用户注册页面")
    public String registerHTML() {
        return "register";
    }

    @GetMapping("/register")
    @ApiOperation("用户注册")
    @ResponseBody
    public Result<?> register(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", user.getName());
        User one = userService.getOne(wrapper);
        if (one != null){
            throw new RuntimeException("账户已注册");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (userService.save(user)){
                return Result.success(user);
            } else {
                throw new RuntimeException("注册失败");
            }

        }
    }
    @GetMapping("/registerSuccess")
    public String registerSuccess(){
        return "registerSuccess";
    }
}

