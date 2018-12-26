package com.itxj.service;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   CartService
 *  @创建者:   小吉
 *  @创建时间:  2018/12/15 22:33
 *  @描述：    TODO
 */

import com.itxj.pojo.Cart;

import java.util.List;

public interface CartService {

    /**
     * 添加商品到购物车
     *
     * @param userId
     * @param itemId
     * @param num
     */

    void addItemToCart(long userId,long itemId,Integer num);

    /**
     * 查询之前的购物车数据
     *
     * @param userId
     * @return
     */
    List<Cart> queryCartByUserId(long userId);

    /**
     * 实现购物车商品数量的加减操作
     * @param userId   用户的id
     * @param itemId   商品的id
     * @param num      商品的数量
     * @return
     */
    Integer updateItemNumByCart(long userId,long itemId,Integer num);

    /**
     * 实现购物车商品的删除操作
     *
     * @param userId   用户id
     * @param itemIds  删除的商品的id
     * @return
     */
    Integer deleItemByCart(long userId,long itemIds);

}
