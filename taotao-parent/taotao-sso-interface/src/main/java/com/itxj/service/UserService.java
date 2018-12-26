package com.itxj.service;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   UserService
 *  @创建者:   小吉
 *  @创建时间:  2018/11/3 14:52
 *  @描述：    TODO
 */
public interface UserService {

    //前台注册校验字段知否存在（1-name，2-phone，3-email）
    Boolean check(String param,int type);
}
