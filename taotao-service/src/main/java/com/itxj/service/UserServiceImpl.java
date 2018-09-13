package com.itxj.service;

import com.alibaba.dubbo.config.annotation.Service;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   UserServiceImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/9/13 13:09
 *  @描述：    TODO
 */
@Service
public class UserServiceImpl implements UserService {


    public void test(){

        System.out.println("service 测试成功");
    }
}
