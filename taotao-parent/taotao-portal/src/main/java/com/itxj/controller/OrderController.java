package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.Utils.CookiesUtil;
import com.itxj.Utils.RedisUtil;
import com.itxj.pojo.Cart;
import com.itxj.pojo.Order;
import com.itxj.pojo.User;
import com.itxj.service.CartService;
import com.itxj.service.OrderService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.controller
 *  @文件名:   OrderController
 *  @创建者:   小吉
 *  @创建时间:  2018/12/19 19:50
 *  @描述：    TODO
 */
@Controller
public class OrderController {

    @Reference
    private CartService cartService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Reference
    private OrderService orderService;

    //http://www.taotao.com/order/order-cart.shtml
    @RequestMapping("/order/order-cart.shtml")
    public String createOrder(HttpServletRequest request, Model model) {

        //获取登录凭证ticket
        String ticket = CookiesUtil.getTicketByCookies(request);

        //获取用户信息
        Long userId = RedisUtil.GetUserByTicket(ticket, redisTemplate).getId();
        //查询redis购物车商品
        List<Cart> carts = cartService.queryCartByUserId(userId);
        //存入model中

        model.addAttribute("carts", carts);
        return "order-cart";
    }

    @RequestMapping("/service/order/submit")
    @ResponseBody
    public Map<String, Object> SubmitOrder(Order order, HttpServletRequest request) {
        // System.out.println("order=" + order.toString());


        //获取下单的用户
        User user = (User) request.getAttribute("user");
        System.out.println(user);



        //设置下单的用户id
        order.setUserId(user.getId());
        //设置下单用户的昵称
        order.setBuyerNick(user.getUsername());

        //创建订单返回订单号
        String orderId=orderService.saveOrder(order);

        // 创建订单成功，封装返回的数据
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("status", 200);
        map.put("data", orderId);
//清除购物车

        //删除购物车
        RedisUtil.clearCart(redisTemplate,user.getId());
        return map;
    }

    @RequestMapping("/order/success.html")
    public String showOrder(String id, Model model){
        Order order = orderService.queryOrderByOrderId(id);

        model.addAttribute("order",order);

// 获取当前时间的两天后，即时送达时间
        String date = new DateTime().plusDays(2).toString("yyyy年MM月dd日HH时mm分ss秒SSS毫秒");

        model.addAttribute("date",date);


        return "success";
    }
}
