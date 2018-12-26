package com.itxj.service;

import com.itxj.pojo.Item;
import com.itxj.pojo.Page;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   SearchServie
 *  @创建者:   小吉
 *  @创建时间:  2018/11/14 15:47
 *  @描述：    TODO
 */
public interface SearchServie {

    //搜索系统查询商品数据
   Page<Item> SearchItem(String q,Integer page);
}
