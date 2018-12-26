package com.itxj.service;

import com.itxj.pojo.ContentCategory;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service
 *  @文件名:   ContentCategoryService
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/26 9:05
 *  @描述：    TODO
 */
public interface ContentCategoryService {

    //查询商品内容分类
    List<ContentCategory>  getCategoryByParentId(Long id);
    //商品内容分类管理------添加
    ContentCategory addCategory(ContentCategory contentCategory);

    //删除子类目
    void deleteCategory(ContentCategory contentCategory);

    //重命名类目
    int updateCategory(ContentCategory contentCategory);
}
