package com.itxj.test;

import com.itxj.bean.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

/*
 *  @项目名：  TestFreeMarker
 *  @包名：    com.itxj.test
 *  @文件名:   testFreeMarker
 *  @创建者:   小吉
 *  @创建时间:  2018/12/4 13:33
 *  @描述：    模板+数据====页面（FreeMarker）
 */
public class TestFreeMarker {

    @Test
    public void testFreeMarker() throws Exception {

//1、FreeMarker核心配置类
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);

//2、获得模板目录
        String path = System.getProperty("user.dir") + "\\src\\main\\resources";
        System.out.println(path);
//3、设置模板目录
        configuration.setDirectoryForTemplateLoading(new File(path + "\\ftl"));

//4、在模板目录下通过模板名称------获取模板
        Template template = configuration.getTemplate("test.html");

//5、封装动态数据
        Map<String, Object> datas = new HashMap<>();

        //5-1、封装 字符串字段
        datas.put("username", "小吉02");

        //5-2、封装 对象数据
        datas.put("user", new User(1l, "小吉", "深圳"));

        // 5-3、封装 list集合
        List<User> list = new ArrayList<>();
        list.add(new User(01l, "小吉01", "深圳01"));
        list.add(new User(02l, "小吉02", "深圳02"));
        list.add(new User(03l, "小吉03", "深圳03"));
        datas.put("list", list);

        // 5-4、封装 map集合
        Map<String, Object> userMap = new HashMap<>();

        userMap.put("user001", new User(001l, "小吉001", "深圳001"));
        userMap.put("user002", new User(002l, "小吉002", "深圳002"));
        userMap.put("user003", new User(003l, "小吉003", "深圳003"));

        datas.put("map", userMap);
        // 5-5、封装 空null数据
        String empty = null;
        datas.put("empty", userMap);

        // 5-6、封装时间数据
        Date date = new Date();
        datas.put("date", date);

//6、输出生成页面指定的位置
        Writer writer = new FileWriter(new File(path + "\\index.html"));

//7、输出模板+数据生成html页面
        template.process(datas, writer);

    }
}
