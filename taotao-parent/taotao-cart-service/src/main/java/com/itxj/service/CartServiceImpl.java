package com.itxj.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itxj.mapper.ItemMapper;
import com.itxj.pojo.Cart;
import com.itxj.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   CartServiceImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/12/15 22:37
 *  @描述：    TODO
 */
@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ItemMapper itemMapper;

    //添加商品到购物车

    @Override
    public void addItemToCart(long userId, long itemId, Integer num) {

        System.out.println("商品的id==" + itemId);
        //用户已经登录-------查询以前的购物车商品
        List<Cart> cartList = queryCartByUserId(userId);

        /**
         * 遍历上面的集合获取的原来购物车中商品的信息
         * 判断新加的商品是否一件存在购物车中----如果存在，商品的数量+num
         * 如果不存在，查询新加的商品添加到集合中，更新redis中购物车商品数据
         */
        Cart c = null;
        //遍历集合
        for (Cart cart : cartList) {

            //判断新加的商品id与原来购物车中的商品是否重复
            if (cart.getItemId() == itemId) {
                System.out.println("以前添加到购物车里商品的数量==" + cart.getNum());
                //商品存在，叠加商品数量
                cart.setNum(cart.getNum() + num);//叠加购物车商品数量
                cart.setUpdate(new Date());//修改新加商品的时间
                c = cart;
                break;

            }
        }

        //通过判断c是否为空-----如果c==null说明该商品是第一次添加，反之则不是第一次添加的
        if (c == null) {
            //通过itemId查询该商品的数据
            Item item = itemMapper.selectByPrimaryKey(itemId);
            //添加到购物车Cart中
            c = new Cart();
            c.setItemId(itemId);
            c.setItemTitle(item.getTitle());
            c.setUpdate(new Date());
            c.setNum(num);
            c.setCreate(new Date());
            c.setImage(item.getImage());
            c.setItemPrice(item.getPrice());
            c.setUserId(userId);
            //添加到cartList集合中
            cartList.add(c);
            System.out.println("购物车添加成功。。。。。。。");
        }

        //购物车添加成功后商品的数量是否叠加
        System.out.println("商品添加到购物车后的id===" + cartList.get(0).getItemId() + "num==" + cartList.get(0).getNum());

        //更新redis中的购物车数据
        String Cartjson = new Gson().toJson(cartList);
        //redisTemplate.delete("itxj_"+userId);
        redisTemplate.opsForValue().set("itxj_" + userId, Cartjson);
    }


    //根据用户id查询对应购物车商品
    @Override
    public List<Cart> queryCartByUserId(long userId) {

        //查询以前的购物车商品
        String cartListJson = redisTemplate.opsForValue().get("itxj_" + userId);

        //判断redis中是否又购物车商品数据
if("null".equals(cartListJson)||cartListJson==null)
{
    //说明这是第一次添加商品-----redis中没有购物车数据
    System.out.println("这是第一次添加商品到购物车........");

    //返回一个null的集合
    return new ArrayList<>();
}

        if (!StringUtils.isEmpty(cartListJson)) {
            List<Cart> cartList = new Gson().fromJson(cartListJson,
                    new TypeToken<List<Cart>>() {
                    }.getType());
            //System.out.println("gson反序列化获取商品集合中商品名称===" + cartList.get(0).getItemTitle());

            return cartList;
        } else {
            //说明这是第一次添加商品-----redis中没有购物车数据
            System.out.println("这是第一次添加商品到购物车........");

            //返回一个null的集合
            return new ArrayList<>();
        }

    }

    /**
     * 实现购物车商品数量的加减操作
     *
     * @param userId 用户的id
     * @param itemId 商品的id
     * @param num    商品的数量
     * @return
     */
    @Override
    public Integer updateItemNumByCart(long userId, long itemId, Integer num) {


            //查询购物车中的商品
            List<Cart> cartList = queryCartByUserId(userId);
            //遍历购物车商品获取指定的商品
            for (Cart cart : cartList) {
                //判断修改的商品id是否存在购物车中
                if (cart.getItemId() == itemId) {
                    //商品的数量操作
                    cart.setNum(num);//修改购物车商品数量
                    cart.setUpdate(new Date());//修改商品的时间
                    break;

                }
            }




        //更新redis购物车数据
        String Cartjson = new Gson().toJson(cartList);
        redisTemplate.opsForValue().set("itxj_" + userId, Cartjson);
        return 1;
    }

    /**
     * 实现购物车商品的删除操作
     *
     * @param userId  用户id
     * @param itemIds 删除的商品的id
     * @return
     */
    @Override
    public Integer deleItemByCart(long userId, long itemIds) {
        //查询购物车商品
        List<Cart> cartList = queryCartByUserId(userId);
        //遍历获取购物车中需要删除的商品
        for (Cart cart : cartList) {
            //判断修改的商品id是否存在购物车中
            if (cart.getItemId() == itemIds) {
                cartList.remove(cart);
                break;

            }
        }

        //更新redis购物车数据
        String Cartjson = new Gson().toJson(cartList);
        redisTemplate.opsForValue().set("itxj_" + userId, Cartjson);
        return 1;
    }
}
