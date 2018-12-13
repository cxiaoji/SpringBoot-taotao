package com.itxj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   CartController
 *  @创建者:   小吉
 *  @创建时间:  2018/12/13 0:08
 *  @描述：    添加购物车
 *               1. 添加商品到购物车
                        a)	获取用户信息
                        b)	调用购物车服务，把商品数据保存在redis的购物车中的
                        i.	参数包含：用户id，商品id，商品数量
                        2. 展示购物车详情页
                        a)	获取用户信息，需要用户的id
                        b)	调用购物车服务，把该用户的所有购物车数据查询数来，根据用户id查询
                        c)	跳转到购物车页面（cart.jsp），显示数据

 */
@Controller
public class CartController {


    //http://www.taotao.com/cart/1231490.html?num=3
    @RequestMapping("/cart/{id}.html")
    @ResponseBody
    public String addItemToCart(@PathVariable long id,Integer num) {

        //System.out.println(id + "==" + num);
        return "success";

    }
}
