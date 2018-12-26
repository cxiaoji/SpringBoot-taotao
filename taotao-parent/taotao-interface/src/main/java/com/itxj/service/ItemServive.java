package com.itxj.service;

import com.github.pagehelper.PageInfo;
import com.itxj.pojo.Item;
import com.itxj.pojo.ItemDesc;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   ItemServive
 *  @创建者:   小吉
 *  @创建时间:  2018/9/26 9:56
 *  @描述：    TODO
 */
public interface ItemServive {

    Integer addItem(Item item, String desc);

    PageInfo<Item> listItem(int page, int rows);

    //通过id查询商品
    Item getItemById(Long id);

    //通过id查询商品详情介绍
    ItemDesc getItemDescById(Long id);

    //删除+上架+下架  商品
    Integer deleteAndInstockAndReshlfItemByItem(String action,String ids);

    //通过id删除商品
    Integer delItem(long id);
    //通过id下架商品
    Integer instockItem(long id);
    //通过id上架商品
    Integer reshelfItem(long id);
    //更新数据
    Integer updateItem(Item item);
}
