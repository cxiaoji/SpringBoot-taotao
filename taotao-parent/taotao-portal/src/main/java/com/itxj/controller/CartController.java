package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.Utils.CookiesUtil;
import com.itxj.Utils.RedisUtil;
import com.itxj.pojo.Cart;
import com.itxj.pojo.User;
import com.itxj.service.CartCookiesService;
import com.itxj.service.CartMergeService;
import com.itxj.service.CartService;
import com.itxj.service.Impl.CartCookiesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @描述：    TODO 购物车中删除多个商品
 *  @包名：    com.itxj.controller
 *  @文件名:   CartController
 *  @创建者:   小吉
 *  @创建时间:  2018/12/13 0:08
 *  @描述：    添加购物车
 *
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

    @Reference
    private CartService cartService;

    @Autowired
    private CartCookiesService cartCookiesService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private CartMergeService cartMergeService;

    //http://www.taotao.com/cart/1231490.html?num=3
    @RequestMapping("/cart/add//{id}.html")

    public String addItemToCart(@PathVariable("id") long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {

        System.out.println("添加的商品的id=" + itemId + ";添加商品的数量" + num);

        //获取用户登录凭证cookies
        String ticket = new CookiesUtil().getTicketByCookies(request);
        System.out.println("redisTemplate===" + redisTemplate);

        //通过用户凭证ticket获取redis中用户信息

        User user = new RedisUtil().GetUserByTicket(ticket, redisTemplate);

        //判断用户是否登录
        if (user != null) {
            //用户已经登录
            System.out.println("用户已经登录");
            //添加商品到购物车
            cartService.addItemToCart(user.getId(), itemId, num);


        } else {
            ///用户未登录
            //添加商品到cookie
            cartCookiesService.addItemByCookie(request, itemId, num, response);
            System.out.println("用户没有登录");
        }


        return "cartSuccess";

    }

    @RequestMapping("/cart/cart.html")
    public String showCart(HttpServletRequest request) {

        //购物车商品集合
        List<Cart> cartList = null;

        //获取用户登录凭证cookies
        String ticket = new CookiesUtil().getTicketByCookies(request);

        //通过用户凭证ticket获取redis中用户信息

        User user = new RedisUtil().GetUserByTicket(ticket, redisTemplate);

        //判断用户是否登录
        if (user != null) {
            //用户已经登录

            //查询购物车商品数据
            cartList = cartService.queryCartByUserId(user.getId());


        } else {
            ///用户未登录

            cartList = new CartCookiesServiceImpl().queryItemByCookie(request);

        }

        //将购物车数据存入域中---跳转页面
        request.setAttribute("cartList", cartList);
        System.out.println("cookie中的cartList==" + cartList);


        return "cart";
    }

    /**
     * 实现更新购物车商品的数量
     *
     * @param itemId  商品id
     * @param num     商品购买数量
     * @param request
     * @return
     */
    @RequestMapping("/service/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public String updateItemNumByCart(@PathVariable("itemId") long itemId, @PathVariable("num") Integer num,
                                      HttpServletRequest request, HttpServletResponse response) {
        //获取用户登录凭证cookies
        String ticket = new CookiesUtil().getTicketByCookies(request);
        //通过用户凭证ticket获取redis中用户信息
        User user = new RedisUtil().GetUserByTicket(ticket, redisTemplate);
        if (user == null) {
            //用户未登录


            Integer result = cartCookiesService.updateItemNumByCart(num, itemId, request, response);
            System.out.println("用户没有登录情况修改购物车商品数量");

        } else {
            //用户登录
            Integer integer = cartService.updateItemNumByCart(user.getId(), itemId, num);

            System.out.println("用户登录情况修改购物车商品数量");
        }


        return "success";

    }

    /**
     * 实现购物车商品的删除操作
     *
     * @param itemIds 要删除的商品的id
     * @return 访问我购物车controller----跳转购物车页面
     */
    @RequestMapping("/cart/delete/{itemIds}.shtml")
    public String delItemByCart(@PathVariable long itemIds, HttpServletRequest request, HttpServletResponse response) {
        //获取用户登录凭证cookies
        String ticket = new CookiesUtil().getTicketByCookies(request);

        //通过用户凭证ticket获取redis中用户信息
        User user = new RedisUtil().GetUserByTicket(ticket, redisTemplate);
        if (user == null) {
            //用户未登录
            Integer result = cartCookiesService.deleItemByCart(itemIds, request, response);
            System.out.println("用户没有登录情况删除购物车商品");

        } else {
            //用户登录
            Integer result = cartService.deleItemByCart(user.getId(), itemIds);
            System.out.println("用户登录情况删除购物车商品");
        }

        return "redirect:/cart/cart.html";
    }
}
