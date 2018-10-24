package com.itxj.service;

import com.github.pagehelper.PageInfo;
import com.itxj.pojo.Content;

import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   ContentService
 *  @创建者:   小吉
 *  @创建时间:  2018/10/15 21:38
 *  @描述：    后台管理----内容管理
 */
public interface ContentService {
    //广告新增
    int add(Content content);
    //广告内容分页查询
    PageInfo<Content> list(Content content, int page, int rows);

    //广告内容删除
    int delete(String ids);
    //广告内容编辑更新
    int update(Content content);

    //首页大广告图片查询显示
    List<Content> selectContentByCategoryParentId(Long cid);
}
