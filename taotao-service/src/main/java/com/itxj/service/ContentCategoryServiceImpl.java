package com.itxj.service;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.service.impl
 *  @文件名:   ContentCategoryServiceImpl
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/26 9:05
 *  @描述：    TODO
 */

import com.alibaba.dubbo.config.annotation.Service;
import com.itxj.mapper.ContentCategoryMapper;
import com.itxj.pojo.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper mapper;

    //查询商品内容分类
    @Override
    public List<ContentCategory> getCategoryByParentId(Long id) {


        ContentCategory category = new ContentCategory();

        category.setParentId(id);


        return mapper.select(category);
    }

    //商品内容分类管理------添加
    @Override
    public ContentCategory addCategory(ContentCategory contentCategory) {
        /**
         * 前端参数：
         *    parentId: 新建类目所在父类目的id
         *
         *     name: 新建分类的名称
         *
         *
         * bug：
         *
         */
        //TODO.添加一个01子类目，在此类目下添加子类目无效(无刷新的情况下同时进行会出现)



        //通过主键id查询类目信息
        Long parentId = contentCategory.getParentId();

        ContentCategory parentCategory = mapper.selectByPrimaryKey(parentId);

        Boolean isParent = parentCategory.getIsParent();

        //打印是否为父类目
        System.out.println("是否是父类目——————"+isParent);

        //判断该类目是否为父类目，1则无需更改，0则更改为1
        if(!isParent){
            parentCategory.setIsParent(true);
            mapper.updateByPrimaryKeySelective(parentCategory);

        }


        //插入新建的类目信息
        contentCategory.setCreated(new Date());//创建时间
        contentCategory.setStatus(1);//正常使用
        contentCategory.setIsParent(false);//不是父类目
        contentCategory.setSortOrder(1);//出现。。。。。
        contentCategory.setUpdated(new Date());//更新时间

        mapper.insertSelective(contentCategory);





        return contentCategory;

    }

    // 删除子类目

    @Override
    public void deleteCategory(ContentCategory contentCategory) {
        /**
         * //前端传来 parentId: 86  id: 86
         *
         * 1.删除的是子类目
         *    1-1.判断是否是父类目下唯一的子类目，是则修改父类目为子类目
         * 2.删除的是父类目
         *   2-1.删除父类目下所有的子类目
         *
         *   bug:
         *   1.当父类目下所有子类目全部删除后没有变为子类目，而是仍然为父类目
         *   2.删除父类目，出现子类目全部删除正确但是父类目无法删除
         */

        //1.通过id查询类目信息，判断该类目级别
        ContentCategory contentCategoryRank = mapper.selectByPrimaryKey(contentCategory.getId());

        if(contentCategoryRank.getIsParent()){

            //为父类目，删除该类目下所有的子类目：条件为parent_id==父类目id

            /**
             * 前端参数：parentId
             *             id
             */
            List<ContentCategory> list=new ArrayList<>();//通过id循环查询父类目下所有的子类目并且存储到list，后期统一删除
            list.add(contentCategoryRank);
            Long id=contentCategory.getId();//获取要删除的类目的id
            findAllChildCategory(list,id);//通过id查询该类目所有的子类目并且存储list集合
            deleteAllChildCategory(list);//删除指定类目下得所有类目
            /*
            * 1.更新删除类目得父类目的级别
            * 2.通过删除的类目的partentID查询其父类目下是否还有子类目
            * */
            ContentCategory c=new ContentCategory();
            c.setParentId(contentCategory.getParentId());
            int count = mapper.selectCount(c);//查询父类目下的子类目数目
            if(count<1){

                c=new ContentCategory();
                c.setId(contentCategory.getParentId());
                c.setIsParent(false);
                mapper.updateByPrimaryKeySelective(c);//修改父类目的isParent为0
            }

        }
        else if (!contentCategoryRank.getIsParent()){
            //为子类目
            mapper.deleteByPrimaryKey(contentCategory);//先删除选中的子类目

            //判断该子类目所在的父类目下是否还有子类目，条件：parent_id
            ContentCategory clidCategory=new ContentCategory();

            clidCategory.setParentId(contentCategory.getParentId());

            List<ContentCategory> list = mapper.select(clidCategory);//查询该父类目下是否有字类目


            if(null == list || list.size() ==0){

                //说明该父类目下无子类目
                System.out.println("查询该父类目下是否有子类目："+list);

                //查询删除的字类目所在的父类目
                clidCategory.setId(contentCategory.getParentId());
                ContentCategory parentCategory02 = mapper.selectByPrimaryKey(clidCategory);

                //修改父类目（isParent=0）

                parentCategory02.setIsParent(false);

                mapper.updateByPrimaryKey(parentCategory02);
            }



        }


    }


    //删除该类目下所有的子类目
    private void deleteAllChildCategory(List<ContentCategory> list) {

        for (ContentCategory contentCategory : list) {
            mapper.deleteByPrimaryKey(contentCategory);
        }

    }

    //通过id循环查询父类目下所有的子类目并且存储到list，后期统一删除
  public void  findAllChildCategory(List<ContentCategory> list,Long id){
      /**
       * 前端参数：parentId
       *             id
       */

      List<ContentCategory> ListcategoryByParentId = getCategoryByParentId(id);//查询的是该类目所有的子类目和父类目（下还有子类目）
      for (ContentCategory contentCategory : ListcategoryByParentId) {
          //for循环取出类目，并且判断其类目的级别
          if(!contentCategory.getIsParent()){
              //如果是子类目，则存在list
              list.add(contentCategory);

          }
          else if(contentCategory.getIsParent()){

              //如果是父类目，先存list
              list.add(contentCategory);

              //反之如果是父类目，查询该目录下所有子类目并且存储list

              findAllChildCategory(list,contentCategory.getId());

          }
      }


  }

    //重命名类目
    //TODO 未完成
    @Override
    public int updateCategory(ContentCategory contentCategory) {
        //前端传来：
        //     id: 重命名的类目的id
        //     name: 重新修改的名字
        int result = mapper.updateByPrimaryKeySelective(contentCategory);//修该名字
        return result;
    }


}
