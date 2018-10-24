package com.itxj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj
 *  @文件名:   controllerApp
 *  @创建者:   小吉
 *  @创建时间:  2018/9/13 13:01
 *  @描述：    TODO
 */

//告诉SpringBoot 不要检测数据源  exclude ： 不要包含数据源的配置
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class controllerApp {
    public static void main(String[] args) {
        SpringApplication.run(controllerApp.class, args);
    }
}