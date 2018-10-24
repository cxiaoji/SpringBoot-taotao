package com.itxj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   pageController
 *  @创建者:   小吉
 *  @创建时间:  2018/9/18 11:02
 *  @描述：    TODO
 */
@Controller
public class pageController {

    @RequestMapping("index")
    public String index(){


        return "index";
    }

    @RequestMapping("/rest/page/{pageName}")
    public String index(@PathVariable String  pageName){


        return pageName;
    }
}
