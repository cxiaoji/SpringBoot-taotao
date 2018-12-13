package com.itxj.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itxj.mapper.UserMapper;
import com.itxj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   UserServiceImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/10/31 16:23
 *  @描述：    TODO  单点登录模块未实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //前台注册校验字段知否存在（1-name，2-phone，3-email）
    @Override
    public Boolean check(String param, int type) {


        User user=new User();

        switch (type) {

            case 1://校验用户名
                user.setUsername(param);
                break;
            case 2://校验手机号码
                user.setPhone(param);
                break;
            case 3://校验邮箱
                user.setEmail(param);
                break;

             default://空参数，默认校验用户名
                 user.setUsername(param);
                break;
        }
        List<User> list = userMapper.select(user);
        return list.size()<=0;
    }
}
