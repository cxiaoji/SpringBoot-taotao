package com.itxj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *  @项目名：  gSpringBootTest
 *  @包名：    com.itxj.controller
 *  @文件名:   testDevtools
 *  @创建者:   小吉
 *  @创建时间:  2018/11/1 18:40
 *  @描述：    TODO
 */
@Controller
public class testDevtools {

    @RequestMapping("/dev")
    @ResponseBody
    public String testDev(){
        System.out.println("正在测试热部署0000");
        return "正在测试热部署0000";
    }
}
