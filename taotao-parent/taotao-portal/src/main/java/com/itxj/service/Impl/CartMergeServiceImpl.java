package com.itxj.service.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.itxj.pojo.Cart;
import com.itxj.service.CartCookiesService;
import com.itxj.service.CartMergeService;
import com.itxj.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service.Impl
 *  @文件名:   CartMergeServiceImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/12/19 18:35
 *  @描述：    TODO
 */
@Service
public class CartMergeServiceImpl implements CartMergeService {

    @Autowired
    private CartCookiesService cartCookiesService;

    @Reference
    private CartService cartService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //合并购物车----cookie+redis
    @Override
    public void mergeCart(HttpServletRequest request, HttpServletResponse response,long userId) {

        //查询cookie中购物车商品
        List<Cart> cartListByCookie = cartCookiesService.queryItemByCookie(request);
        //查询redis中购物车商品
        List<Cart> cartListByRedis = cartService.queryCartByUserId(userId);

        if(cartListByCookie==null||cartListByRedis==null){
            return;
        }
                    //循环遍历叠加商品
                    for (Cart cart : cartListByCookie) {

                        //redis购物车中已经存在cookie中的购物车
                        if (cartListByRedis.contains(cart)){
                            int i = cartListByRedis.indexOf(cart);

                            cartListByRedis.get(i).setNum(cartListByRedis.get(i).getNum()+cart.getNum());

                        }
                        else{
                            //redis购物车中没有存在该商品（cookie中）
                            cartListByRedis.add(cart);
                        }
                    }

                //保存叠加后的购物车商品到redis
                String json = new Gson().toJson(cartListByRedis);
                redisTemplate.opsForValue().set("itxj_"+userId,json);
                String toJson = null;
                try {
                    //清除cookie
                    toJson = new Gson().toJson(cartListByCookie);
                    toJson= URLEncoder.encode(toJson,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                System.out.println("用户登录成功，现在合并cookie+redis购物车成功");

                Cookie cookie=new Cookie("itxj_cart",null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);

    }
}
