package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.pojo.User;
import com.itxj.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   UserController
 *  @创建者:   小吉
 *  @创建时间:  2018/9/13 13:05
 *  @描述：    TODO
 */
@RestController
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("test")
  public  String  test() {

        return "controller 测试成功";
    }

    @RequestMapping("addUser")
    public String addUser(){
        User user=new User();
        user.setId(102L);
        user.setUsername("xy");
        user.setPassword("123456");
        Date date=new Date();

        user.setCreated(date);
        user.setEmail("827693164@qq.com");
        user.setUpdated(date);
        user.setPhone("15914795276");
        //增加用户
        userService.addUser(user);
        //删除用户
        //修改用户
        //查询用户
        return "增加成功";
    }

}
