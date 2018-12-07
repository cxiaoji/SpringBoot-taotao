package com.itxj.service;

import com.itxj.pojo.ItemCat;

import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   ItemCatService
 *  @创建者:   小吉
 *  @创建时间:  2018/9/18 14:31
 *  @描述：
 */
public interface ItemCatService {

    List<ItemCat> selectItemCatByParentId(long parentId);
}
