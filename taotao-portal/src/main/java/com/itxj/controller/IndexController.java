package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   IndexController
 *  @创建者:   小吉
 *  @创建时间:  2018/10/10 23:39
 *  @描述：    TODO
 */
@Controller
public class IndexController {

    @Reference
    private ContentService contentService;

    //门户首页跳转
    @RequestMapping("/")
    public String index(Model model){

        //首页大广告图片查询显示
        String categoryParentId="89";
        String  json = contentService.selectContentByCategoryParentId(Long.parseLong(categoryParentId));

//        //构造前端所需的数据机构
//        /**
//         * {
//         * 	"srcB": "http://192.168.174.127/group1/M00/00/00/wKiuf1mCexeABwCpAADW-NO57MA039.jpg",
//         * 	"height": 240,
//         * 	"alt": "",
//         * 	"width": 670,
//         * 	"src": "http://192.168.174.127/group1/M00/00/00/wKiuf1mCexeABwCpAADW-NO57MA039.jpg",
//         * 	"widthB": 550,
//         * 	"href": "http://sale.jd.com/act/e0FMkuDhJz35CNt.html?cpdad=1DLSUE",
//         * 	"heightB": 240
//         * }
//         */
//        List<Map<String,Object>> list=new ArrayList<>();
//        for (Content content : contents) {
//            Map<String,Object> map=new HashMap<>();
//            map.put("srcB",content.getPic2());
//            map.put("height",240);
//            map.put("alt","");
//            map.put("width",670);
//            map.put("src",content.getPic());
//            map.put("widthB",550);
//            map.put("href",content.getUrl());
//            map.put("heightB",240);
//
//            list.add(map);
//        }
//        //json格式转化
//        Gson gson=new Gson();
//        String json = gson.toJson(list);

        //将json数据存入视图解析器
        model.addAttribute("list",json);
        return "index";
    }
}
