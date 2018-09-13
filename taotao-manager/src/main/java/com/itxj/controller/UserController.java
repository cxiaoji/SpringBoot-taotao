package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        userService.test();
        System.out.println("controller 测试成功");
        return "controller 测试成功";
    }

}
