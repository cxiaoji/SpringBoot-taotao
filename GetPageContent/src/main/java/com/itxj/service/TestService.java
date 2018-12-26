package com.itxj.service;

import com.itxj.pojo.Mail;
import com.itxj.pojo.Test;

import java.util.List;

/*
 *  @项目名：  GetPageContent
 *  @包名：    com.itxj.service
 *  @文件名:   TestService
 *  @创建者:   小吉
 *  @创建时间:  2018/11/13 16:23
 *  @描述：    TODO
 */
public interface TestService {

    void test();

    void add(Test test);

    int insert(List list);

    //查询数据库是否存在QQ号码
    List<Mail> selecQQ(String qq);

    //保存QQ到数据库
    int saveQQ(String qq);
}
