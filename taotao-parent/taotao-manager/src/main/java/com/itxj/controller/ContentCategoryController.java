package com.itxj.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itxj.pojo.ContentCategory;
import com.itxj.service.ContentCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 *  @项目名：  taotao-parent 
 *  @包名：    com.itheima.controller
 *  @文件名:   ContentCategoryController
 *  @创建者:   xiaomi
 *  @创建时间:  2018/9/26 9:00
 *  @描述：    TODO
 */

@Controller
public class ContentCategoryController {


    @Reference
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/rest/content/category")
    @ResponseBody
    public List<ContentCategory> getCategoryByParentId(@RequestParam(defaultValue = "0") Long id){

        List<ContentCategory> list = contentCategoryService.getCategoryByParentId(id);

        return list;
    }

    //添加子类目
    @RequestMapping("/rest/content/category/add")
    @ResponseBody
    public  ContentCategory addCategory(ContentCategory contentCategory){

        ContentCategory parentcategory = contentCategoryService.addCategory(contentCategory);
        return parentcategory;

    }
    //删除类目
    @RequestMapping("/rest/content/category/delete")
    @ResponseBody
    public  String  deleteCategory(ContentCategory contentCategory){


        contentCategoryService.deleteCategory(contentCategory);

        return "success";

    }
    //重命名类目
    @RequestMapping("/rest/content/category/update")
    @ResponseBody
    public  String  updateCategory(ContentCategory contentCategory){


        int result = contentCategoryService.updateCategory(contentCategory);

        return "success";

    }

}
