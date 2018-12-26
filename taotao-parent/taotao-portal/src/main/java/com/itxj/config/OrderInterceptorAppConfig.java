package com.itxj.config;

import com.itxj.interceptor.OrderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.config
 *  @文件名:   OrderInterceptorAppConfig
 *  @创建者:   小吉
 *  @创建时间:  2018/12/20 14:40
 *  @描述：    订单拦截器的配置类------配置拦截器拦截的地址
 */

@Component
public class OrderInterceptorAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private OrderInterceptor orderInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        System.out.println("现在开始注册订单拦截器.................");

        ///添加拦截器------------         拦截器       -----拦截的地址

       // registry.addInterceptor(new OrderInterceptor()).addPathPatterns("/order/**");

        registry.addInterceptor(orderInterceptor).addPathPatterns("/order/**");
       // service/order/submit
        registry.addInterceptor(orderInterceptor).addPathPatterns("/service/order/**");
        //registry.addInterceptor(orderInterceptor).addPathPatterns("/cart/**");


        System.out.println("订单拦截器注册成功.................");

    }
}
