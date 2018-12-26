package com.itxj.service;

import com.itxj.pojo.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   addCartToCookie
 *  @创建者:   小吉
 *  @创建时间:  2018/12/18 14:45
 *  @描述：    TODO
 */
public interface CartCookiesService {


    //添加商品到cookie
    Integer addItemByCookie(HttpServletRequest request, long itemId, Integer num, HttpServletResponse response);

    //查询cookies中的购物车
   List<Cart> queryItemByCookie(HttpServletRequest request);
   //用户未登录删除购物车商品数据
    Integer deleItemByCart(long itemId,HttpServletRequest request,HttpServletResponse response);
    //用户未登录修改购物车商品数量
    Integer updateItemNumByCart(Integer num,long itemId,HttpServletRequest request,HttpServletResponse response);



}
