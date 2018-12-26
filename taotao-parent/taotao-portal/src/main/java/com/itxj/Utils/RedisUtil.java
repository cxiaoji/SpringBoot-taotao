package com.itxj.Utils;

import com.google.gson.Gson;
import com.itxj.pojo.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.Utils
 *  @文件名:   GetUserByRedisUtil
 *  @创建者:   小吉
 *  @创建时间:  2018/12/17 15:35
 *  @描述：    TODO
 */
public class RedisUtil {


   public static User GetUserByTicket(String ticket, RedisTemplate<String ,String> redisTemplate){
       User user=null;

       //如果Ticket不为空------从redis中获取用户信息
       if(!StringUtils.isEmpty(ticket)){
           String userJson = redisTemplate.opsForValue().get(ticket);
            user = new Gson().fromJson(userJson, User.class);
           //System.out.println("用户的id=" + user.getId() + "用户姓名==" + user.getUsername());

           return  user;
       }
       //如果为空---返回空的User对象
       else {
           return null;
       }


    }


    public static void clearCart( RedisTemplate<String ,String> redisTemplate,long userId){
        redisTemplate.delete("itxj_"+userId);
        System.out.println("删除购物车。。。。。。");

    }
}
