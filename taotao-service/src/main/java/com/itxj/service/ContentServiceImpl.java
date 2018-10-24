package com.itxj.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itxj.mapper.ContentMapper;
import com.itxj.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.service
 *  @文件名:   ContentServiceImpl
 *  @创建者:   小吉
 *  @创建时间:  2018/10/15 21:40
 *  @描述：    后台管理----内容管理
 */
@Service
public class ContentServiceImpl implements ContentService{

    /*
     *  @文件名:   ContentServiceImpl
     *  @创建时间:  2018/10/15 21:40
     *  @描述：    广告新增
     *  前端参数：
     *          categoryId 所在类目的id
                title:     内容标题
                subTitle   内容子标题
                titleDesc  内容描述
                url         URL
                pic         图片
                pic2        图片2
                content    内容
      *
      * 需补充参数：
      *        created  创建时间
      *        updated  更新时间
     */
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public int add(Content content) {
         //添加创建时间
        content.setCreated(new Date());
        //添加更新时间
        content.setUpdated(new Date());
        //向数据库添加数据
        int result = contentMapper.insert(content);

        return result;
    }

    //广告内容分页查询
    @Override
    public PageInfo<Content> list(Content content, int page, int rows) {
        PageHelper.startPage(page,rows);

        List<Content> list = contentMapper.select(content);
        return new PageInfo<>(list);

    }

    //广告内容删除
    @Override
    public int delete(String ids) {
        //通过“，”切割获得id
        String[] i = ids.split(",");
        int result =0;//删除行数
        for (String id : i) {
            result += contentMapper.deleteByPrimaryKey(Long.parseLong(id));
        }
        return result;
    }

    //广告内容编辑更新
    @Override
    public int update(Content content) {

        content.setUpdated(new Date());
        return contentMapper.updateByPrimaryKey(content);
    }
     //首页大广告图片查询显示-----增加redis缓存查询

    @Override
    public List<Content> selectContentByCategoryParentId(Long cid) {

        //1.从缓存中查询数据


        //2.判断缓存中是否存在数据

        //3.如果缓存中存在数据则返回数据

        //4.如果缓存中不存在数据则从数据库查询数据并且存在redis缓存中
        Content content=new Content();
        content.setCategoryId(cid);
        //查询大广告数据
        List<Content> contents = contentMapper.select(content);

        return contents;
    }


//    //首页大广告图片查询显示
//
//    @Override
//    public List<Content> selectContentByCategoryParentId(Long cid) {
//
//        Content content=new Content();
//        content.setCategoryId(cid);
//        //查询大广告数据
//        List<Content> contents = contentMapper.select(content);
//
//        return contents;
//    }
}
