package com.itxj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   TestDevtools
 *  @创建者:   小吉
 *  @创建时间:  2018/11/17 16:51
 *  @描述：    TODO
 */
@Controller
public class TestDevtools {

    @RequestMapping("/dev")
    @ResponseBody
    public String Devtools(){
        System.out.println("devtools测试中......03");
        return "devtools...03";
    }
}
