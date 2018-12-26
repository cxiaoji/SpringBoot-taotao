package com.itxj.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itxj.mapper.OrderItemMapper;
import com.itxj.mapper.OrderMapper;
import com.itxj.mapper.OrderShippingMapper;
import com.itxj.pojo.Order;
import com.itxj.pojo.OrderItem;
import com.itxj.pojo.OrderShipping;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   OrderServiceImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/12/25 11:16
 *  @描述：    TODO
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Override
    public String saveOrder(Order order) {

        //1、创建并且添加订单号
        String orderId=order.getUserId()+ UUID.randomUUID().toString().replace("-","");
        System.out.println("订单号=" + orderId);
        // 2、保存订单数据
        //添加订单的id
        order.setOrderId(orderId);
        //添加订单的状态---未付款
        order.setStatus(1);
        //订单的创建时间
        order.setCreateTime(new Date());
        //订单的更新时间
        order.setUpdateTime(new Date());

        //3、添加商品表order数据

        int resultOrder = orderMapper.insertSelective(order);
        //添加商品条目表数据
        List<OrderItem> orderItems = order.getOrderItems();
        int resultOrderItem=0;
        for (OrderItem orderItem : orderItems) {

            orderItem.setOrderId(orderId);
            orderItem.setId(order.getUserId()+ UUID.randomUUID().toString().replace("-","").substring(0,5));
            resultOrderItem  = orderItemMapper.insertSelective(orderItem);
        }

        //添加商品物流数据
        OrderShipping orderShipping = order.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(order.getCreateTime());
        orderShipping.setUpdated(new Date());

        int resultOrderShipping = orderShippingMapper.insertSelective(orderShipping);


        return orderId;
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);

        return order;
    }

    @Override
    public void clearOrder() {


        Example example=new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("paymentType",1);
        criteria.andEqualTo("status",1);
        criteria .andLessThanOrEqualTo("createTime",new DateTime().minusDays(1).toDate());


        Order order=new Order();
        order.setStatus(6);
        order.setCloseTime(new Date());

        int i = orderMapper.updateByExampleSelective(order, example);
        System.out.println("清除无效订单result==" + i);
    }
}
