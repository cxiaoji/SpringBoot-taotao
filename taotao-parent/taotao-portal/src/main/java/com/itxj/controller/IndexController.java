package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.itxj.pojo.User;
import com.itxj.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Reference
    private ContentService contentService;

    //页面跳转
    @RequestMapping("/page/{PageName}")
    public String page(@PathVariable String PageName,String uri,HttpServletRequest request){
        System.out.println("uri=" + uri);
        request.setAttribute("uri",uri);
        return PageName;
    }


    //门户首页跳转
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request){

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
        
        //获取登录的消息
        Cookie[] cookies = request.getCookies();
        
        //判断cookies是否为空
         if(!StringUtils.isEmpty(cookies)){
             for (Cookie cookie : cookies) {
                 String cookieName = cookie.getName();//获取cookies中cookie中key----判断是否是ticket
                 if("ticket".equals(cookieName)){
                     String ticket= cookie.getValue();//从cookie中获取令牌---redis的key
                     String UserInfo= redisTemplate.opsForValue().get(ticket);//从redis中获取User数据
                     User user=new Gson().fromJson(UserInfo, User.class);//转为对象
                     System.out.println("user="+user);
                     model.addAttribute("user",user);
                 }
                 
             }
         }
        return "index";
    }
}
