package com.itxj.controller;

import com.itxj.JsoupUtils.GrabContentByHtml;
import com.itxj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *  @项目名：  GetPageContent
 *  @包名：    com.itxj.controller
 *  @文件名:   testJsoupControllere
 *  @创建者:   小吉
 *  @创建时间:  2018/12/24 18:30
 *  @描述：    TODO
 */
@Controller
public class testJsoupControllere {

    @Autowired
    private TestService testService;



    @RequestMapping("/grab001")
    @ResponseBody
    public String grab01() throws Exception {
        //  http://25c6.com/list/?7-2.html
        //new GrabByUrl().grab(testService,"http://868xx.com/list/?30-",".html",77,1)
       new GrabContentByHtml().grabContentByhtml("http://868xx.com/list/?1-",".html",342,1);
        new GrabContentByHtml().grabContentByhtml("http://868xx.com/list/?2-",".html",306,1);
        new GrabContentByHtml().grabContentByhtml("http://868xx.com/list/?32-",".html",53,1);
        new GrabContentByHtml().grabContentByhtml("http://868xx.com/list/?31-",".html",94,1);
        new GrabContentByHtml().grabContentByhtml("http://868xx.com/list/?30-",".html",137,1);
        new GrabContentByHtml().grabContentByhtml("http://868xx.com/list/?4-",".html",74,1);
        new GrabContentByHtml().grabContentByhtml("http://868xx.com/list/?5-",".html",31,1);
        new GrabContentByHtml().grabContentByhtml("http://868xx.com/list/?7-",".html",49,1);
        new GrabContentByHtml().grabContentByhtml("http://868xx.com/list/?9-",".html",42,1);
        new GrabContentByHtml().grabContentByhtml("http://868xx.com/list/?14-",".html",27,1);








        return "success";

    }

}
