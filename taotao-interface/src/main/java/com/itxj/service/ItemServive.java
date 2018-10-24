package com.itxj.service;

import com.github.pagehelper.PageInfo;
import com.itxj.pojo.Item;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   ItemServive
 *  @创建者:   小吉
 *  @创建时间:  2018/9/26 9:56
 *  @描述：    TODO
 */
public interface ItemServive {

    Integer addItem(Item item,String desc);
   PageInfo<Item> listItem(int page, int rows);
}
