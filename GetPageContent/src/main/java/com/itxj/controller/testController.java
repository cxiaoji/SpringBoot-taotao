package com.itxj.controller;

import com.itxj.Utils.GrabByUrl;
import com.itxj.pojo.Test;
import com.itxj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/*
 *  @项目名：  GetPageContent
 *  @包名：    com.itxj.controller
 *  @文件名:   testController
 *  @创建者:   小吉
 *  @创建时间:  2018/11/13 16:22
 *  @描述：    TODO
 */
@Controller
public class testController {

    @Autowired
    private TestService testService;


    @RequestMapping("/test")
    @ResponseBody
     public String test(){
        testService.test();
        Test test=new Test();
        test.setUrl("baidu");
        test.setName("xj");

        testService.add(test);
         return "success";
     }

    @RequestMapping("/grab01")
    @ResponseBody
    public String grab01() throws Exception {
        //  http://25c6.com/list/?7-2.html
        new GrabByUrl().grab(testService,"http://868xx.com/list/?30-",".html",77,1);
        new GrabByUrl().grab(testService,"http://868xx.com/list/?7-",".html",47,1);

        return "success";

        }








}
