package com.itxj.JsoupUtils.Impl;

import com.itxj.JsoupUtils.InsertSql;
import com.itxj.mapper.TestMapper;
import com.itxj.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *  @项目名：  GetPageContent
 *  @包名：    com.itxj.JsoupUtils.Impl
 *  @文件名:   InsertSql
 *  @创建者:   小吉
 *  @创建时间:  2018/12/24 19:14
 *  @描述：    TODO
 */
@Service
public class InsertSqlImpl  {
    @Autowired
    private TestMapper testMapper;

    public   void insertSql(String name, String url, int num) {
        Test t = new Test();//创建对象存储数据

        t.setName(name);
        t.setUrl(url);
        t.setNum(0);

        //添加到数据库
        testMapper.insert(t);
    }
}
