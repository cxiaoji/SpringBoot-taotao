package com.itxj.service.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itxj.pojo.Cart;
import com.itxj.pojo.Item;
import com.itxj.service.CartCookiesService;
import com.itxj.service.ItemServive;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service.Impl
 *  @文件名:   CartCookiesServiceImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/12/18 14:46
 *  @描述：
 */
@Service
public class CartCookiesServiceImpl implements CartCookiesService {

    //@Autowired
    //private ItemMapper itemMapper;
    @Reference
    private ItemServive itemServive;


    @Override
    public Integer addItemByCookie(HttpServletRequest request, long itemId, Integer num, HttpServletResponse response) {


        //添加商品到cookies中


        List<Cart> cartList = null;

        try {
            //1.查询以前cookie中商品的数据
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("itxj_cart".equals(cookie.getName())) {
                        String cartListJson = cookie.getValue();
                        //由于cookies中无法存 空格 字符串，所以在添加购物车时对购物车json数据进行编码
                        //现在需要对字符串进行解码
                        cartListJson = URLDecoder.decode(cartListJson, "utf-8");
                        cartList = new Gson().fromJson(cartListJson,
                                new TypeToken<List<Cart>>() {
                                }.getType());
                        break;
                    }

                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //3.cookie购物车不存在
        if(cartList==null){
            System.out.println("cookie购物车不存在，这是第一次添加购物车到cookie");

            cartList=new ArrayList<>();
        }


            Cart c = null;

            //2-1、遍历新加的商品是否已经存在，如果存在，数量累加，如果不存在，新加商品
            for (Cart cart : cartList) {

                //2-2、商品存在购物车
                if (cart.getItemId() == itemId) {
                    //2-3、商品的数量累加
                    cart.setNum(cart.getNum() + num);
                    //2-4、更新商品的修改数据
                    cart.setUpdate(new Date());
                    c = cart;

                    //2-5、跳出循环
                    break;
                }
            }
            //2-6、购物车没有这一件商品
            if (c == null) {
                //查询商品信息----添加商品到cart
                Item item = itemServive.getItemById(itemId);
                //添加商品到购物车
                c = new Cart();
               // c.setUserId(Long.parseLong(UUID.randomUUID().toString().replace("-","")));
                //c.setId(7l);
                c.setUpdate(new Date());
                c.setCreate(new Date());
                c.setNum(num);
                c.setImages(item.getImages());
                c.setImage(item.getImage());
                c.setItemPrice(item.getPrice());
                c.setItemTitle(item.getTitle());
                c.setItemId(itemId);

                cartList.add(c);
            }


        System.out.println("cookies中购物车数据cartList=="+cartList.toString());

        String json = new Gson().toJson(cartList);

        //由于cookies中无法存 空格字符串，所以需要对购物车json数据进行编码
        try {
            json= URLEncoder.encode(json, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 更新cookie商品数据
        Cookie cookie=new Cookie("itxj_cart",json);
        cookie.setPath("/");
        response.addCookie(cookie);

        return 1;
    }


    //查询cookies中的购物车
    @Override
    public List<Cart> queryItemByCookie(HttpServletRequest request) {
        List<Cart> cartList=null;
        try {
            //查询cookie中购物车数据
            Cookie[] cookies = request.getCookies();
            if (cookies!=null){
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("itxj_cart")){

                        //获取cookie中购物车的json数据
                        String json = cookie.getValue();
                        //对购物车json数据进行解码（由于在添加时cookie不允许添加的数据存在空格----需要编码）
                        json = URLDecoder.decode(json, "utf-8");

                        //json---转为list对象

                        cartList = new Gson().fromJson(json,
                                new TypeToken<List<Cart>>() {
                                }.getType());
                        System.out.println("cookie中的cartList==" + cartList);
                        break;
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cartList;
    }

    //用户未登录删除购物车商品
    // 数据
    @Override
    public Integer deleItemByCart(long itemId,HttpServletRequest request,HttpServletResponse response) {
        //查询cookie中的购物车
        List<Cart> cartList = queryItemByCookie(request);


        //遍历循环获取指定的商品---删除商品
        for (Cart cart : cartList) {
            if (cart.getItemId()==itemId){

                cartList.remove(cart);//删除商品
                break;
            }
        }

        String json = "";

        try {
            //更新cookie中的购物车数据
            json = new Gson().toJson(cartList);
            //对购物车json数据进行解码（由于在添加时cookie不允许添加的数据存在空格----需要编码）
            json = URLEncoder.encode(json, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Cookie cookie=new Cookie("itxj_cart",json);
        cookie.setPath("/");
        response.addCookie(cookie);

        return 1;
    }

    //用户未登录修改购物车商品数量
    @Override
    public Integer updateItemNumByCart(Integer num,long itemId, HttpServletRequest request, HttpServletResponse response) {
        //查询cookie中的购物车
        List<Cart> cartList = queryItemByCookie(request);


        //遍历循环获取指定的商品---删除商品
        for (Cart cart : cartList) {
            if (cart.getItemId()==itemId){

                cart.setNum(num);//修改商品数量
                break;
            }
        }

        String json = "";

        try {
            //更新cookie中的购物车数据
            json = new Gson().toJson(cartList);
            //对购物车json数据进行解码（由于在添加时cookie不允许添加的数据存在空格----需要编码）
            json = URLEncoder.encode(json, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Cookie cookie=new Cookie("itxj_cart",json);
        cookie.setPath("/");
        response.addCookie(cookie);

        return 1;
    }
}
