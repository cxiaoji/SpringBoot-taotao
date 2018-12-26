package com.itxj.service;

import com.itxj.pojo.User;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   UserService
 *  @创建者:   小吉
 *  @创建时间:  2018/9/13 12:59
 *  @描述：    TODO
 */
public interface UserService {

//实现用户注册
   String addUser(User user);
//实现用户登录
    String login(User user);
}
