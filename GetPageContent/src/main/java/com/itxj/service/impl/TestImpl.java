package com.itxj.service.impl;

import com.itxj.mapper.MailMapper;
import com.itxj.mapper.TestMapper;
import com.itxj.pojo.Mail;
import com.itxj.pojo.Test;
import com.itxj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *  @项目名：  GetPageContent
 *  @包名：    com.itxj.service.impl
 *  @文件名:   TestImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/11/13 16:24
 *  @描述：    TODO
 */
@Service
public class TestImpl implements TestService {

    @Autowired
    private MailMapper mailMapper;

    @Autowired
    private TestMapper testMapper;

    @Override
    public void test() {
        System.out.println("测试service");
    }

    @Override
    public void add(Test test) {
        testMapper.insert(test);
    }

    @Override
    public int insert(List list) {
        for (Object o : list) {
            Test t= (Test) o;

            try{
                //添加到数据库
                testMapper.insert(t);
            }catch (Exception e){
                //添加到数据库
                testMapper.insert(null);
                break;
            }

        }
        return 1;
    }

    @Override
    public List<Mail> selecQQ(String qq) {
        Mail mail=new Mail();
        mail.setQq(qq);
        List<Mail> select = mailMapper.select(mail);
        return select;
    }

    @Override
    public int saveQQ(String qq) {
        Mail mail=new Mail();
        mail.setQq(qq);
        int select = mailMapper.insert(mail);
        return select;
    }
}
