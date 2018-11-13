package com.itxj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   PageController
 *  @创建者:   小吉
 *  @创建时间:  2018/11/13 13:46
 *  @描述：    TODO
 */
@Controller
public class PageController {

    @RequestMapping("/page/search.shtml")
    public String index(){

        System.out.println("跳转搜索页面");

         return "search";
    }


}
