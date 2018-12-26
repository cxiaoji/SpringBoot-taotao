package com.itxj.service;

import com.itxj.pojo.Order;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   OrderService
 *  @创建者:   小吉
 *  @创建时间:  2018/12/25 11:16
 *  @描述：    TODO
 */
public interface OrderService {

    /**
     * 创建订单返回订单号
     * @param order
     * @return 订单号
     */
    String saveOrder(Order order);

    /**
     * 查询订单
     * @param orderId
     * @return 订单
     */
    Order queryOrderByOrderId(String orderId);

    /**
     * 清除无效订单
     */
    void clearOrder();


}
