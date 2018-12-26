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
import com.itxj.Utils.CookiesUtil;
import com.itxj.Utils.RedisUtil;
import com.itxj.pojo.User;
import com.itxj.service.CartCookiesService;
import com.itxj.service.CartMergeService;
import com.itxj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Reference
    private UserService userService;

    @Autowired
    private CartCookiesService cartCookiesService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private CartMergeService cartMergeService;
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
    public  Map<String,String> login(User user, HttpServletRequest request, HttpServletResponse response){
       String ticket= userService.login(user);
        System.out.println("ticket=" + ticket);
        Map<String,String> map=new HashMap<>();
       //判断ticket是否存在，如果存在----将ticket存cookie+准备数据跳转页面
        if(!StringUtils.isEmpty(ticket)){
            //用户登录成功---合并购物车
            User user1 = new RedisUtil().GetUserByTicket(ticket, redisTemplate);
            System.out.println("user="+user1);
            Long userId = new RedisUtil().GetUserByTicket(ticket, redisTemplate).getId();
            System.out.println("userId=="+userId);
            cartMergeService.mergeCart(request, response, userId);

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

    @RequestMapping("/user/doExit.shtml")
    public String exit(HttpServletRequest request,HttpServletResponse response){
        String ticket = CookiesUtil.getTicketByCookies(request);
        //清除redis中的user信息
        redisTemplate.delete(ticket);
        //清除cookie中的ticket
        //Cookie cooke=new Cookie(ticket,null);


        System.out.println("redisUser..." + redisTemplate.opsForValue().get(ticket));
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(ticket))
                cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);

            System.out.println("正在退出");
            System.out.println("cookieUser..." + cookie.getName()+"..."+cookie.getValue());
        }
        return "redirect:http://www.taotao.com/";
    }

}
