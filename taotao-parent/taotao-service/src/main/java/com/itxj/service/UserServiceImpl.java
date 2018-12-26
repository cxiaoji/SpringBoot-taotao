package com.itxj.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.itxj.mapper.UserMapper;
import com.itxj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.DigestUtils;

import java.util.*;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   UserServiceImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/11/3 15:17
 *  @描述：    用户注册
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    //实现首页用户注册功能
    @Override
    public String addUser(User user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());

        //使用MD5加密密码
        String password = user.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());
             user.setPassword(password);
        int i = userMapper.insert(user);

        Map<String,String> map=new HashMap<>();
        if(i>0){
            map.put("status","200");//注册成功状态码
        }
        else{
            map.put("status","500");//注册成功状态码
        }
        String result=new Gson().toJson(map);

        return result;
    }
/**
 * 实现用户登录
 * 1.通过用户名和密码（MD5加密）查询用户是否存在
 * 2.若存在添加到redis缓存
 * 3.返回redis的key
 *
 */
    @Override
    public String login(User user) {

        //md5加密password-----对应数据库的passsword
        String password = user.getPassword();
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password);

        //创建redis
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();

        //查询数据库中是否存在此用户
        List<User> list = userMapper.select(user);

        //创建一个默认的key
        String key="";


        if(list.size()>0){
            //获取改用户
            User loginUser = list.get(0);
            System.out.println("loginUser=" + loginUser);

           key= "xj"+UUID.randomUUID().toString();
            //将用户数据转为字符串json
            String json=new Gson().toJson(loginUser);
            //存rediszhon
            opsForValue.set(key,json);

            System.out.println("key=" + key);
        }

        return key;
    }
}
