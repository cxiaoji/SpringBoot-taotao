package com.itxj.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itxj.mapper.ItemCatMapper;
import com.itxj.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   ItemCatServiceImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/9/18 14:32
 *  @描述：    TODO
 */

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> selectItemCatByParentId(long parentId) {

        //因为这里是按照普通列来查询的。所以稍微不一样。
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);

        //假设按学生姓名来查询
        /*Student stu = new Stuent();
        stu.setName("zhangsan")
        itemCatMapper.select(stu);*/

        return itemCatMapper.select(itemCat);

    }
}