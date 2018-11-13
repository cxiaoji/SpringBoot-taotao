package com.itxj.controller;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   UserController
 *  @创建者:   小吉
 *  @创建时间:  2018/11/3 14:57
 *  @描述：    用户注册功能
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.pojo.User;
import com.itxj.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Reference
    private UserService userService;

    //实现首页注册
   //http://www.taotao.com/user/doRegister.shtml
    @PostMapping("/user/doRegister.shtml")
    @ResponseBody
    public String register(User user){

       return userService.addUser(user);

    }

    //实现登录
    @PostMapping("/user/doLogin.shtml")
    @ResponseBody
    public  Map<String,String> login(User user, HttpServletResponse response){
       String ticket= userService.login(user);
        System.out.println("ticket=" + ticket);
        Map<String,String> map=new HashMap<>();
       //判断ticket是否存在，如果存在----将ticket存cookie+准备数据跳转页面
        if(!StringUtils.isEmpty(ticket)){
          //数据存在
            map.put("status","200");
            map.put("success","http://www.taotao.com");

          //cookie
          Cookie cookie=new Cookie("ticket",ticket);
          cookie.setMaxAge(60 * 60 * 24 * 7);
          cookie.setPath("/");
          response.addCookie(cookie);
            return map;
        }
        map.put("status","500");
        return map;
    }

}
